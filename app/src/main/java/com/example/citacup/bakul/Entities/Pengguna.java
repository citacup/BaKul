package com.example.citacup.bakul.Entities;

/**
 * Created by Helmi Fakhriandi on 4/12/2015.
 */
public class Pengguna {
    String username;
    String name;
    int jurusan;
    int session;
    int avatar;

    public Pengguna(String username, String name, int jurusan, int session, int avatar) {
        this.username = username;
        this.name = name;
        this.jurusan = jurusan;
        this.session = session;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
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

    public int getAvatar() {
        return avatar;
    }
}
