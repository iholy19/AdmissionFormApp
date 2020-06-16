package com.example.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;

public class DatabaseForm extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="form.db";
    public static final String TABLE_P="personal";
    public static final String TABLE_E="education";
    public static final String C_0="id";
    public static final String C1 = "name";
    public static final String C_1 = "user" ;
    public static final String C_2 = "dob" ;
    public static final String C_3 = "fname";
    public static final String C_4 = "mname";
    public static final String C_5 = "address";
    public static final String C_6 = "city";
    public static final String C_7 = "state";
    public static final String C_8 = "pincode";
    public static final String C_9 = "lang";
    public static final String C_11 = "nationality";
    public static final String C_12 = "mobile";
    public static final String C_13 = "email";

    public DatabaseForm(Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE personal(ID INTEGER PRIMARY KEY AUTOINCREMENT ,user TEXT,name TEXT,dob TEXT, fname TEXT,mname TEXT,address TEXT,city TEXT,state TEXT,pincode TEXT,lang TEXT,nationality TEXT,mobile TEXT,email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_P);
    }

    public long addPersonalDetails(String user,String name, String d,String f,String m,String add, String city, String state, String pin, String lang,String nat,String mob, String em ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("user",user);
        contentvalues.put("name",name);
        contentvalues.put("dob",d);
        contentvalues.put("fname",f);
        contentvalues.put("mname",m);
        contentvalues.put("address",add);
        contentvalues.put("city",city);
        contentvalues.put("state",state);
        contentvalues.put("pincode",pin);
        contentvalues.put("lang",lang);
        contentvalues.put("nationality",nat);
        contentvalues.put("mobile",mob);
        contentvalues.put("email",em);
        long res = db.insert("personal",null,contentvalues);
        return res;
    }
    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from "+TABLE_P;
        Cursor cr = db.rawQuery(query,null);
        return cr;
    }
    public void deleteData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+TABLE_P);
    }
    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, dob FROM "+ TABLE_P;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(C1)));
            user.put("dob",cursor.getString(cursor.getColumnIndex(C_2)));
            userList.add(user);
        }
        return  userList;
    }

}
