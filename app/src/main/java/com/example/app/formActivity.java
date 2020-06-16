package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

public class formActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText n,dob,fn,mn,add,city,st,pin,lang,nat,mob,email;
    Button next;
    DatabaseForm db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        n = (EditText)findViewById(R.id.name);
        dob = (EditText)findViewById(R.id.dob);
        fn = (EditText)findViewById(R.id.fname);
        mn = (EditText)findViewById(R.id.mname);
        add = (EditText)findViewById(R.id.address);
        city = (EditText)findViewById(R.id.city);
        st = (EditText)findViewById(R.id.state);
        pin = (EditText)findViewById(R.id.pincode);
        lang = (EditText)findViewById(R.id.lang);
        nat = (EditText)findViewById(R.id.nat);
        mob = (EditText)findViewById(R.id.mobile);
        email = (EditText)findViewById(R.id.email);
        Spinner sp = findViewById(R.id.gend);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.gender,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);
        next = (Button)findViewById(R.id.next);
        db = new DatabaseForm(this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("user_details",MODE_PRIVATE);
                String uname = pref.getString("username",null);
                String name = n.getText().toString().trim();
                String date = dob.getText().toString().trim();
                String fname = fn.getText().toString().trim();
                String mname = mn.getText().toString().trim();
                String address = add.getText().toString().trim();
                String cit = city.getText().toString().trim();
                String state = st.getText().toString().trim();
                String pincode = pin.getText().toString().trim();
                String language = lang.getText().toString().trim();
                String nationality = nat.getText().toString().trim();
                String mobile = mob.getText().toString().trim();
                String emai = email.getText().toString().trim();
                if(fname.equals("") || name.equals("") || date.equals("") || mname.equals("") || address.equals("") || cit.equals("") || state.equals("") || language.equals("") || pincode.equals("") || mobile.equals("") || emai.equals("")){
                    Toast.makeText(formActivity.this, "Invalid field", Toast.LENGTH_SHORT).show();
                }
                else {
                    long val = db.addPersonalDetails(uname,name,date,fname,mname,address,cit,state,pincode,language,nationality,mobile,emai);
                    if(val>0){
                        Toast.makeText(formActivity.this, "Fill this form", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(formActivity.this, EduActivity.class);
                        startActivity(moveToLogin);
                    }
                }

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String gen = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
