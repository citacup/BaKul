package com.example.citacup.bakul.Entities;

/**
 * Created by SAMSUNG NB on 4/14/2015.
 */
public class KomponenPenilaian {
    String matkul;
    String nama;
    int bobot;
    int nilai;

    public KomponenPenilaian( String matkul, String nama, int bobot, int nilai){
        this.nama = nama;
        this.bobot = bobot;
        this.nilai = nilai;
    }

    public String getMatkul(){
        return matkul;
    }
    public String getNama(){
        return nama;
    }

    public int getBobot(){
        return bobot;
    }

    public int getNilai (){
        return nilai;
    }
}
