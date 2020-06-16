package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class admin1 extends AppCompatActivity {
    DbForm2 db;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin1);
        db = new DbForm2(this);
        lv = findViewById(R.id.userList);
        GetUsers();
    }
    private void GetUsers() {
        ArrayList<HashMap<String, String>> userList1 = db.GetUsers();
        ListAdapter adapter = new SimpleAdapter(admin1.this, userList1, R.layout.list_row,new String[]{"user","p1","p2","p3"}, new int[]{R.id.name, R.id.p1,R.id.p2,R.id.p3});
        lv.setAdapter(adapter);
    }
}
