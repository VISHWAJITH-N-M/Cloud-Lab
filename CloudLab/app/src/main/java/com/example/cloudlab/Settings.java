package com.example.cloudlab;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Settings extends AppCompatActivity {
    SwitchCompat toggleButton;
    CardView update,help,share;
    Dialog dialog;
    Button c;
    ConstraintLayout sh;
    ImageView header,footer;
    SharedPreferences.Editor prefEditor;
    SharedPreferences prefs;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        toggleButton = findViewById(R.id.togglebutton);
        update=findViewById(R.id.cardView6);
        help = findViewById(R.id.cardView5);
        share = findViewById(R.id.cardView4);
        SharedPreferences settings = getSharedPreferences(String.valueOf(preferences), 0);
        boolean silent = settings.getBoolean("switchkey", false);
        toggleButton.setChecked(silent);
        header = findViewById(R.id.imageView22);
        footer = findViewById(R.id.footer);
        ConstraintLayout.LayoutParams h = (ConstraintLayout.LayoutParams) header.getLayoutParams();
        ConstraintLayout.LayoutParams f = (ConstraintLayout.LayoutParams) footer.getLayoutParams();
        h.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.39);

        //qr code dialog box
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(Settings.this);
                dialog.setContentView(R.layout.activity_q_rcode);
                dialog.setCancelable(false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
                c=dialog.findViewById(R.id.c);
                sh=dialog.findViewById(R.id.sh);
                c.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                        //Intent intent = new Intent(Settings.this,Settings.class);
                        //startActivity(intent);
                    }
                });
                sh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openWhatsapp();
                    }
                });
                dialog.show();
            }
        });

        // check update
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.google.com/");
            }
            private void gotoUrl(String s) {
                Uri uri =Uri.parse(s);
                Intent launch=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(launch);

            }
        });

        // notification
        toggleButton.setOnCheckedChangeListener(new SwitchCompat.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    SharedPreferences settings = getSharedPreferences(String.valueOf(prefs), MODE_PRIVATE);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("push", "false");
                    editor.commit();
                    SharedPreferences.Editor editors = settings.edit();
                    editors.putBoolean("switchkey", isChecked);
                    editors.commit();
//                    SharedPreferences.Editor editors = preferences.edit();
//                    editors.putBoolean("tgpref", toggleButton.isChecked()); // value to store
//                    editors.commit();
                } else {
                    // The toggle is
                    SharedPreferences settings = getSharedPreferences(String.valueOf(prefs), MODE_PRIVATE);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("push", "true");
                    editor.commit();
                    SharedPreferences.Editor editors = settings.edit();
                    editors.putBoolean("switchkey", isChecked);
                    editors.commit();
                }

            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openInfo();
            }
        });
    }

    private void openInfo() {

        Intent intent= new Intent(this,Information.class);
        startActivity(intent);
    }
    private void openWhatsapp() {

        try {
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT,"share demo");
            String sharemessage="https://play.google.com/store/app/details?="+BuildConfig.APPLICATION_ID+"\n\n";
            intent.putExtra(Intent.EXTRA_TEXT,sharemessage);
            startActivity(Intent.createChooser(intent,"share by"));
        }catch (Exception e){
            Toast.makeText(Settings.this,"Error Occured",Toast.LENGTH_SHORT).show();
        }
    }

}
