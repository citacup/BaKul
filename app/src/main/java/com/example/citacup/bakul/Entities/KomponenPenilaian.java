package com.example.citacup.bakul.Entities;

/**
 * Created by SAMSUNG NB on 4/14/2015.
 */
public class KomponenPenilaian {
    String nama;
    String bobot;
    String nilai;

    public KomponenPenilaian(String nama, String bobot, String nilai) {
        this.nama = nama;
        this.bobot = bobot;
        this.nilai = nilai;
    }

    public String getNama() {
        return nama;
    }

    public String getBobot() {
        return bobot;
    }

    public String getNilai() {
        return nilai;
    }
}
