package com.example.shenghuotong;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.shenghuotong.data.data;

import java.util.ArrayList;

public class MyHelper   extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    public MyHelper(@Nullable Context context) {
        super(context, "clx.db", null, 1);
        db=getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE info(name varchar(20),password integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void add(String name,String password){
        db.execSQL("INSERT INTO info (name,password) VALUES(?,?)",new Object[]{name,password});
    }
    public void delete(String name,String password){
        db.execSQL("DELETE FROM info WHERE name = AND password ="+name+password);
    }
    public void updata(String password){
        db.execSQL("UPDATE info SET password = ?",new Object[]{password});
    }
    public ArrayList<data> getAllData(){

        ArrayList<data> list = new ArrayList<data>();
        Cursor cursor = db.query("info",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new data(name,password));
        }
        return list;
    }
}
