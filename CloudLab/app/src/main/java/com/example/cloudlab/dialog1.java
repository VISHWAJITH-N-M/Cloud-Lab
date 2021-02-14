package com.example.cloudlab;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class dialog1 {
    private AppCompatActivity activity;
    private AlertDialog dialog;

    public dialog1(AppCompatActivity myActivity) {
        activity = myActivity;
    }


    void startLoadingDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog1,null));
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }
    void dismissDialog(){
        dialog.dismiss();
    }
}
