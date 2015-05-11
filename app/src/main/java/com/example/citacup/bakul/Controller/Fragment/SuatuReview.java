package com.example.citacup.bakul.Controller.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.example.citacup.bakul.R;

/**
 * Created by CITACUP on PPL.
 */
public class SuatuReview extends Fragment {
    View rootview;
    private TextView jumlahsuka;
    private TextView jumlahtidaksuka;
    private TextView judul;
    private TextView isi;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.suatureview, container, false);

        isi = (TextView) rootview.findViewById(R.id.isi);
        judul = (TextView) rootview.findViewById(R.id.labeltext);
        jumlahsuka = (TextView) rootview.findViewById(R.id.jumlahsuka);
        jumlahtidaksuka = (TextView) rootview.findViewById(R.id.jumlahtidaksuka);

        String suka = Pencarian.pilihReview.getLike();
        String tidaksuka = Pencarian.pilihReview.getDislike();

        isi.setText(Pencarian.pilihReview.getKomentar());
        judul.setText("Review " + Pencarian.pilihReview.getNama());
        jumlahsuka.setText(suka);
        jumlahtidaksuka.setText(tidaksuka);
        return rootview;
    }
}
