package com.example.cloudlab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


public class MainActivity extends AppCompatActivity {
    private Button button;
    TextView a;
    String prevStarted = "prevStarted";

    private int STORAGE_PERMISSION_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            SharedPreferences preferences = getSharedPreferences("PREFERENCE",MODE_PRIVATE);
            String FirstTime = preferences.getString("First","");
            if (FirstTime.equals("Yes"))
            {

                Intent intent = new Intent(MainActivity.this, LoaderPage.class);
                startActivity(intent);
                finish();

            }else {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("First", "Yes");
                editor.apply();

            }
            setContentView(R.layout.activity_main);
            a = findViewById(R.id.info);
            a.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Information.class);
                    startActivity(intent);
                }
            });
            button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    opengetstarted();
                }
            });

        }



    public void opengetstarted(){
        Intent intent = new Intent(MainActivity.this, LoaderPage.class);
        startActivity(intent);
        finish();

    }
    private Boolean firstTime = null;
    /**
     * Checks if the user is opening the app for the first time.
     * Note that this method should be placed inside an activity and it can be called multiple times.
     * @return boolean
     */

    private boolean isFirstTime() {
        if (firstTime == null) {
            SharedPreferences mPreferences = this.getSharedPreferences("first_time", Context.MODE_PRIVATE);
            firstTime = mPreferences.getBoolean("firstTime", true);
            if (firstTime) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("firstTime", false);
                editor.commit();
            }
        }

        return firstTime;
    }

}