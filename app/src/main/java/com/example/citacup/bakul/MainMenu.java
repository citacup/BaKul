package com.example.citacup.bakul;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;

public class MainMenu extends Fragment {
    View rootview;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.mainmenu, container, false);
        return rootview;
    }

    public void mainMenu(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);

        switch(v.getId()) {
        case R.id.satulayout :
            //fitur 1
            //startActivity(new Intent(getBaseContext(), InformasiKuliah.class));
            break;
        case R.id.dualayout :
            //fitur 2
            //startActivity(new Intent(getBaseContext(), PerancanganKuliah.class));
            break;
        case R.id.tigalayout :
            //fitur 3
            //startActivity(new Intent(getBaseContext(), KalkulatorNilai.class));
            break;
        }
    }

   // public void onBackPressed(){
        //this.finish();
    //}
}
