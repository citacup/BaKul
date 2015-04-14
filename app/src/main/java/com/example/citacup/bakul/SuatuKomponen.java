package com.example.citacup.bakul;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

public class SuatuKomponen extends Fragment {
    View rootview;
    private TextView jumlahsuka;
    private TextView jumlahtidaksuka;
    private TextView judul;
    private TextView isi;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.suatukomponen, container, false);

        judul = (TextView) rootview.findViewById(R.id.labeltext);
        judul.setText("Review "+Pencarian.pilihReview.getNama());

        return rootview;
    }
}
