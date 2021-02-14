package com.example.cloudlab;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.firebase.client.Firebase;

import pl.droidsonroids.gif.GifImageView;

public class Feedback extends AppCompatActivity {
    Button SubmitButton,refresh;
    Dialog dialog;
    EditText NameEditText,  EmailEditText,IdeaEditText;
    String [] split;
    ImageView bg,header;
    // Declaring String variable ( In which we are storing firebase server URL ).
    public static final String Firebase_Server_URL = "https://cloudapp-9475c.firebaseio.com/";
    int temp;
    // Declaring String variables to store name & phone number get from EditText.
    String NameHolder,  EmailHolder , IdeaHolder, b1;

    // Declaring Firebase object.
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        // Adding MainActivity context into Firebase context.
        Firebase.setAndroidContext(Feedback.this);

        // Passing firebase Server URL into firebase object.
        firebase = new Firebase("https://cloudapp-9475c.firebaseio.com/");
        SubmitButton = (Button)findViewById(R.id.submit);
        NameEditText = (EditText)findViewById(R.id.name);
        EmailEditText = (EditText)findViewById(R.id.mail);
        bg = findViewById(R.id.imageView28);
        IdeaEditText =(EditText)findViewById(R.id.feedback);
        header = findViewById(R.id.gifImageView2);

        ConstraintLayout.LayoutParams et1 = (ConstraintLayout.LayoutParams) NameEditText.getLayoutParams();
        ConstraintLayout.LayoutParams et2 = (ConstraintLayout.LayoutParams) EmailEditText.getLayoutParams();
        ConstraintLayout.LayoutParams et3 = (ConstraintLayout.LayoutParams) IdeaEditText.getLayoutParams();
        ConstraintLayout.LayoutParams h = (ConstraintLayout.LayoutParams) header.getLayoutParams();
        ConstraintLayout.LayoutParams b = (ConstraintLayout.LayoutParams) bg.getLayoutParams();

        et1.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        et1.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        et2.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        et2.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        et3.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        h.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.30);
        b.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.48);
        b.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.96);


        final dialog1 Dialog1= new dialog1(Feedback.this);
        // Adding Click listener to Submit button.
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Declaring student class object.
                Student student = new Student();

                // Calling function to Get data from EditText and store into string variables.
                GetDataFromEditText();

                // Adding student name into student class object.
                student.setName(NameHolder);

                // Adding student number into student class object.
                student.setEmail(EmailHolder);
                student.setfeedback(IdeaHolder);

                ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo == null||!networkInfo.isConnected()||!networkInfo.isAvailable()){
                    dialog.setContentView(R.layout.alert_dialog);
                    dialog.setCancelable(false);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
                    refresh=dialog.findViewById(R.id.tryagain);
                    refresh.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            recreate();
                        }
                    });
                    dialog.show();
                }else {
                    b1 = "abcdefghijklmnopqrstuvwxyz.,~#^|$%&*!@";
                    NameHolder = NameEditText.getText().toString();
                    EmailHolder = EmailEditText.getText().toString();
                    IdeaHolder = IdeaEditText.getText().toString();
                    if(NameHolder.isEmpty() || EmailHolder.isEmpty() || IdeaHolder.isEmpty())
                    {
                        if(NameHolder.isEmpty())
                            NameEditText.setError("Field required");
                        else
                        {
                            for (int i = 0; i < NameHolder.length(); i++) {
                                for (int j = 0; j < b1.length(); j++) {
                                    if (NameHolder.charAt(i) == b1.charAt(j)) {

                                        NameEditText.setError("Avoid lower case and special characters EX: VITEL N");
                                        break;
                                    }
                                }
                            }
                        }
                        if(EmailHolder.isEmpty())
                            EmailEditText.setError("Field required");
                        else
                        {
                            split = EmailHolder.split("@");
                            if(split.length == 1){

                                EmailEditText.setError("Enter a valid mail ID Ex:example@bitsathy.ac.in");
                            }
                            else if(!split[1].trim().equals("bitsathy.ac.in"))
                            {

                                EmailEditText.setError("Enter a valid mail ID Ex:example@bitsathy.ac.in");
                            }
                        }
                        if(IdeaHolder.isEmpty())
                            IdeaEditText.setError("Field required");
                    }
                    else
                    {
                        temp = 0;
                        for (int i = 0; i < NameHolder.length(); i++) {
                            for (int j = 0; j < b1.length(); j++) {
                                if (NameHolder.charAt(i) == b1.charAt(j)) {
                                    temp = temp + 1;
                                    NameEditText.setError("Avoid lower case and special characters EX: VITEL N");
                                    break;
                                }
                            }
                        }
                        split = EmailHolder.split("@");
                        if(split.length == 1){
                            temp = temp + 1;
                            EmailEditText.setError("Enter a valid mail ID Ex:example@bitsathy.ac.in");
                        }
                        else if(!split[1].trim().equals("bitsathy.ac.in"))
                        {
                            temp = temp + 1;
                            EmailEditText.setError("Enter a valid mail ID Ex:example@bitsathy.ac.in");
                        }
                        if(temp == 0) {
                            firebase.child("Student").push().setValue(student);
                            Dialog1.startLoadingDialog();
                            ;
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Dialog1.dismissDialog();
                                }
                            }, 1000);
                            // Showing toast message after data inserted.
                            Textfieldclear();
                        }
                        else
                            Toast.makeText(Feedback.this,"Form validation failed, check all fields",Toast.LENGTH_LONG).show();
                    }


                }
                // Passing student phone number and name into firebase object to add into database.

            }
        });

    }

    public void GetDataFromEditText(){

        NameHolder = NameEditText.getText().toString().trim();

        EmailHolder = EmailEditText.getText().toString().trim();

        IdeaHolder = IdeaEditText.getText().toString().trim();

    }
    public void Textfieldclear()
    {
        NameEditText.getText().clear();
        EmailEditText.getText().clear();
        IdeaEditText.getText().clear();
    }
}