package com.example.citacup.bakul;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;

/**
 * Created by CITACUP on PPL.
 */
public class KalkulatorNilai extends Fragment {
    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.kalkulatornilai, container, false);
        return rootview;
    }

    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);

    public void mainMenu(View v) {
        //aktifkan efek klik dari button login
        v.startAnimation(buttonClick);
        switch(v.getId()) {
        case R.id.satulayout :
            //fitur 1
            //startActivity(new Intent(getBaseContext(), KalkulatorHasil.class));
            //this.finish();
            break;
        }
    }
}
