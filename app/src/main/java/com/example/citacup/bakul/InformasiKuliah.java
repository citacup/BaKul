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
public class InformasiKuliah extends Fragment {
    View rootview;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.informasikuliah, container, false);
        return rootview;
    }

    //public void onBackPressed(){
    //    this.finish();
    //}
}
