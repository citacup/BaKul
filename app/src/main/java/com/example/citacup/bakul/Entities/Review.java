package com.example.citacup.bakul.Entities;

/**
 * Created by SAMSUNG NB on 4/12/2015.
 */
public class Review {
    String idrev;
    String username;
    String nama;
    String komentar;
    String app_flag;
    String like;
    String dislike;


    public Review(String idrev,String username, String nama, String komentar, String app_flag, String like, String dislike) {
        this.idrev = idrev;
        this.username = username;
        this.nama = nama;
        this.komentar = komentar;
        this.app_flag = app_flag;
        this.like = like;
        this.dislike = dislike;
    }

    public String getIdrev() {
        return idrev;
    }

    public String getUsername() {
        return username;
    }

    public String getNama() {
        return nama;
    }

    public String getKomentar() {
        return komentar;
    }

    public String getApp_flag() {
        return app_flag;
    }

    public String getLike() {
        return like;
    }

    public String getDislike() {
        return dislike;
    }



}
