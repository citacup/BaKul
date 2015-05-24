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
import android.widget.Toast;

import com.example.citacup.bakul.MyActivity;
import com.example.citacup.bakul.R;

/**
 * Created by CITACUP on PPL.
 */
public class NewKomponen extends Fragment {
    public static String nama1 = "";
    public static int nilai1 = 0;
    public static int persentase1 = 0;
    public static String matkul = "";
    protected ImageView simpan;
    View rootview;
    private EditText nama;
    private EditText nilai;
    private EditText persentase;
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

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matkul = KalkulatorNilai.selected.getNama();
                try {
                    nama1 = nama.getText().toString().trim();
                    nilai1 = Integer.parseInt(nilai.getText().toString());
                    persentase1 = Integer.parseInt(persentase.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(getActivity().getBaseContext(), "Isian salah!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (nama1.isEmpty() || nilai.getText().toString().isEmpty() ||
                        persentase.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity().getBaseContext(), "Isian salah!",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    MyActivity.databaseHelper.insertKomponen(matkul, nama1, persentase1, nilai1);
                }

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction()
                               .add(R.id.container, new KalkulatorHasil())
                               .addToBackStack(null)
                               .commit();
                Toast.makeText(getActivity().getBaseContext(), "Isian disimpan!",
                        Toast.LENGTH_SHORT).show();
            }
        });
        return rootview;
    }
}
