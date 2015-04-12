package com.example.citacup.bakul;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.example.citacup.bakul.Business.DatabaseHelper;


public class PilihJurusan extends ActionBarActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo_kecil);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.pilihjurusan);
    }

    public void mainMenu(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        switch(v.getId()) {
            case (R.id.ilkomlayout) :
                //pilih jurusan ilkom
                db.insertPengguna(MyActivity.currentUser, 0);
                startActivity(new Intent(getBaseContext(), MyActivity.class));
                this.finish();
                break;
            case (R.id.ilkomimage) :
                //pilih jurusan ilkom
                db.insertPengguna(MyActivity.currentUser, 0);
                startActivity(new Intent(getBaseContext(), MyActivity.class));
                this.finish();
                break;
            case (R.id.ilkomtext) :
                //pilih jurusan ilkom
                db.insertPengguna(MyActivity.currentUser, 0);
                startActivity(new Intent(getBaseContext(), MyActivity.class));
                this.finish();
                break;
            case (R.id.silayout) :
                //pilih jurusan si
                db.insertPengguna(MyActivity.currentUser, 1);
                startActivity(new Intent(getBaseContext(), MyActivity.class));
                this.finish();
                break;
            case (R.id.siimage) :
                //pilih jurusan si
                db.insertPengguna(MyActivity.currentUser, 1);
                startActivity(new Intent(getBaseContext(), MyActivity.class));
                this.finish();
                break;
            case (R.id.sitext) :
                //pilih jurusan si
                db.insertPengguna(MyActivity.currentUser, 1);
                startActivity(new Intent(getBaseContext(), MyActivity.class));
                this.finish();
                break;
        }
    }
}
