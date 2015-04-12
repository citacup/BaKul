package com.example.citacup.bakul.Entities;

/**
 * Created by Helmi Fakhriandi on 4/12/2015.
 */
public class Pengguna {
    String username;
    int jurusan;
    int session;

    public Pengguna(String username, int jurusan, int session) {
        this.username = username;
        this.jurusan = jurusan;
        this.session = session;
    }
    public String getUsername() {
        return username;
    }

    public int getJurusan() {
        return jurusan;
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }
}
