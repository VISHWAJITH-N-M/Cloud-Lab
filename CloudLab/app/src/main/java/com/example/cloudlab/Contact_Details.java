package com.example.cloudlab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Contact_Details extends AppCompatActivity {

    ImageButton Call,Linedin,mail,Call1,Linedin1,mail1;
    ImageView header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__details);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Call=findViewById(R.id.call);
        Call1=findViewById(R.id.call1);
        Linedin=findViewById(R.id.linked);
        mail = findViewById(R.id.Gmail);
        mail1 = findViewById(R.id.Gmail1);
        header = findViewById(R.id.imageView5);
        ConstraintLayout.LayoutParams h = (ConstraintLayout.LayoutParams) header.getLayoutParams();
        h.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.34);

        Linedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.linkedin.com/in/nataraj-n-6593b35b/");
            }

            private void gotoUrl(String s) {
                Uri uri =Uri.parse(s);
                Intent launch=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(launch);

            }

                    });
        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9715114848"));
                startActivity(intent);
            }
        });
        Call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9952641869"));
                startActivity(intent);
            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmailButton("nataraj@bitsathy.ac.in");
            }
        });
        mail1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmailButton("poornimaa@bitsathy.ac.in");
            }
        });
        Call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9715114848"));
                startActivity(intent);
            }
        });

    }
    public void EmailButton(String e){
        Intent sendintent=new Intent(Intent.ACTION_SEND);
        sendintent.setData(Uri.parse("mailto:abc@gmail.com"));
        sendintent.setType("text/plain");
        sendintent.putExtra(Intent.EXTRA_EMAIL, new String[] {e});
        sendintent.setPackage("com.google.android.gm");
        startActivity(sendintent);
    }
}