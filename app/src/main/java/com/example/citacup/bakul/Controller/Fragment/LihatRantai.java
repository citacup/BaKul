package com.example.citacup.bakul.Controller.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.citacup.bakul.R;

/**
 * Created by CITACUP on PPL.
 */
public class LihatRantai extends Fragment {
    View rootview;

    // private String kode;
    private TextView judulMatkul;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.lihatrantai, container, false);

        judulMatkul = (TextView) rootview.findViewById(R.id.judulMatkul);

        judulMatkul.setText("Rantai "+Pencarian.pilih.getNama());
        //       kode = Pencarian.pilih.getKodemk();

        ImageView rantaiimg = (ImageView) rootview.findViewById(R.id.rantaiimg);
        //kode dijadikan sebuah variable
        //kemudian disesuaikan dengan drawable.kode , di mana kode bervariasi
        //lanjutkan sampai ke bawah

        if (Pencarian.pilih.getNama().contains("Aljabar")) {
            rantaiimg.setImageResource(R.drawable.alin);
        } else if (Pencarian.pilih.getNama().contains("Dasar Pemrograman")) {
            rantaiimg.setImageResource(R.drawable.ddpik);
        } else if (Pencarian.pilih.getNama().contains("Struktur Data")) {
            rantaiimg.setImageResource(R.drawable.ddpik);
        } else if (Pencarian.pilih.getNama().contains("Pemrograman Web")) {
            rantaiimg.setImageResource(R.drawable.ddpik);
        } else if (Pencarian.pilih.getNama().contains("Perangkat Lunak")) {
            rantaiimg.setImageResource(R.drawable.ddpik);
        } else if (Pencarian.pilih.getNama().contains("Basis Data")) {
            rantaiimg.setImageResource(R.drawable.ddpik);
        } else if (Pencarian.pilih.getNama().contains("Game Development")) {
            rantaiimg.setImageResource(R.drawable.ddpik);
        } else if (Pencarian.pilih.getNama().contains("Geometry")) {
            rantaiimg.setImageResource(R.drawable.ddpik);
        } else if (Pencarian.pilih.getNama().contains("Perolehan Informasi")) {
            rantaiimg.setImageResource(R.drawable.ddpik);
        } else if (Pencarian.pilih.getNama().contains("Pengolahan Multimedia")) {
            rantaiimg.setImageResource(R.drawable.ddpik);
        } else if (Pencarian.pilih.getNama().contains("Analisis Algoritma")) {
            rantaiimg.setImageResource(R.drawable.ddpik);
        } else if (Pencarian.pilih.getNama().contains("Pemrograman Deklaratif")) {
            rantaiimg.setImageResource(R.drawable.ddpik);
        } else if (Pencarian.pilih.getNama().contains("Sistem Interaksi")) {
            rantaiimg.setImageResource(R.drawable.ddpik);
        } else if (Pencarian.pilih.getNama().contains("Berbantuan Komputer")) {
            rantaiimg.setImageResource(R.drawable.ddpik);
        } else if (Pencarian.pilih.getNama().contains("Matematika Diskret 1")) {
            rantaiimg.setImageResource(R.drawable.md1);
        } else if (Pencarian.pilih.getNama().contains("Matematika Diskret 2")) {
            rantaiimg.setImageResource(R.drawable.md2);
        } else if (Pencarian.pilih.getNama().contains("Sistem Cerdas")) {
            rantaiimg.setImageResource(R.drawable.sc);
        } else if (Pencarian.pilih.getNama().contains("Automata")) {
            rantaiimg.setImageResource(R.drawable.sc);
        } else if (Pencarian.pilih.getNama().contains("Pengolahan Bahasa Manusia")) {
            rantaiimg.setImageResource(R.drawable.sc);
        } else if (Pencarian.pilih.getNama().contains("Logika Komputasional")) {
            rantaiimg.setImageResource(R.drawable.sc);
        } else if (Pencarian.pilih.getNama().contains("Komputasi Lunak")) {
            rantaiimg.setImageResource(R.drawable.sc);
        } else if (Pencarian.pilih.getNama().contains("Machine Learning")) {
            rantaiimg.setImageResource(R.drawable.sc);
        } else if (Pencarian.pilih.getNama().contains("Bisnis")) {
            rantaiimg.setImageResource(R.drawable.adbis);
        } else if (Pencarian.pilih.getNama().contains("Matematika Diskret 1")) {
            rantaiimg.setImageResource(R.drawable.md1);
        } else if (Pencarian.pilih.getNama().contains("Matematika Diskret 2")) {
            rantaiimg.setImageResource(R.drawable.md2);
        } else if (Pencarian.pilih.getNama().contains("Matematika Dasar 1")) {
            rantaiimg.setImageResource(R.drawable.matdas1);
        } else if (Pencarian.pilih.getNama().contains("Analisis Numerik")) {
            rantaiimg.setImageResource(R.drawable.anum);
        } else if (Pencarian.pilih.getNama().contains("Pengantar Sistem")) {
            rantaiimg.setImageResource(R.drawable.psd);
        } else if (Pencarian.pilih.getNama().contains("Sistem Operasi")) {
            rantaiimg.setImageResource(R.drawable.psd);
        } else if (Pencarian.pilih.getNama().contains("Pengantar Sistem")) {
            rantaiimg.setImageResource(R.drawable.psd);
        } else if (Pencarian.pilih.getNama().contains("Pengantar Operasi")) {
            rantaiimg.setImageResource(R.drawable.psd);
        } else if (Pencarian.pilih.getNama().contains("Organisasi Sistem")) {
            rantaiimg.setImageResource(R.drawable.psd);
        } else if (Pencarian.pilih.getNama().contains("Rancangan Sistem")) {
            rantaiimg.setImageResource(R.drawable.psd);
        } else if (Pencarian.pilih.getNama().contains("Embedded")) {
            rantaiimg.setImageResource(R.drawable.psd);
        } else if (Pencarian.pilih.getNama().contains("Komunikasi Data")) {
            rantaiimg.setImageResource(R.drawable.jarkomdat);
        } else if (Pencarian.pilih.getNama().contains("Arsitektur Komputer")) {
            rantaiimg.setImageResource(R.drawable.ddak);
        } else if (Pencarian.pilih.getNama().contains("Administrasi Sistem")) {
            rantaiimg.setImageResource(R.drawable.ddak);
        } else if (Pencarian.pilih.getNama().contains("Jaringan Komputer")) {
            rantaiimg.setImageResource(R.drawable.jarkom);
        } else if (Pencarian.pilih.getNama().contains("Probabilitas")) {
            rantaiimg.setImageResource(R.drawable.sc);
        } else if (Pencarian.pilih.getNama().contains("Bioinformatika")) {
            rantaiimg.setImageResource(R.drawable.sc);
        } else {
            rantaiimg.setImageResource(R.drawable.ddpik);
        }
        return rootview;
    }


}
