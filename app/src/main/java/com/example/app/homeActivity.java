package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class homeActivity extends AppCompatActivity {
    SharedPreferences prf;
    Intent intent,intent1;
    TextView ins,ins1;
    DbForm2 db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DbForm2(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView result = (TextView)findViewById(R.id.resultView);
        ins = (TextView)findViewById(R.id.formins) ;
        ins1 = (TextView)findViewById(R.id.formins1) ;

        Button form = (Button)findViewById(R.id.form);
        Button btnLogOut = (Button)findViewById(R.id.btnLogOut);
        prf = getSharedPreferences("user_details",MODE_PRIVATE);
        intent = new Intent(homeActivity.this,MainActivity.class);
        result.setText("Hello, "+prf.getString("username",null));
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prf.edit();
                editor.clear();
                editor.commit();
                startActivity(intent);
            }
        });
        String user = prf.getString("username",null);
        Boolean res = db.checkUser(user);
        if(res==true){
            form.setVisibility(View.VISIBLE);
            ins.setVisibility(View.VISIBLE);
            ins1.setVisibility(View.INVISIBLE);
            form.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent1 = new Intent(homeActivity.this,gpay.class);
                    startActivity(intent1);
                }
            });
        }
        else {
            ins1.setVisibility(View.VISIBLE);
            ins.setVisibility(View.INVISIBLE);
            form.setVisibility(View.INVISIBLE);
        }

    }
}