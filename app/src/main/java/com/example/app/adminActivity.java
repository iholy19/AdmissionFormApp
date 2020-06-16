package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class adminActivity extends AppCompatActivity {
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    DatabaseForm db;
    DbForm1 db1;
    DbForm2 db2;
    DatabaseHelper db3;
    ListView lv;
    Button b,b1,b3,b2;
    Intent intent1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        db = new DatabaseForm(this);
        db1 = new DbForm1(this);
        db2 = new DbForm2(this);
        db3 = new DatabaseHelper(this);
        listItem = new ArrayList<>();
        lv = findViewById(R.id.userList);
        b = (Button)findViewById(R.id.del);
        b1 = (Button)findViewById(R.id.del1);
        b3 = (Button)findViewById(R.id.pref);
        b2 = (Button)findViewById(R.id.logout);
        GetUsers();
//        viewData();
//
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                String text = lv.getItemAtPosition(i).toString();
                Toast.makeText(adminActivity.this,""+text,Toast.LENGTH_SHORT).show();
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteData();
                db1.deleteData();
                db2.deleteData();
                listItem.clear();
                GetUsers();
                Toast.makeText(adminActivity.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db3.deleteData();
                Toast.makeText(adminActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent1 = new Intent(adminActivity.this,admin1.class);
                startActivity(intent1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent1 = new Intent(adminActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });
   }

    private void GetUsers() {
        ArrayList<HashMap<String, String>> userList1 = db.GetUsers();
        ListAdapter adapter = new SimpleAdapter(adminActivity.this, userList1, R.layout.list_row,new String[]{"name","dob"}, new int[]{R.id.name, R.id.p1});
        lv.setAdapter(adapter);
    }
//
//    private void viewData() {
//        Cursor cr = db.viewData();
//        if(cr.getCount()==0){
//            Toast.makeText(adminActivity.this,"No data to show",Toast.LENGTH_SHORT).show();
//        }
//        else {
//            while(cr.moveToNext()){
//                listItem.add(cr.getString(1));
//            }
//            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItem);
//            userList.setAdapter(adapter);
//        }
//    }
}
