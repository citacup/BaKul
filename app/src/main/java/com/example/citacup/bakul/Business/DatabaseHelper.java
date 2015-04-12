package com.example.citacup.bakul.Business;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.citacup.bakul.Entities.Dosen;
import com.example.citacup.bakul.Entities.Kategori;
import com.example.citacup.bakul.Entities.MataKuliah;
import com.example.citacup.bakul.Entities.Review;

import java.util.ArrayList;

/**
 * Created by SAMSUNG NB on 4/11/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private String FILENAME = "DBBakul.db";

    public DatabaseHelper(Context context) {
        super(context, "FILENAME", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE `Dosen` (`iddosen` TEXT, `nama` TEXT, `email` TEXT); ";
        db.execSQL(query);
        query = "CREATE TABLE `Matakuliah` (`kodemk` TEXT, `nama` TEXT, `sks` TEXT, `semester` TEXT, `islulus` TEXT,`deskripsi` TEXT,`referensi` TEXT, `objektif` TEXT,`kategori` TEXT); ";
        db.execSQL(query);
        query = "CREATE TABLE `Kategori` (`kategori` TEXT); ";
        db.execSQL(query);
        query = "CREATE TABLE `Review` (`idrev` TEXT,`username` TEXT,`nama` TEXT,`komentar` TEXT,`app_flag` TEXT,`like` TEXT,`dislike` TEXT); ";
        db.execSQL(query);
    }

    public void insertDosen (String iddosen, String nama, String email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("iddosen", iddosen);
        values.put("nama", nama);
        values.put("email",email);
        sqLiteDatabase.insert("Dosen", null, values);
    }

    public void insertMatakuliah (String kodemk, String nama, String sks, String semester, String islulus, String deskripsi, String referensi, String objektif, String kategori ) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("kodemk", kodemk);
        values.put("nama", nama);
        values.put("sks",sks);
        values.put("semester",semester);
        values.put("islulus",islulus);
        values.put("deskripsi",deskripsi);
        values.put("referensi",referensi);
        values.put("objektif",objektif);
        values.put("kategori",kategori);
        sqLiteDatabase.insert("Matakuliah", null, values);
    }

    public void insertKategori (String kategori) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("kategori", kategori);
        sqLiteDatabase.insert("Kategori", null, values);
    }

    public void insertReview (String idrev, String username, String nama, String komentar, String app_flag, String like, String dislike) {
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

    public ArrayList<String> getAllDosen() {
        ArrayList<Dosen> listDosen = new ArrayList<Dosen>();
        ArrayList<String> listNamaDosen = new ArrayList <String>();
        String fetchdata = "select * from Dosen";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);
        ////(int id, String nama, String email)
        if (cursor.moveToFirst()) {
            Log.d("cursor dosen", "tidak null");
            do {
                Dosen dosen = new Dosen(cursor.getString(0), cursor.getString(1),cursor.getString(2));
                listDosen.add(dosen);
            } while (cursor.moveToNext());
        }

        for(Dosen dosen : listDosen){
            listNamaDosen.add(dosen.getNama());
        }
        return listNamaDosen;
    }




    public Dosen getDosenFromID (int id) {
        Dosen dosen = null;
        String query = "SELECT * from Dosen where id = " + id;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            dosen = new Dosen(cursor.getString(0), cursor.getString(1),cursor.getString(2));
        }
        return dosen;
    }

    public Dosen getDosenFromNama (String nama) {
        Dosen dosen = null;
        String query = "SELECT * from Dosen where nama = "+"'" + nama+"'" ;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            dosen = new Dosen(cursor.getString(0), cursor.getString(1),cursor.getString(2));
        }
        return dosen;
    }

  //////////////////////////////////////////BUAT MATAKULIAH!!!!!!!!!!!!!!!!!!
  public ArrayList<String> getAllMatakuliah() {
      ArrayList<MataKuliah> listMatakuliah = new ArrayList<MataKuliah>();
      ArrayList<String> listNamaMatakuliah = new ArrayList <String>();
      String fetchdata = "select * from Matakuliah";
      SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
      Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);
      ////(int id, String nama, String email)
      if (cursor.moveToFirst()) {
          Log.d("cursor dosen", "tidak null");
          do {
              MataKuliah matakuliah = new MataKuliah(cursor.getString(0), cursor.getString(1),
                      cursor.getString(2),cursor.getString(3),cursor.getString(4),
                      cursor.getString(5),cursor.getString(6),cursor.getString(7),
                      cursor.getString(8));
              listMatakuliah.add(matakuliah);
          } while (cursor.moveToNext());
      }

      for(MataKuliah matakuliah : listMatakuliah){
          listNamaMatakuliah.add(matakuliah.getNama());
      }
      return listNamaMatakuliah;
  }

    public MataKuliah getMatakuliahFromNama (String nama) {
        MataKuliah matakuliah = null;
        String query = "SELECT * from Matakuliah where nama = "+"'" + nama+"'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            matakuliah = new MataKuliah(cursor.getString(0), cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
        }
        return matakuliah;
    }

    public String getSemesterMatkulFromID (int id) {
        MataKuliah matakuliah = null;
        String query = "SELECT semester from Matakuliah where kodemk = " + id;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String semesterMatkul="";
        if (cursor.moveToFirst()) {
         semesterMatkul = cursor.getString(3);
        }

        return semesterMatkul;
    }
    public String getNamaMatkulFromID (int id) {
        MataKuliah matakuliah = null;
        String query = "SELECT nama from Matakuliah where kodemk = " + id;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String namaMatkul="";
        if (cursor.moveToFirst()) {
            namaMatkul = cursor.getString(1);
        }

        return namaMatkul;
    }

    public String getSksMatkulFromID (int id) {
        MataKuliah matakuliah = null;
        String query = "SELECT sks from Matakuliah where kodemk = " + id;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String sksMatkul="";
        if (cursor.moveToFirst()) {
            sksMatkul = cursor.getString(2);
        }

        return sksMatkul;
    }
    public String getDeskripsiMatkulFromID (int id) {
        MataKuliah matakuliah = null;
        String query = "SELECT deskripsi from Matakuliah where kodemk = " + id;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String deskripsiMatkul="";
        if (cursor.moveToFirst()) {
            deskripsiMatkul = cursor.getString(5);
        }

        return deskripsiMatkul;
    }

    public String getReferensiMatkulFromID (int id) {
        MataKuliah matakuliah = null;
        String query = "SELECT referensi from Matakuliah where kodemk = " + id;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String referensiMatkul="";
        if (cursor.moveToFirst()) {
            referensiMatkul = cursor.getString(6);
        }

        return referensiMatkul;
    }

    public String getObjektifMatkulFromID (int id) {
        MataKuliah matakuliah = null;
        String query = "SELECT objektif from Matakuliah where kodemk = " + id;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String objektifMatkul="";
        if (cursor.moveToFirst()) {
            objektifMatkul = cursor.getString(7);
        }

        return objektifMatkul;
    }

    public String getKategoriMatkulFromID (int id) {
        MataKuliah matakuliah = null;
        String query = "SELECT kategori from Matakuliah where kodemk = " + id;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String kategoriMatkul="";
        if (cursor.moveToFirst()) {
            kategoriMatkul = cursor.getString(8);
        }

        return kategoriMatkul;
    }
    //--------------------------------------------------------------------------------------///

    ////////////////////////////////////KATEGORI



    public ArrayList<String> getAllKategori() {
        ArrayList<Kategori> listKategori = new ArrayList<Kategori>();
        ArrayList<String> listNamaKategori = new ArrayList <String>();
        String fetchdata = "select * from Kategori";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);
        ////(int id, String nama, String email)
        if (cursor.moveToFirst()) {
            Log.d("cursor dosen", "tidak null");
            do {
                Kategori kategori = new Kategori(cursor.getString(0));
                listKategori.add(kategori);
            } while (cursor.moveToNext());
        }

        for(Kategori kategori : listKategori){
            listNamaKategori.add(kategori.getKategori());
        }
        return listNamaKategori;
    }

    /////////////////////////////------------------------------------////////////////
////////////////////////////////////REVIEW////////////////////////

    public ArrayList<String> getAllReview() {
        ArrayList<Review> listReview = new ArrayList<Review>();
        ArrayList<String> listReviewMatkul = new ArrayList <String>();
        String fetchdata = "select * from Review";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);
        ////(int id, String nama, String email)
        if (cursor.moveToFirst()) {
            Log.d("cursor dosen", "tidak null");
            do {
                Review review = new Review(cursor.getString(0), cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
                listReview.add(review);
            } while (cursor.moveToNext());
        }

        for(Review review : listReview){
            listReviewMatkul.add(review.getNama());
        }
        return listReviewMatkul;
    }

    public ArrayList<String> getReviewFromNama(String nama) {
        ArrayList<Review> listReview2 = new ArrayList<Review>();
        ArrayList<String> listReviewMatkul2 = new ArrayList <String>();
        String query = "SELECT * from Review where nama = "+"'" + nama+"'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        ////(int id, String nama, String email)
        if (cursor.moveToFirst()) {
            Log.d("cursor dosen", "tidak null");
            do {
                Review review2 = new Review(cursor.getString(0), cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
                listReview2.add(review2);
            } while (cursor.moveToNext());
        }

        for(Review review : listReview2){
            listReviewMatkul2.add(review.getKomentar());
        }
        return listReviewMatkul2;
    }


    //---------------------------------------------------------------------------------///

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

}
