package com.example.citacup.bakul;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class LoginSso extends ActionBarActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);
    private EditText username;
    private EditText password;
    private Button submitButton;
    private HttpPost httppost;
    HttpClient httpclient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_kecil);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.loginsso);
        username = (EditText) findViewById(R.id.username_SSO);
        password = (EditText) findViewById(R.id.password);
        submitButton = (Button) findViewById(R.id.email_sign_in_button);
    }

    public void mainMenu(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);
        if (v.getId()==R.id.email_sign_in_button) {
            startActivity(new Intent(getBaseContext(), PilihJurusan.class));
            this.finish();
        }
    }
<<<<<<< HEAD
=======

    public void loginSSO(View v){
        //get message from message box
        v.startAnimation(buttonClick);

        String  uname = username.getText().toString();
        String  pwd = password.getText().toString();

        //check whether the msg empty or not
        if(uname.length()>0 && pwd.length()>0) {
            httpclient = new DefaultHttpClient();
            //url post web
            httppost = new HttpPost("http://ppl-a07.cs.ui.ac.id/test/index.php");

            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("username", uname));
                nameValuePairs.add(new BasicNameValuePair("password", pwd));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                //submit and get response
                // HttpResponse response =
                //httpclient.execute(httppost);

                //password.setText(""); //reset the message text field
                //Toast.makeText(getBaseContext(), "Sent", Toast.LENGTH_SHORT).show();
                BackGroundTask doItInBackGround = new BackGroundTask();
                doItInBackGround.execute();
            } /*catch (ClientProtocolException e) {
                e.printStackTrace();
            }*/ catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //display message if text field is empty
            Toast.makeText(getBaseContext(), "All fields are required", Toast.LENGTH_SHORT).show();
        }
    }

    private class BackGroundTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                //execute - means sending
                HttpResponse response = httpclient.execute(httppost);
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                String json = reader.readLine();
                Log.d("http response", json+"");
                JSONObject jobject = new JSONObject(json);
                Log.d("state response",jobject.getString("state"));
                if(jobject.getString("state").equals("1")) {
                    MyActivity.currentUser = jobject.getString("username");
                    // cek database mobile
                    // if yes start
                    // if no write local database hp
                    startActivity(new Intent(getBaseContext(), PilihJurusan.class));
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

/**
 * A login screen that offers login via email/password.
 *
public class LoginActivity extends Activity implements LoaderCallbacks<Cursor> {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     *
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     *
    private UserLoginTask mAuthTask = null;
>>>>>>> origin/master

    public void loginSSO(View v){
        //get message from message box
        v.startAnimation(buttonClick);

        String  uname = username.getText().toString();
        String  pwd = password.getText().toString();

        //check whether the msg empty or not
        if(uname.length()>0 && pwd.length()>0) {
            httpclient = new DefaultHttpClient();
            //url post web
            httppost = new HttpPost("http://ppl-a07.cs.ui.ac.id/test/index.php");

            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("username", uname));
                nameValuePairs.add(new BasicNameValuePair("password", pwd));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                //submit and get response
                // HttpResponse response =
                //httpclient.execute(httppost);

                //password.setText(""); //reset the message text field
                //Toast.makeText(getBaseContext(), "Sent", Toast.LENGTH_SHORT).show();
                BackGroundTask doItInBackGround = new BackGroundTask();
                doItInBackGround.execute();
            } /*catch (ClientProtocolException e) {
                e.printStackTrace();
            }*/ catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //display message if text field is empty
            Toast.makeText(getBaseContext(), "All fields are required", Toast.LENGTH_SHORT).show();
        }
    }

    private class BackGroundTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                //execute - means sending
                HttpResponse response = httpclient.execute(httppost);
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                String json = reader.readLine();
                Log.d("http response", json+"");
                JSONObject jobject = new JSONObject(json);
                Log.d("state response",jobject.getString("state"));
                if(jobject.getString("state").equals("1")) {
                    MyActivity.currentUser = jobject.getString("username");
                    // cek database mobile
                    // if yes start
                    // if no write local database hp
                    startActivity(new Intent(getBaseContext(), PilihJurusan.class));
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
