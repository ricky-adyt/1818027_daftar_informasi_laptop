package com.example.daftar_informasi_laptop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_laptop";
    private static final String tb_jenis_laptop = "tb_jenis_laptop";
    private static final String tb_jenis_id = "id";
    private static final String tb_jenis_image = "image";
    private static final String tb_jenis_nama = "nama";
    private static final String tb_jenis_tipe = "tipe";
    private static final String tb_jenis_warna = "warna";
    private static final String tb_jenis_harga = "harga";
    private static final String CREATE_TABLE_JENIS_LAPTOP = "CREATE TABLE " +
            tb_jenis_laptop + "("
            + tb_jenis_id + " INTEGER PRIMARY KEY ,"
            + tb_jenis_image + " BLOB,"
            + tb_jenis_nama + " TEXT,"
            + tb_jenis_tipe + " TEXT,"
            + tb_jenis_warna + " TEXT,"
            + tb_jenis_harga + " TEXT " + ")";

    public MyDatabase (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_JENIS_LAPTOP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void CreateLaptop (Laptop mdNotif) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_jenis_id, mdNotif.get_id());
        values.put(tb_jenis_image, mdNotif.get_image());
        values.put(tb_jenis_nama, mdNotif.get_nama());
        values.put(tb_jenis_tipe, mdNotif.get_tipe());
        values.put(tb_jenis_warna, mdNotif.get_warna());
        values.put(tb_jenis_harga, mdNotif.get_harga());
        db.insert(tb_jenis_laptop, null, values);
        db.close();
    }

    public List<Laptop> ReadLaptop() {
        List<Laptop> judulModelList = new ArrayList<Laptop>();
        String selectQuery = "SELECT * FROM " + tb_jenis_laptop;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Laptop mdKontak = new Laptop();
                mdKontak.set_id(cursor.getString(0));
                mdKontak.set_image(cursor.getBlob(1));
                mdKontak.set_nama(cursor.getString(2));
                mdKontak.set_tipe(cursor.getString(3));
                mdKontak.set_warna(cursor.getString(4));
                mdKontak.set_harga(cursor.getString(5));
                judulModelList.add(mdKontak);
            } while (cursor.moveToNext());
        }
        db.close();
        return judulModelList;
    }
    public int UpdateLaptop (Laptop mdNotif) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_jenis_image, mdNotif.get_image());
        values.put(tb_jenis_nama, mdNotif.get_nama());
        values.put(tb_jenis_tipe, mdNotif.get_tipe());
        values.put(tb_jenis_warna, mdNotif.get_warna());
        values.put(tb_jenis_harga, mdNotif.get_harga());
        return db.update(tb_jenis_laptop, values, tb_jenis_id + " = ?",
                new String[] { String.valueOf(mdNotif.get_id())});
    }
    public void DeleteLaptop (Laptop mdNotif) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_jenis_laptop, tb_jenis_id+ " = ?",
                new String[]{String.valueOf(mdNotif.get_id())});
        db.close();
    }
}


