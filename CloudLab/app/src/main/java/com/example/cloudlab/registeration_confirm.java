package com.example.cloudlab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import pl.droidsonroids.gif.GifImageView;

public class registeration_confirm extends AppCompatActivity {

    TextView name,rolllno;
    String s_name;
    Button wapp,home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration_confirm);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        name=findViewById(R.id.textView4);
        wapp=findViewById(R.id.whatsapp);
        home = findViewById(R.id.backmain);


        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
             s_name = "Hello "+bundle.getString("s_name")+", we’re grateful for your support";
            name.setText(s_name);
        }


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registeration_confirm.this,Mainpage.class);
                startActivity(intent);
            }
        });


        wapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://chat.whatsapp.com/GrakdjYdAJaJoFvsdpHJ7h");
            }
            private void gotoUrl(String s) {
                Uri uri =Uri.parse(s);
                Intent launch=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(launch);

            }

        });
    }
}