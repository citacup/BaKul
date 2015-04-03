package com.example.citacup.bakul;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;


public class ActivityMain extends ActionBarActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_kecil);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_main);
    }

    public void mainMenu(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);
        if (v.getId()==R.id.labellogin) {
            //untuk berpindah ke halaman login
            startActivity(new Intent(getBaseContext(), Login.class));
            this.finish();
        }
    }
}
