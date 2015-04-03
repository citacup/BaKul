package com.example.citacup.bakul;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;


public class PerancanganKuliah extends ActionBarActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_kecil);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.perancangankuliah);
    }

    public void mainMenu(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);
        switch(v.getId()) {
        case R.id.lihatlayout :
            //lihat rancangan
            startActivity(new Intent(getBaseContext(), LihatRancangan.class));
            this.finish();
            break;
        case R.id.ubahlayout :
            //ubah rancangan
            startActivity(new Intent(getBaseContext(), UbahSemester1.class));
            this.finish();
            break;
        }
    }
}
