package com.example.citacup.bakul.Controller.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.citacup.bakul.MyActivity;
import com.example.citacup.bakul.R;

/**
 * Created by CITACUP on PPL.
 */
public class EditKomponen extends Fragment {
    public static String nama1 = "";
    public static String nilai1 = "";
    public static String persentase1 = "";
    protected ImageView simpan;
    protected ImageView hapus;
    View rootview;
    private EditText nama;
    private EditText nilai;
    private EditText persentase;
    private TextView isi;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.suatukomponen, container, false);
        nama = (EditText) rootview.findViewById(R.id.nama);
        nilai = (EditText) rootview.findViewById(R.id.nilai);
        persentase = (EditText) rootview.findViewById(R.id.persentase);
        simpan = (ImageView) rootview.findViewById(R.id.simpan);
        hapus = (ImageView) rootview.findViewById(R.id.hapus);

        nama.setText(KalkulatorHasil.dipilih.getNama());
        nilai.setText(Integer.toString(KalkulatorHasil.dipilih.getNilai()));
        persentase.setText(Integer.toString(KalkulatorHasil.dipilih.getBobot()));
        //MyActivity.databaseHelper.insertKomponen();


        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nama1 = nama.getText().toString();
                nilai1 = nilai.getText().toString();
                persentase1 = persentase.getText().toString();
                MyActivity.databaseHelper.updateKomponen(nama1, nilai1, persentase1);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new KalkulatorHasil())
                               .commit();

                // persentase1 = persentase.getText().toString()

                ;
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nama1 = nama.getText().toString();
                MyActivity.databaseHelper.deleteKomponen(nama1);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                               .replace(R.id.container, new KalkulatorHasil())
                               .commit();

                ;
            }
        });
        return rootview;
    }
}
