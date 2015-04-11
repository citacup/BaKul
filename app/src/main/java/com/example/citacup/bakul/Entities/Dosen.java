package com.example.citacup.bakul.Entities;

/**
 * Created by SAMSUNG NB on 4/11/2015.
 */
public class Dosen {

    String iddosen;
    String nama;
    String email;

    //(int id, String nama, String email)
    public Dosen(String iddosen, String nama, String email) {
        this.iddosen = iddosen;
        this.nama = nama;
        this.email = email;
    }

    public String getId() {
        return iddosen;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }
}