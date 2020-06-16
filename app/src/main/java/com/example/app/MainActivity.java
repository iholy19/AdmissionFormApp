package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText user;
    EditText pass;
    Button login;
    TextView register;
    DatabaseHelper db;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        user = (EditText)findViewById(R.id.uname);
        pass = (EditText)findViewById(R.id.pword);
        login = (Button)findViewById(R.id.login);
        register = (TextView)findViewById(R.id.reg);
        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString().trim();
                String pwd = pass.getText().toString().trim();
                if(username.equals("admin") && pwd.equals("admin")){
                    Toast.makeText(MainActivity.this,"Successfully logged in",Toast.LENGTH_SHORT).show();
                    Intent adminIntent = new Intent(MainActivity.this,adminActivity.class);
                    startActivity(adminIntent);
                }
                else {
                    Boolean res = db.checkUserPass(username,pwd);
                    if (res==true){
                        Toast.makeText(MainActivity.this,"Successfully logged in",Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("username",username);
                        editor.commit();
                        Intent homeIntent = new Intent(MainActivity.this,homeActivity.class);
                        startActivity(homeIntent);
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Login Error",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
