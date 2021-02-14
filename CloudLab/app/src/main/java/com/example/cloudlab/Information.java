package com.example.cloudlab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Information extends AppCompatActivity {
    ImageView km,kl,kc,rm,rl,rc,vm,vl,vc,iv1,iv2,iv3;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);
        ImageView f = findViewById(R.id.imageView1);
        km = findViewById(R.id.imageView19);
        kl = findViewById(R.id.imageView20);
        kc = findViewById(R.id.imageView18);
        rm = findViewById(R.id.i9);
        rl = findViewById(R.id.i0);
        rc = findViewById(R.id.i8);
        vm = findViewById(R.id.i19);
        vl = findViewById(R.id.i20);
        vc = findViewById(R.id.i18);

        kl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkedIn("https://www.linkedin.com/in/kishore-durai-7932321a4/");
            }
        });
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkedIn("https://www.linkedin.com/in/rameshraja-k-5309921b0/");
            }
        });
        vl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkedIn("https://www.linkedin.com/in/vishwajith-n-m-5304b01a4/");
            }
        });
        km.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail("kishore.ct19@bitsathy.ac.in");
            }
        });
        rm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail("rameshraja.ct19@bitsathy.ac.in");
            }
        });
        vm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail("vishwajith.ig19@bitsathy.ac.in");
            }
        });
        kc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone("9787688154");
            }
        });
        rc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone("7010394960");
            }
        });
        vc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone("7339656952");
            }
        });
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        ConstraintLayout.LayoutParams i1 = (ConstraintLayout.LayoutParams) iv1.getLayoutParams();
        ConstraintLayout.LayoutParams i2 = (ConstraintLayout.LayoutParams) iv2.getLayoutParams();
        ConstraintLayout.LayoutParams i3 = (ConstraintLayout.LayoutParams) iv3.getLayoutParams();

        i1.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.24);
        i2.width = i1.width;
        i3.width = i1.width;
        i1.height = (int) (getResources().getDisplayMetrics().widthPixels * 0.24);
        i2.height = i1.height;
        i3.height = i1.height;
    }
    public void linkedIn (String l){
        Uri link = Uri.parse(l);
        Intent intent = new Intent(Intent.ACTION_VIEW,link);
        startActivity(intent);
    }
    public void mail(String m){
        Intent mail = new Intent(Intent.ACTION_SEND);
        mail.setData(Uri.parse("mailto:abc@gmail.com"));
        mail.setType("text/plain");
        mail.putExtra(Intent.EXTRA_EMAIL, new String[] {m});
        mail.setPackage("com.google.android.gm");
        startActivity(mail);
    }
    public void phone(String n){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+n));
        startActivity(intent);
    }
}