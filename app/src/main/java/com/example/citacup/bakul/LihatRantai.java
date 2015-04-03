package com.example.citacup.bakul;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;


public class LihatRantai extends ActionBarActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_kecil);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.mainmenu);
    }

    public void mainMenu(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);
        switch(v.getId()) {
        case R.id.ok :
            //tombol ok
            startActivity(new Intent(getBaseContext(), InformasiMatkul.class));
            this.finish();
            break;
        }
    }
}
