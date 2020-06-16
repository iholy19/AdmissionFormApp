package com.example.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbForm1 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="form1.db";
    public static final String TABLE_E="education";


    public DbForm1(Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE education(ID INTEGER PRIMARY KEY AUTOINCREMENT ,user TEXT,school TEXT,place TEXT,register TEXT,marks TEXT,total TEXT,percentage TEXT,board TEXT,passed TEXT,school1 TEXT,place1 TEXT,register1 TEXT,marks1 TEXT,total1 TEXT,percentage1 TEXT,board1 TEXT,passed1 TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_E);
        onCreate(db);
    }

    public long addEducation(String user,String school1,String place1,String reg1,String marks1,String total1,String percentage1,String board1,String passed1,String school,String place,String reg,String marks,String total,String percentage,String board,String passed){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("user",user);
        cv.put("school",school1);
        cv.put("place",place1);
        cv.put("register",reg1);
        cv.put("marks",place1);
        cv.put("total",total1);
        cv.put("percentage",percentage1);
        cv.put("board",board1);
        cv.put("passed",passed1);
        cv.put("school1",school);
        cv.put("place1",place);
        cv.put("register1",reg);
        cv.put("marks1",place);
        cv.put("total1",total);
        cv.put("percentage1",percentage);
        cv.put("board1",board);
        cv.put("passed1",passed);
        long res = db.insert("education",null,cv);
        return res;
    }
    public void deleteData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+TABLE_E);
    }
}
