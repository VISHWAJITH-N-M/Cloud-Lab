 package com.example.cloudlab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class odproof_response extends AppCompatActivity {
    TextView id;
    String act_id;
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odproof_response);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        id=findViewById(R.id.textView39);
        home = findViewById(R.id.backmain2);
        act_id = getIntent().getExtras().getString("value");
        id.setText(act_id.toUpperCase());
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(odproof_response.this,Mainpage.class);
                startActivity(intent);
            }
        });
    }
    }
