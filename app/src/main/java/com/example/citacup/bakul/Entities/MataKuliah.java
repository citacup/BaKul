package com.example.citacup.bakul.Entities;

/**
 * Created by SAMSUNG NB on 4/11/2015.
 */
public class MataKuliah {

    String kodemk;
    String nama;
    int sks;
    String semester;
    String islulus;
    String deskripsi;
    String referensi;
    String objektif;
    String kategori;

    //(int id, String nama, String email)
    public MataKuliah(String kodemk, String nama, int sks, String semester, String islulus,String deskripsi, String referensi, String objektif,String kategori) {
        this.kodemk = kodemk;
        this.nama = nama;
        this.sks = sks;
        this.semester = semester;
        this.islulus = islulus;
        this.deskripsi = deskripsi;
        this.referensi = referensi;
        this.objektif = objektif;
        this.kategori = kategori;

    }

    public String getKodemk() {
        return kodemk;
    }
    public String getNama() {
        return nama;
    }
    public int getSks() {
        return sks;
    }
    public String getSemester() {
        return semester;
    }
    public String getIslulus() {
        return islulus;
    }
    public String getDeskripsi() {
        return deskripsi;
    }
    public String getReferensi() {
        return referensi;
    }
    public String getObjektif() {
        return objektif;
    }
    public String getKategori() {
        return kategori;
    }
}
