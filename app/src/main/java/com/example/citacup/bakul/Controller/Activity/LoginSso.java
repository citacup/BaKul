package com.example.citacup.bakul.Controller.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.citacup.bakul.Business.DatabaseHelper;
import com.example.citacup.bakul.MyActivity;
import com.example.citacup.bakul.R;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class LoginSso extends ActionBarActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);
    private EditText username;
    private EditText password;
    private ImageView submitButton;
    private HttpPost httppost;
    private HttpClient httpclient;
    private JSONObject jobject;
    private boolean loginSuccess = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_kecil);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setContentView(R.layout.loginsso);

        username = (EditText) findViewById(R.id.username_SSO);
        password = (EditText) findViewById(R.id.password);
        submitButton = (ImageView) findViewById(R.id.email_sign_in_button);
    }

    public void mainMenu(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);
        if (v.getId() == R.id.email_sign_in_button) {
            startActivity(new Intent(getBaseContext(), PilihJurusan.class));
            this.finish();
        }
    }

    public void loginSSO(View v) {
        //get message from message box
        v.startAnimation(buttonClick);

        String uname = username.getText().toString();
        String pwd = password.getText().toString();

        //check whether the msg empty or not
        if (uname.length() > 0 && pwd.length() > 0) {
            httpclient = new DefaultHttpClient();
            //url post web
            httppost = new HttpPost("http://ppl-a07.cs.ui.ac.id/test/index.php");

            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("username", uname));
                nameValuePairs.add(new BasicNameValuePair("password", pwd));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                BackGroundTask doItInBackGround = new BackGroundTask(new ProgressDialog(this),
                        LoginSso.this);
                doItInBackGround.execute();
                if (loginSuccess) {
                    doSomething();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //display message if text field is empty
            Toast.makeText(getBaseContext(), "All fields are required", Toast.LENGTH_SHORT).show();
        }
    }

    public void doSomething() {
        try {
            MyActivity.currentUser = jobject.getString("username");
            MyActivity.currentName = jobject.getString("nama");
            String uname = jobject.getString("username");
            // cek database mobile
            DatabaseHelper db = new DatabaseHelper(getApplicationContext());
            if (db.hasPengguna(uname)) {
                db.switchSessionPengguna(uname, 1);
                startActivity(new Intent(getBaseContext(), MyActivity.class));
                this.finish();
            } else {
                startActivity(new Intent(getBaseContext(), PilihJurusan.class));
                this.finish();
            }
        } catch (Exception e) {
            Log.e("Login SSO", "Exception caught: ", e);
        }
    }

    public void doSomething2() {
        Toast.makeText(getBaseContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
    }

    private class BackGroundTask extends AsyncTask<Void, Void, Void> {
        private ProgressDialog dialog;
        private LoginSso activity;

        private BackGroundTask(ProgressDialog dialog, LoginSso activity) {
            this.dialog = dialog;
            this.activity = activity;
        }

        protected void onPreExecute() {
            this.dialog.setMessage("Logging in");
            this.dialog.show();
            this.dialog.setCancelable(false);
        }

        @Override
        protected void onPostExecute(Void v) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            if (!loginSuccess) {
                doSomething2();
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                //execute - means sending
                HttpResponse response = httpclient.execute(httppost);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                String json = reader.readLine();
                Log.d("http response", json + "");
                jobject = new JSONObject(json);
                Log.d("state response", jobject.getString("state"));
                if (jobject.getString("state").equals("1")) {
                    loginSuccess = true;
                    doSomething();
                } else {
                    loginSuccess = false;
                }
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                Log.e("Login SSO", "Exception caught: ", e);
            }
            return null;
        }
    }

}
