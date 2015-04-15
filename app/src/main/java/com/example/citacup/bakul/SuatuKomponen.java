package com.example.citacup.bakul;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.citacup.bakul.Entities.Kalkulator;

public class SuatuKomponen extends Fragment {
    View rootview;
    private EditText nama;
    private EditText nilai;
    private EditText persentase;
    private TextView isi;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);

    public static String nama1="";
    public static String nilai1="";
    public static String persentase1="";
    protected Button simpan;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.suatukomponen, container, false);
        nama = (EditText) rootview.findViewById(R.id.nama);
        nilai = (EditText) rootview.findViewById(R.id.nilai);
        persentase = (EditText) rootview.findViewById(R.id.persentase);
        simpan = (Button) rootview.findViewById(R.id.simpan);


        //MyActivity.databaseHelper.insertKomponen();


        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama1 = nama.getText().toString();
                nilai1 = nilai.getText().toString();
                persentase1 = persentase.getText().toString();

                if (nama1.length()==0 || nilai1.length()==0 || persentase1.length()==0) {

                } else {
                    MyActivity.databaseHelper.insertKomponen(nama1, persentase1, nilai1);
                }
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
