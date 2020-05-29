package com.example.tugasdatabasesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context){

        super(context, "db_mhs",null,1 );
    }

    private ArrayList<Mahasiswa> arrayList = new ArrayList<>();
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table tb_mhs(stb text(50) primary key, nama text(50),angkatan text(11), alamat text(50))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(SQLiteDatabase db,Mahasiswa mhs){
        ContentValues cv = new ContentValues();
        cv.put("stb",mhs.getStb());
        cv.put("nama",mhs.getNama());
        cv.put("angkatan",mhs.getAngkatan());
        cv.put("alamat",mhs.getAlamat());
        db.insert("tb_mhs", null,cv);
        db.close();
    }

    public ArrayList<Mahasiswa> getArrayList(SQLiteDatabase db){
        arrayList.clear();
        Cursor cursor = db.rawQuery("select * from tb_mhs",null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                arrayList.add(new Mahasiswa(cursor.getString(0),cursor.getString(1),cursor.getInt(2),
                        cursor.getString(3)));
            }while (cursor.moveToNext());
        }
    return arrayList;
    }

    public  void hapusData(SQLiteDatabase db, String sb){
        db.delete("tb_mhs","stb=?",new String[]{sb});
    }

    public  void updateDate(SQLiteDatabase db, Mahasiswa mhs, String sb){
        ContentValues cv = new ContentValues();
        cv.put("stb",mhs.getStb());
        cv.put("nama",mhs.getNama());
        cv.put("angkatan",mhs.getAngkatan());
        cv.put("alamat",mhs.getAlamat());
        db.update("tb_mhs", cv ,"stb=?", new String[]{sb});

    }
}
