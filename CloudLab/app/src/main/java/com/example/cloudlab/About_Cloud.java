package com.example.cloudlab;


import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;


public class About_Cloud extends AppCompatActivity {
    ImageView iv1,iv2;
    public Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__cloud);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        iv1=findViewById(R.id.iv1);
        iv2=findViewById(R.id.iv2);
        ConstraintLayout.LayoutParams i1 = (ConstraintLayout.LayoutParams) iv1.getLayoutParams();
        ConstraintLayout.LayoutParams i2 = (ConstraintLayout.LayoutParams) iv2.getLayoutParams();
        i1.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.33);
        i1.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.14);
        i2.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.28);
        i2.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.125);
    }
}
