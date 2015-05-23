package com.example.citacup.bakul.Entities;

/**
 * Created by CITACUP on 4/14/2015.
 */
public class Kalkulator {
    String username;
    String namamatkul;

    public Kalkulator(String username, String namamatkul) {
        this.username = username;
        this.namamatkul = namamatkul;
    }
    public String getUsername() {
        return username;
    }

    public String getMatkul() {
        return namamatkul;
    }
}

