package com.example.cloudlab;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.List;


public class Mainpage extends AppCompatActivity {
    ImageSlider dash,events;
    ImageView image1,image2;
    TextView ab,fb,wb,ct,set;
    public Button join,od,rp,bit,settings,feedback,contact,web;
    List<String> slider_links = new ArrayList<>();
    List<String> slider2_links = new ArrayList<>();
    List<Bitmap> slider = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        dash = findViewById(R.id.dash);
        od = findViewById(R.id.onduty);
        bit = findViewById(R.id.lab);
        web = findViewById(R.id.button3);
        settings = findViewById(R.id.settings);
        join = findViewById(R.id.register);
        rp = findViewById(R.id.rewards);
        events = findViewById(R.id.events);
        image1 = findViewById(R.id.imageView1);
        image2 = findViewById(R.id.imageView2);
        feedback = findViewById(R.id.feedback);
        contact = findViewById(R.id.contact);
        ab = findViewById(R.id.textView);
        wb = findViewById(R.id.textView40);
        fb = findViewById(R.id.textView18);
        set = findViewById(R.id.textView42);
        ct = findViewById(R.id.textView43);
        wb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri link = Uri.parse("https://kishoredurai.github.io/Cloudwebsite/");
                Intent intent = new Intent(Intent.ACTION_VIEW,link);
                startActivity(intent);
            }
        });
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri link = Uri.parse("https://kishoredurai.github.io/Cloudwebsite/");
                Intent intent = new Intent(Intent.ACTION_VIEW,link);
                startActivity(intent);
            }
        });

        ConstraintLayout.LayoutParams row11 = (ConstraintLayout.LayoutParams) rp.getLayoutParams();
        ConstraintLayout.LayoutParams row12 = (ConstraintLayout.LayoutParams) od.getLayoutParams();
        ConstraintLayout.LayoutParams row13 = (ConstraintLayout.LayoutParams) join.getLayoutParams();
        ConstraintLayout.LayoutParams i1 = (ConstraintLayout.LayoutParams) image1.getLayoutParams();
        ConstraintLayout.LayoutParams i2 = (ConstraintLayout.LayoutParams) image2.getLayoutParams();
        ConstraintLayout.LayoutParams slide1 = (ConstraintLayout.LayoutParams) dash.getLayoutParams();
        ConstraintLayout.LayoutParams slide2 = (ConstraintLayout.LayoutParams) events.getLayoutParams();


        row11.width = (int)(getResources().getDisplayMetrics().widthPixels * 0.29);
        row11.height = (int)(getResources().getDisplayMetrics().widthPixels * 0.29);
        row12.width = (int)(getResources().getDisplayMetrics().widthPixels * 0.29);
        row12.height = (int)(getResources().getDisplayMetrics().widthPixels * 0.29);
        row13.width = (int)(getResources().getDisplayMetrics().widthPixels * 0.29);
        row13.height = (int)(getResources().getDisplayMetrics().widthPixels * 0.29);
        i1.width = (int)(getResources().getDisplayMetrics().widthPixels * 0.948);
        i1.height = (int)(getResources().getDisplayMetrics().heightPixels * 0.41);
        i1.topMargin = (int) (getResources().getDisplayMetrics().heightPixels * 0.3);
        i2.width = (int)(getResources().getDisplayMetrics().widthPixels * 0.90);
        i2.height = (int)(getResources().getDisplayMetrics().heightPixels * 0.1);
        slide1.height = (int)(getResources().getDisplayMetrics().heightPixels * 0.38);
        slide2.width = (int)(getResources().getDisplayMetrics().widthPixels * 0.90);
        slide2.height = (int)(getResources().getDisplayMetrics().heightPixels * 0.26);

        ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAbout_cloud();
            }
        });
        bit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAbout_cloud();
            }
        });
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_settings();
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_settings();
            }
        });
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Mainpage.this,Feedback.class);
                startActivity(intent);
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Mainpage.this,Feedback.class);
                startActivity(intent);
            }
        });
        ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mainpage.this,Contact_Details.class);
                startActivity(intent);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mainpage.this,Contact_Details.class);
                startActivity(intent);
            }
        });
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Mainpage.this,Register_Student.class);
                startActivity(intent);

            }
        });
        rp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEvent_attended();
            }
        });
        od.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOdproof();
            }
        });

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if(task.isSuccessful()){
                    String token= task.getResult().getToken();
                    System.out.println("Token : "+token);
                }else{
                    System.out.println("Token : ");

                }
            }
        });

        final List<SlideModel> dashlist = new ArrayList<>();
        final List<SlideModel> eventlist = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("dash")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot data:snapshot.getChildren()) {
                            dashlist.add(new SlideModel(data.child("url").getValue().toString(), ScaleTypes.CENTER_INSIDE));
                            slider_links.add(new String(data.child("link").getValue().toString()));
                        }
                        dash.setImageList(dashlist, ScaleTypes.FIT);
                        dash.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onItemSelected(int i) {
                                String link = slider_links.get(i);
                                gotoUrl(link);
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(),"Check your network connection",Toast.LENGTH_LONG).show();
                    }
                });
        FirebaseDatabase.getInstance().getReference().child("events")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot data:snapshot.getChildren()) {
                            eventlist.add(new SlideModel(data.child("url").getValue().toString(), ScaleTypes.CENTER_INSIDE));
                            slider2_links.add(new String(data.child("link").getValue().toString()));
                        }
                        events.setImageList(eventlist, ScaleTypes.FIT);
                        events.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onItemSelected(int i) {
                                String link = slider2_links.get(i);
                                gotoUrl(link);
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(),"Check your network connection",Toast.LENGTH_LONG).show();
                    }
                });

    }
    private void openEvent_attended() {

        Intent intent= new Intent(this,Attended_Events.class);
        startActivity(intent);
    }

    private void openOdproof() {

        Intent intent= new Intent(Mainpage.this,Odproof_generate.class);
        startActivity(intent);
    }


    private void openAbout_cloud() {

        Intent intent= new Intent(this,About_Cloud.class);
        startActivity(intent);
    }

    private void open_settings() {

        Intent intent= new Intent(this,Settings.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                //.setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();

    }
    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

}