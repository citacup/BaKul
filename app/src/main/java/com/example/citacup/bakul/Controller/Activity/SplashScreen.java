package com.example.citacup.bakul.Controller.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.example.citacup.bakul.R;

/**
 * Created by CITACUP on 3/31/2015.
 */
public class SplashScreen extends Activity {

    //Set waktu lama splashscreen
    private static int splashInterval = 4000;
    //DatabaseHelper db = new DatabaseHelper(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Pengguna user = db.whoHasSession();
        if (user != null) {
            switch (user.getTema()) {
                case 1:
                    (findViewById(R.id.depan))
                            .setBackgroundColor(R.color.biru);
                    break;
                case 2:
                    (findViewById(R.id.depan))
                            .setBackgroundColor(R.color.merah);
                    break;
                case 3:
                    (findViewById(R.id.depan))
                            .setBackgroundColor(R.color.kuning);
                    break;
                case 4:
                    (findViewById(R.id.depan))
                            .setBackgroundColor(R.color.oren);
                    break;
                case 5:
                    (findViewById(R.id.depan))
                            .setBackgroundColor(R.color.hijau);
                    break;
                case 6:
                    (findViewById(R.id.depan))
                            .setBackgroundColor(R.color.ungu);
                    break;
            }
        }*/

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splashscreen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent i = new Intent(SplashScreen.this, Login.class);
                startActivity(i);
                this.finish();
            }

            private void finish() {
                // TODO Auto-generated method stub
            }
        }, splashInterval);

    }
}
