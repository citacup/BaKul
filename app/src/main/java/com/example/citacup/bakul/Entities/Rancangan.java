package com.example.citacup.bakul.Entities;

/**
 * Created by CITACUP on 4/14/2015.
 */
public class Rancangan {
    String username;
    String semester;

    public Rancangan(String username, String semester) {
        this.username = username;
        this.semester = semester;
    }

    public String getUsername() {
        return username;
    }

    public String getSemester() {
        return semester;
    }

}
