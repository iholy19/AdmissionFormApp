package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PrefActivity extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b1;
    DbForm2 db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref);
        e1 = (EditText)findViewById(R.id.p1);
        e2 = (EditText)findViewById(R.id.p2);
        e3 = (EditText)findViewById(R.id.p3);
        b1 = (Button)findViewById(R.id.submit);
        db = new DbForm2(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("user_details",MODE_PRIVATE);
                String user = pref.getString("username",null);
                String p1 = e1.getText().toString().trim();
                String p2 = e2.getText().toString().trim();
                String p3 = e3.getText().toString().trim();
                if(user.equals("") || p1.equals("") || p2.equals("") || p3.equals("")){
                    Toast.makeText(PrefActivity.this, "Invalid Field", Toast.LENGTH_SHORT).show();
                }else {
                    long val = db.addDetails(user,p1,p2,p3);
                    if(val>0){
                        Toast.makeText(PrefActivity.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(PrefActivity.this, homeActivity.class);
                        startActivity(moveToLogin);
                    }
                }
            }
        });

    }
}
