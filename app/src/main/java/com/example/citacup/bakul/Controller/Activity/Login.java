package com.example.citacup.bakul.Controller.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.example.citacup.bakul.Business.DatabaseHelper;
import com.example.citacup.bakul.Entities.Pengguna;
import com.example.citacup.bakul.MyActivity;
import com.example.citacup.bakul.R;


public class Login extends ActionBarActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(getBaseContext());

        setContentView(R.layout.login);

        BackGroundTask background = new BackGroundTask(new ProgressDialog(this), Login.this);
        background.execute();
    }

    public void mainMenu(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);
        if (v.getId() == R.id.labellogin) {
            startActivity(new Intent(getBaseContext(), LoginSso.class));
            this.finish();
        }
    }

    public void nextMenu(boolean hasSession) {
        if (hasSession) {
            startActivity(new Intent(getBaseContext(), MyActivity.class));
            this.finish();
        } else {
            startActivity(new Intent(getBaseContext(), LoginSso.class));
            this.finish();
        }
    }

    private class BackGroundTask extends AsyncTask<Void, Void, Void> {
        boolean hasSession;
        private ProgressDialog dialog;
        private Login activity;

        private BackGroundTask(ProgressDialog dialog, Login activity) {
            this.dialog = dialog;
            this.activity = activity;
            hasSession = false;
        }

        protected void onPreExecute() {
            this.dialog.setMessage("Checking Session");
            this.dialog.show();
            this.dialog.setCancelable(false);
        }

        @Override
        protected void onPostExecute(Void v) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            nextMenu(hasSession);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Pengguna current = db.whoHasSession();
                Log.d("Session", current.getUsername() + " " + current.getSession());
                if (current != null) {
                    MyActivity.currentUser = current.getUsername();
                    hasSession = true;
                }
            } catch (Exception e) {
                Log.e("Session", "Exception caught: ", e);
            }
            return null;
        }
    }
}
