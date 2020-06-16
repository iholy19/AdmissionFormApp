package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText user;
    EditText pass;
    EditText confPass;
    Button register;
    TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        user = (EditText)findViewById(R.id.uname);
        pass = (EditText)findViewById(R.id.pword);
        confPass = (EditText)findViewById(R.id.conf_pword) ;
        login = (TextView) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(loginIntent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString().trim();
                String pass1 = pass.getText().toString().trim();
                String pass2 = confPass.getText().toString().trim();
                if(username.isEmpty()==false || pass1.isEmpty()==false || pass2.isEmpty()==false)
                {
                    if (pass1.equals(pass2)) {
                        Boolean res = db.checkUser(username);
                        if(res==true){
                            long val = db.addUser(username, pass1);
                            if (val > 0) {
                                Toast.makeText(RegisterActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                Intent moveToLogin = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(moveToLogin);

                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration error!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Username already exists!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Empty string!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
