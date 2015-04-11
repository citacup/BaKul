package com.example.citacup.bakul.Business;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.citacup.bakul.Entities.Dosen;

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
    }

    public void insertDosen (String iddosen, String nama, String email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("iddosen", iddosen);
        values.put("nama", nama);
        values.put("email",email);
        sqLiteDatabase.insert("Dosen", null, values);
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
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

}
