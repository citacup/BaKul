package com.example.citacup.bakul;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

/**
 * Created by CITACUP on PPL.
 */
public class LihatMatkul extends Fragment {
    View rootview;

    private TextView judulMatkul;
    private TextView kode;
    private TextView nama;
    private TextView jumlahsks;
    private TextView semester;
    private TextView deskripsi;
    private TextView kategori;
    private TextView objektif;
    private TextView referensi;

    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.3F);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.lihatmatkul, container, false);


        judulMatkul = (TextView) rootview.findViewById(R.id.judulMatkul);
        kode = (TextView) rootview.findViewById(R.id.kode);
        nama = (TextView) rootview.findViewById(R.id.nama);
        jumlahsks = (TextView) rootview.findViewById(R.id.sks);
        semester = (TextView) rootview.findViewById(R.id.semester);
        kategori = (TextView) rootview.findViewById(R.id.kategori);
        deskripsi = (TextView) rootview.findViewById(R.id.deskripsi);
        objektif = (TextView) rootview.findViewById(R.id.objektif);
        referensi = (TextView) rootview.findViewById(R.id.referensi);

       // kode.setText(InformasiDosen.selected.getNama());
        judulMatkul.setText(Pencarian.pilih.getNama());
        kode.setText(Pencarian.pilih.getKodemk());
        nama.setText(Pencarian.pilih.getNama());
        jumlahsks.setText(Pencarian.pilih.getSks());
        semester.setText(Pencarian.pilih.getSemester());
        kategori.setText(Pencarian.pilih.getKategori());
        objektif.setText(Pencarian.pilih.getObjektif());
        deskripsi.setText(Pencarian.pilih.getDeskripsi());
        referensi.setText(Pencarian.pilih.getReferensi());
        return rootview;
    }

}
