package com.example.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DbForm2 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="form2.db";
    public static final String TABLE_P="preference";
    public static final String COL_1="ID";
    public static final String COL_2="user";
    public static final String COL_3="p1";
    public static final String COL_4="p2";
    public static final String COL_5="p3";


    public DbForm2(Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE preference(ID INTEGER PRIMARY KEY AUTOINCREMENT ,user TEXT,p1 TEXT,p2 TEXT,p3 TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_P);
        onCreate(db);
    }

    public long addDetails(String user,String p1,String p2,String p3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("user",user);
        cv.put("p1",p1);
        cv.put("p2",p2);
        cv.put("p3",p3);
        long res = db.insert("preference",null,cv);
        return res;
    }
    public boolean checkUser(String username){
        String[] columns = {COL_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" ;
        String[] selectionArgs  = {username};
        Cursor cursor = db.query(TABLE_P,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        if (count>0)
            return false;
        else
            return true;
    }
    public void deleteData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+TABLE_P);
    }
    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT user,p1,p2,p3 FROM "+ TABLE_P;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("user",cursor.getString(cursor.getColumnIndex(COL_2)));
            user.put("p1",cursor.getString(cursor.getColumnIndex(COL_3)));
            user.put("p2",cursor.getString(cursor.getColumnIndex(COL_4)));
            user.put("p3",cursor.getString(cursor.getColumnIndex(COL_5)));
            userList.add(user);
        }
        return  userList;
    }

}
