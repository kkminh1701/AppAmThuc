package com.example.pc.appamthuc;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PC on 11/23/2017.
 */

public class MyDatabase extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "Database.db";
    private static final String TABLE_NAME = "SinhVien";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "TIEUDE";
    private static final String COL_3 = "NOIDUNG";


    public MyDatabase(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME + "(" + COL_1 + "INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + "TEXT, " +COL_3 + "TEXT)" );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IN EXISTS" + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String tieude, String noidung){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2,tieude);
        values.put(COL_3, noidung);
        long result = db.insert(TABLE_NAME, null,values);
        if(result == -1 ){
            return false;
        }
        else return true;
    }

    public boolean updateData(String id, String tieude, String noidung){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1, id);
        values.put(COL_2,tieude);
        values.put(COL_3, noidung);
        long result = db.update(TABLE_NAME, values, COL_1 + "=?",new String[]{id});
        if(result == -1 ){
            return false;
        }
        else return true;
    }

    public boolean deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,COL_1 + "=?",new String[]{id});
        if(result == -1 ){
            return false;
        }
        else return true;
    }
}
