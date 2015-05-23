package com.example.citacup.bakul.Business;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.citacup.bakul.Entities.Dosen;
import com.example.citacup.bakul.Entities.Kalkulator;
import com.example.citacup.bakul.Entities.Kategori;
import com.example.citacup.bakul.Entities.KomponenPenilaian;
import com.example.citacup.bakul.Entities.MataKuliah;
import com.example.citacup.bakul.Entities.Pengguna;
import com.example.citacup.bakul.Entities.Review;
import com.example.citacup.bakul.MyActivity;

import java.util.ArrayList;

/**
 * Created by CITACUP on 4/11/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private String FILENAME = "DBBakul.db";

    public DatabaseHelper(Context context) {
        super(context, "FILENAME", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        String query
                = "CREATE TABLE `Dosen` (`iddosen` TEXT PRIMARY KEY, `nama` TEXT, `email` TEXT); ";
        db.execSQL(query);
        query
                = "CREATE TABLE `Matakuliah` (`kodemk` TEXT PRIMARY KEY, `nama` TEXT, " +
                "`sks` INTEGER, `semester` TEXT, `islulus` TEXT,`deskripsi` TEXT,`referensi` TEXT, " +
                "`objektif` TEXT,`kategori` TEXT); ";
        db.execSQL(query);
        query = "CREATE TABLE `Kategori` (`kategori` TEXT PRIMARY KEY); ";
        db.execSQL(query);
        query
                = "CREATE TABLE `Review` (`idrev` TEXT PRIMARY KEY,`username` TEXT,`nama` TEXT," +
                "`komentar` TEXT,`app_flag` TEXT,`like` TEXT,`dislike` TEXT); ";
        db.execSQL(query);
        query
                = "CREATE TABLE IF NOT EXISTS `Pengguna` (`username` TEXT PRIMARY KEY,`name` TEXT, " +
                "`jurusan` INTEGER,`session` INTEGER, `avatar` INTEGER)";
        db.execSQL(query);
        query = "CREATE TABLE IF NOT EXISTS `Kalkulator` (`username` TEXT, `namamatkul` TEXT)";
        db.execSQL(query);
        query
                = "CREATE TABLE IF NOT EXISTS `Rancangan` (`username` TEXT PRIMARY KEY, " +
                "`semester` TEXT)";
        db.execSQL(query);
        query = "CREATE TABLE IF NOT EXISTS `KomponenPenilaian` (`matkul` Text,`nama` TEXT, `bobot` INTEGER, `nilai` INTEGER)";
        db.execSQL(query);
    }

    public boolean insertKomponen (String matkul, String nama, int bobot, int nilai) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matkul",matkul);
        values.put("nama", nama);
        values.put("bobot", bobot);
        values.put("nilai", nilai);
        sqLiteDatabase.insert("KomponenPenilaian", null, values);

        return !getAllKomponen().isEmpty();
    }

    public void insertRancangan(String username, String semester) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("semester", semester);
        sqLiteDatabase.insert("Rancangan", null, values);
    }

    public boolean deleteRancangan(String username, String semester) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("Rancangan",
                "username = '" + username + "' and semester = '" + semester + "'", null);
        return getKalkulator(username, semester).isEmpty();
    }

    public boolean insertKalkulator(String username, String namamatkul) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("namamatkul", namamatkul);
        sqLiteDatabase.insert("Kalkulator", null, values);
        return !getKalkulator(username, namamatkul).isEmpty();
    }

    public boolean deleteKalkulator(String username, String namamatkul) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("Kalkulator",
                "username = '" + username + "' and namamatkul = '" + namamatkul + "'", null);
        return getKalkulator(username, namamatkul).isEmpty();
    }

    public void insertDosen(String iddosen, String nama, String email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("iddosen", iddosen);
        values.put("nama", nama);
        values.put("email", email);
        sqLiteDatabase.insert("Dosen", null, values);
    }

    public void insertMatakuliah(
            String kodemk, String nama, String sks, String semester, String islulus,
            String deskripsi, String referensi, String objektif, String kategori) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("kodemk", kodemk);
        values.put("nama", nama);
        values.put("sks", sks);
        values.put("semester", semester);
        values.put("islulus", islulus);
        values.put("deskripsi", deskripsi);
        values.put("referensi", referensi);
        values.put("objektif", objektif);
        values.put("kategori", kategori);
        sqLiteDatabase.insert("Matakuliah", null, values);
    }

    public void insertKategori(String kategori) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("kategori", kategori);
        sqLiteDatabase.insert("Kategori", null, values);
    }

    public void insertReview(
            String idrev, String username, String nama, String komentar, String app_flag,
            String like, String dislike) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idrev", idrev);
        values.put("username", username);
        values.put("nama", nama);
        values.put("komentar", komentar);
        values.put("app_flag", app_flag);
        values.put("like", like);
        values.put("dislike", dislike);
        sqLiteDatabase.insert("Review", null, values);
    }

    public void insertPengguna(String username,String name, int jurusan, int avatar) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("name", name);
        values.put("jurusan", jurusan);
        values.put("session", 0);
        values.put("avatar", avatar);
        sqLiteDatabase.insert("Pengguna", null, values);
    }

    //pengguna
    public boolean hasPengguna(String username) {
        String query = "select * from Pengguna where username='" + username + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        int res = sqLiteDatabase.rawQuery(query, null).getCount();
        return res != 0;
    }

    public Pengguna getPengguna(String username) {
        Pengguna res = null;
        String query = "select * from Pengguna where username='" + username + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            res = new Pengguna(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3),
                    cursor.getInt(4));
        }
        return res;
    }

    public void switchSessionPengguna(String username, int session) {
        Pengguna current = getPengguna(username);
        String query;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        if (current != null) {
            query = "UPDATE Pengguna SET session=" + session + " WHERE username='" + username + "'";
            sqLiteDatabase.execSQL(query);
        }
    }

    public void updateAvatar(String username, int avatar) {
        Pengguna current = getPengguna(username);
        String query;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        if (current != null) {
            query = "UPDATE Pengguna SET avatar=" + avatar + " WHERE username='" + username + "'";
            sqLiteDatabase.execSQL(query);
        }
    }

    public Pengguna whoHasSession() {
        Pengguna res = null;
        String query = "select * from Pengguna where session = 1";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            res = new Pengguna(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3),
                    cursor.getInt(4));
        }
        return res;
    }

    ///////////////////DOSEN!!!!!!!!!!!!!!!!!
    public ArrayList<String> getAllDosen() {
        ArrayList<Dosen> listDosen = new ArrayList<Dosen>();
        ArrayList<String> listNamaDosen = new ArrayList<String>();
        String fetchdata = "select * from Dosen";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);
        ////(int id, String nama, String email)
        if (cursor.moveToFirst()) {
            Log.d("cursor dosen", "tidak null");
            do {
                Dosen dosen = new Dosen(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2));
                listDosen.add(dosen);
            }
            while (cursor.moveToNext());
        }

        for (Dosen dosen : listDosen) {
            listNamaDosen.add(dosen.getNama());
        }
        return listNamaDosen;
    }


    public Dosen getDosenFromID(int id) {
        Dosen dosen = null;
        String query = "SELECT * from Dosen where id = " + id;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            dosen = new Dosen(cursor.getString(0), cursor.getString(1), cursor.getString(2));
        }
        return dosen;
    }

    public Dosen getDosenFromNama(String nama) {
        Dosen dosen = null;
        String query = "SELECT * from Dosen where nama = " + "'" + nama + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            dosen = new Dosen(cursor.getString(0), cursor.getString(1), cursor.getString(2));
        }
        return dosen;
    }

    //////////////////////////////////////////BUAT MATAKULIAH!!!!!!!!!!!!!!!!!!
    public ArrayList<String> getAllMatakuliah() {
        ArrayList<MataKuliah> listMatakuliah = new ArrayList<MataKuliah>();
        ArrayList<String> listNamaMatakuliah = new ArrayList<String>();
        String fetchdata = "select * from Matakuliah";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);
        ////(int id, String nama, String email)
        if (cursor.moveToFirst()) {
            Log.d("cursor dosen", "tidak null");
            do {
                MataKuliah matakuliah = new MataKuliah(cursor.getString(0), cursor.getString(1),
                        cursor.getInt(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6), cursor.getString(7),
                        cursor.getString(8));
                listMatakuliah.add(matakuliah);
            }
            while (cursor.moveToNext());
        }

        for (MataKuliah matakuliah : listMatakuliah) {
            listNamaMatakuliah.add(matakuliah.getNama());
        }
        return listNamaMatakuliah;
    }

    public ArrayList<String> getMatakuliahfromKategori(String kategori) {
        ArrayList<MataKuliah> listMatakuliah = new ArrayList<MataKuliah>();
        ArrayList<String> listNamaMatakuliah = new ArrayList<String>();
        String fetchdata = "select * from Matakuliah where kategori='" + kategori + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);
        ////(int id, String nama, String email)
        if (cursor.moveToFirst()) {
            do {
                MataKuliah matakuliah = new MataKuliah(cursor.getString(0), cursor.getString(1),
                        cursor.getInt(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6), cursor.getString(7),
                        cursor.getString(8));
                listMatakuliah.add(matakuliah);
            }
            while (cursor.moveToNext());
        }

        for (MataKuliah matakuliah : listMatakuliah) {
            listNamaMatakuliah.add(matakuliah.getNama());
        }
        return listNamaMatakuliah;
    }

    public MataKuliah getMatakuliahFromNama(String nama) {
        MataKuliah matakuliah = null;
        String query = "SELECT * from Matakuliah where nama = " + "'" + nama + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            matakuliah = new MataKuliah(cursor.getString(0), cursor.getString(1), cursor.getInt(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5),
                    cursor.getString(6), cursor.getString(7), cursor.getString(8));
        }
        return matakuliah;
    }

    public ArrayList<String> getMataKuliahFromNama2(String nama) {
        ArrayList<MataKuliah> listMatkul = new ArrayList<MataKuliah>();
        ArrayList<String> listMatkul2 = new ArrayList<String>();
        String query = "SELECT * from Matakuliah where nama like " + "'%" + nama + "%'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        ////(int id, String nama, String email)
        if (cursor.moveToFirst()) {
            Log.d("cursor dosen", "tidak null");
            do {
                MataKuliah matkul = new MataKuliah(cursor.getString(0), cursor.getString(1),
                        cursor.getInt(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6), cursor.getString(7),
                        cursor.getString(8));
                listMatkul.add(matkul);
            }
            while (cursor.moveToNext());
        }

        for (MataKuliah matakuliah : listMatkul) {
            listMatkul2.add(matakuliah.getNama());
        }
        return listMatkul2;
    }

    public String getSemesterMatkulFromID(int id) {
        MataKuliah matakuliah = null;
        String query = "SELECT semester from Matakuliah where kodemk = " + id;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String semesterMatkul = "";
        if (cursor.moveToFirst()) {
            semesterMatkul = cursor.getString(3);
        }

        return semesterMatkul;
    }

    public String getNamaMatkulFromID(int id) {
        MataKuliah matakuliah = null;
        String query = "SELECT nama from Matakuliah where kodemk = " + id;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String namaMatkul = "";
        if (cursor.moveToFirst()) {
            namaMatkul = cursor.getString(1);
        }

        return namaMatkul;
    }

    public int getSksMatkulFromID(int id) {
        MataKuliah matakuliah = null;
        String query = "SELECT sks from Matakuliah where kodemk = " + id;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        int sksMatkul = 0;
        if (cursor.moveToFirst()) {
            sksMatkul = cursor.getInt(2);
        }

        return sksMatkul;
    }

    public String getDeskripsiMatkulFromID(int id) {
        MataKuliah matakuliah = null;
        String query = "SELECT deskripsi from Matakuliah where kodemk = " + id;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String deskripsiMatkul = "";
        if (cursor.moveToFirst()) {
            deskripsiMatkul = cursor.getString(5);
        }

        return deskripsiMatkul;
    }

    public String getReferensiMatkulFromID(int id) {
        MataKuliah matakuliah = null;
        String query = "SELECT referensi from Matakuliah where kodemk = " + id;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String referensiMatkul = "";
        if (cursor.moveToFirst()) {
            referensiMatkul = cursor.getString(6);
        }

        return referensiMatkul;
    }

    public String getObjektifMatkulFromID(int id) {
        MataKuliah matakuliah = null;
        String query = "SELECT objektif from Matakuliah where kodemk = " + id;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String objektifMatkul = "";
        if (cursor.moveToFirst()) {
            objektifMatkul = cursor.getString(7);
        }

        return objektifMatkul;
    }

    public String getKategoriMatkulFromID(int id) {
        MataKuliah matakuliah = null;
        String query = "SELECT kategori from Matakuliah where kodemk = " + id;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String kategoriMatkul = "";
        if (cursor.moveToFirst()) {
            kategoriMatkul = cursor.getString(8);
        }

        return kategoriMatkul;
    }

    public void setLulus(String matakuliah) {

        String query;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        //query = "UPDATE Pengguna SET session="+session+" WHERE username='"+username+"'";
        query = "UPDATE Matakuliah SET islulus= '1' where nama ='" + matakuliah + "'";
        sqLiteDatabase.execSQL(query);

    }

    public void setTidakLulus(String matakuliah) {

        String query;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        //query = "UPDATE Pengguna SET session="+session+" WHERE username='"+username+"'";
        query = "UPDATE Matakuliah SET islulus= '0' where nama ='" + matakuliah + "'";
        sqLiteDatabase.execSQL(query);

    }


    public ArrayList<String> getMatkulLulus() {
        ArrayList<MataKuliah> listLulus = new ArrayList<MataKuliah>();
        ArrayList<String> listLulus2 = new ArrayList<String>();
        String fetchdata = "select * from Matakuliah where islulus = '1'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);
        ////(int id, String nama, String email)
        if (cursor.moveToFirst()) {
            do {
                MataKuliah matakuliah = new MataKuliah(cursor.getString(0), cursor.getString(1),
                        cursor.getInt(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6), cursor.getString(7),
                        cursor.getString(8));
                listLulus.add(matakuliah);
            }
            while (cursor.moveToNext());
        }

        for (MataKuliah matakuliah : listLulus) {
            listLulus2.add(matakuliah.getNama());
        }
        return listLulus2;
    }
    //--------------------------------------------------------------------------------------///

    ////////////////////////////////////KATEGORI


    public ArrayList<String> getAllKategori() {
        ArrayList<Kategori> listKategori = new ArrayList<Kategori>();
        ArrayList<String> listNamaKategori = new ArrayList<String>();
        String fetchdata = "select * from Kategori";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);
        ////(int id, String nama, String email)
        if (cursor.moveToFirst()) {
            Log.d("cursor dosen", "tidak null");
            do {
                Kategori kategori = new Kategori(cursor.getString(0));
                listKategori.add(kategori);
            }
            while (cursor.moveToNext());
        }

        for (Kategori kategori : listKategori) {
            listNamaKategori.add(kategori.getKategori());
        }
        return listNamaKategori;
    }

    /////////////////////////////------------------------------------////////////////
////////////////////////////////////REVIEW////////////////////////

    public ArrayList<String> getAllReview() {
        ArrayList<Review> listReview = new ArrayList<Review>();
        ArrayList<String> listReviewMatkul = new ArrayList<String>();
        String fetchdata = "select * from Review";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);
        ////(int id, String nama, String email)
        if (cursor.moveToFirst()) {
            Log.d("cursor dosen", "tidak null");
            do {
                Review review = new Review(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6));
                listReview.add(review);
            }
            while (cursor.moveToNext());
        }

        for (Review review : listReview) {
            listReviewMatkul.add(review.getNama());
        }
        return listReviewMatkul;
    }

    public Review getReviewFromKomentar(String komentar) {
        Review review = null;
        ArrayList<Review> listReview2 = new ArrayList<Review>();
        ArrayList<String> listReviewMatkul2 = new ArrayList<String>();
        String query = "SELECT * from Review where komentar = " + "'" + komentar + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            review = new Review(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5),
                    cursor.getString(6));
        }
        return review;
    }


    public ArrayList<String> getReviewFromNama(String nama) {
        ArrayList<Review> listReview = new ArrayList<Review>();
        ArrayList<String> listReview2 = new ArrayList<String>();
        String query = "SELECT * from Review where nama = " + "'" + nama + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        ////(int id, String nama, String email)
        if (cursor.moveToFirst()) {
            Log.d("cursor dosen", "tidak null");
            do {
                Review review = new Review(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6));
                listReview.add(review);
            }
            while (cursor.moveToNext());
        }

        for (Review review : listReview) {
            listReview2.add(review.getKomentar());
        }
        return listReview2;
    }

    ////////////////////KALKULATOR!!!!!!!!!!!!!!!!
    public ArrayList<Kalkulator> getKalkulator(String username, String namamatkul) {
        ArrayList<Kalkulator> listKalkulator = new ArrayList<Kalkulator>();
        String query = "SELECT * from Kalkulator where username = " + "'" + username +
                "' and namamatkul = '" + namamatkul + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Kalkulator kalkulator = new Kalkulator(cursor.getString(0), cursor.getString(1));
                listKalkulator.add(kalkulator);
            }
            while (cursor.moveToNext());
        }
        return listKalkulator;
    }

    public ArrayList<String> getAllMatkulKalkulator() {
        ArrayList<Kalkulator> listKalkulator = new ArrayList<Kalkulator>();
        ArrayList<String> listKalkulator2 = new ArrayList<String>();
        String fetchdata = "select * from Kalkulator where username = '" + MyActivity.currentUser +
                "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);
        ////(int id, String nama, String email)
        if (cursor.moveToFirst()) {
            do {
                Kalkulator kalkulator = new Kalkulator(cursor.getString(0), cursor.getString(1));
                listKalkulator.add(kalkulator);
            }
            while (cursor.moveToNext());
        }

        for (Kalkulator kalkulator : listKalkulator) {
            listKalkulator2.add(kalkulator.getMatkul());
        }
        return listKalkulator2;
    }

    ///////////////////////RANCANGAN!!!!!!!!!!!!!!!
    public String getRancangan(String username) {
        String query = "SELECT semester from Rancangan where username = '" + username + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String rancangan = null;
        if (cursor.moveToFirst()) {
            rancangan = cursor.getString(0);
        }

        return rancangan;
    }

    public boolean cekValidLulus(int sks) {
        String query = "SELECT sum(sks) from MataKuliah where islulus = '1'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        int rancangan = 0;
        if (cursor.moveToFirst()) {
            rancangan = cursor.getInt(0);
        }
        if (rancangan + sks <= 24) {
            return true;
        }
        return false;
    }

    ///-------------------KOMPONEN--------------------------------------////////////////////


    public ArrayList<String> getAllKomponen() {
        ArrayList<KomponenPenilaian> listKomponen = new ArrayList<KomponenPenilaian>();
        ArrayList<String> listKomponenMatkul = new ArrayList <String>();
        String fetchdata = "select * from KomponenPenilaian";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);
        ////(int id, String nama, String email)
        if (cursor.moveToFirst()) {
            Log.d("cursor dosen", "tidak null");
            do {
                KomponenPenilaian komponen = new KomponenPenilaian(cursor.getString(0), cursor.getString(1),cursor.getInt(2),cursor.getInt(3));
                listKomponen.add(komponen);
            } while (cursor.moveToNext());
        }

        for(KomponenPenilaian komponenpenilaian : listKomponen){
            listKomponenMatkul.add(komponenpenilaian.getNama());
        }
        return listKomponenMatkul;
    }

    public KomponenPenilaian getKomponenFromNama (String nama) {
        KomponenPenilaian komponenPenilaian = null;
        String query = "SELECT * from KomponenPenilaian where nama = "+"'" + nama+"'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            komponenPenilaian = new KomponenPenilaian(cursor.getString(0), cursor.getString(1),cursor.getInt(2),cursor.getInt(3));
        }
        return komponenPenilaian;
    }

    public void updateKomponen(String nama, String nilai, String persentase){
        String query;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        query = "UPDATE KomponenPenilaian SET nilai="+nilai+", bobot="+persentase+" WHERE nama='"+nama+"'";
        sqLiteDatabase.execSQL(query);

    }


    public KomponenPenilaian getKomponenFromMatkul (String matkul) {
        KomponenPenilaian komponenPenilaian = null;
        String query = "SELECT * from KomponenPenilaian where matkul = "+"'" + matkul+"'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            komponenPenilaian = new KomponenPenilaian(cursor.getString(0), cursor.getString(1),cursor.getInt(2),cursor.getInt(3));
        }
        return komponenPenilaian;
    }

    public ArrayList<String> getKomponenFromMatkul2(String matkul) {
        ArrayList<KomponenPenilaian> listKomponen = new ArrayList<KomponenPenilaian>();
        ArrayList<String> listKomponenMatkul = new ArrayList <String>();
        String fetchdata = "SELECT * from KomponenPenilaian where matkul = "+"'" + matkul+"'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);
        ////(int id, String nama, String email)
        if (cursor.moveToFirst()) {
            Log.d("cursor dosen", "tidak null");
            do {
                KomponenPenilaian komponen = new KomponenPenilaian(cursor.getString(0), cursor.getString(1),cursor.getInt(2),cursor.getInt(3));
                listKomponen.add(komponen);
            } while (cursor.moveToNext());
        }

        for(KomponenPenilaian komponenpenilaian : listKomponen){
            listKomponenMatkul.add(komponenpenilaian.getNama());
        }
        return listKomponenMatkul;
    }

    public void deleteKomponen(String nama){
        String query;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        query = "DELETE From KomponenPenilaian WHERE nama='"+nama+"'";
        sqLiteDatabase.execSQL(query);

    }

    public ArrayList<Integer> getNilaiKomponenFromMatkul(String matkul) {
        ArrayList<KomponenPenilaian> listKomponen = new ArrayList<KomponenPenilaian>();
        ArrayList<Integer> listKomponenMatkul = new ArrayList <Integer>();
        String fetchdata = "select * from KomponenPenilaian WHERE matkul='"+matkul+"'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);
        ////(int id, String nama, String email)
        if (cursor.moveToFirst()) {
            Log.d("cursor dosen", "tidak null");
            do {
                KomponenPenilaian komponen = new KomponenPenilaian(cursor.getString(0), cursor.getString(1),cursor.getInt(2),cursor.getInt(3));
                listKomponen.add(komponen);
            } while (cursor.moveToNext());
        }

        for(KomponenPenilaian komponenpenilaian : listKomponen){
            listKomponenMatkul.add(komponenpenilaian.getNilai());
        }
        return listKomponenMatkul;
    }

    public ArrayList<Integer> getBobotKomponenFromMatkul(String matkul) {
        ArrayList<KomponenPenilaian> listKomponen = new ArrayList<KomponenPenilaian>();
        ArrayList<Integer> listKomponenMatkul = new ArrayList <Integer>();
        String fetchdata = "select * from KomponenPenilaian WHERE matkul='"+matkul+"'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);
        ////(int id, String nama, String email)
        if (cursor.moveToFirst()) {
            Log.d("cursor dosen", "tidak null");
            do {
                KomponenPenilaian komponen = new KomponenPenilaian(cursor.getString(0), cursor.getString(1),cursor.getInt(2),cursor.getInt(3));
                listKomponen.add(komponen);
            } while (cursor.moveToNext());
        }

        for(KomponenPenilaian komponenpenilaian : listKomponen){
            listKomponenMatkul.add(komponenpenilaian.getBobot());
        }
        return listKomponenMatkul;
    }

    //---------------------------------------------------------------------------------///

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

}
