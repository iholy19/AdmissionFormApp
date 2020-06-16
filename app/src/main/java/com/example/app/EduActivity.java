package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EduActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8,ee1,ee2,ee3,ee4,ee5,ee6,ee7,ee8;
    Button next;
    DbForm1 db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu);
        e1 = (EditText)findViewById(R.id.school);
        e2 = (EditText)findViewById(R.id.place);
        e3 = (EditText)findViewById(R.id.reg);
        e4 = (EditText)findViewById(R.id.mark);
        e5 = (EditText)findViewById(R.id.total);
        e6 = (EditText)findViewById(R.id.perc);
        e7 = (EditText)findViewById(R.id.board);
        e8 = (EditText)findViewById(R.id.passed);
        ee1 = (EditText)findViewById(R.id.school1);
        ee2 = (EditText)findViewById(R.id.place1);
        ee3 = (EditText)findViewById(R.id.reg1);
        ee4 = (EditText)findViewById(R.id.mark1);
        ee5 = (EditText)findViewById(R.id.total1);
        ee6 = (EditText)findViewById(R.id.perc1);
        ee7 = (EditText)findViewById(R.id.board1);
        ee8 = (EditText)findViewById(R.id.passed1);
        next = (Button)findViewById(R.id.next);
        db = new DbForm1(this);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("user_details",MODE_PRIVATE);
                String uname = pref.getString("username",null);
                String school = e1.getText().toString().trim();
                String place = e2.getText().toString().trim();
                String reg = e3.getText().toString().trim();
                String marks = e4.getText().toString().trim();
                String total = e5.getText().toString().trim();
                String percentage = e6.getText().toString().trim();
                String board = e7.getText().toString().trim();
                String passed = e8.getText().toString().trim();
                String school1 = ee1.getText().toString().trim();
                String place1 = ee2.getText().toString().trim();
                String reg1 = ee3.getText().toString().trim();
                String marks1 = ee4.getText().toString().trim();
                String total1 = ee5.getText().toString().trim();
                String percentage1 = ee6.getText().toString().trim();
                String board1 = ee7.getText().toString().trim();
                String passed1 = ee8.getText().toString().trim();
                if(uname.equals("") || school.equals("") || place.equals("") || reg.equals("") || marks.equals("") || total.equals("") || percentage.equals("") || board.equals("") || passed.equals("") || school1.equals("") || place1.equals("") || reg1.equals("") || marks1.equals("") || total1.equals("") || percentage1.equals("") || passed1.equals("") || board1.equals("")){
                    Toast.makeText(EduActivity.this, "Invalid field", Toast.LENGTH_SHORT).show();
                }
                else {
                    long val = db.addEducation(uname,school,place,reg,marks,total,percentage,board,passed,school1,place1,reg1,marks1,total1,percentage1,board1,passed1);
                    if(val>0){
                        Toast.makeText(EduActivity.this, "Fill this form", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(EduActivity.this, PrefActivity.class);
                        startActivity(moveToLogin);
                    }
                }
            }
        });
    }
}
