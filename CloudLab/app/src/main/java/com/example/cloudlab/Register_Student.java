package com.example.cloudlab;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifImageView;

public class Register_Student extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button register;
    ImageView header,bg1;
    Button refresh;
    EditText inputname,inputrollno,inputmobile,inputemail;
    String name,rollno,mobile,email,dept,b2 = "abcdefghijklmnopqrstuvwxyz.,~#^|$%&*!@";
    String[] split;
    int temp;
    Spinner department;
    Dialog dialog;
    Timer time;
    GifImageView done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__student);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        header = findViewById(R.id.imageView17);
        bg1 = findViewById(R.id.imageView28);
        ConstraintLayout.LayoutParams h = (ConstraintLayout.LayoutParams) header.getLayoutParams();
        h.height = (int)(getResources().getDisplayMetrics().heightPixels * 0.42);
        department = findViewById(R.id.dept);
        done = findViewById(R.id.gif);
        done.setVisibility(View.INVISIBLE);
        department.setOnItemSelectedListener(this);

        dialog=new Dialog(this);

        inputname = findViewById(R.id.name);
        inputrollno = findViewById(R.id.roll);
        inputmobile = findViewById(R.id.num);
        inputemail = findViewById(R.id.mail);
        register = findViewById(R.id.register);
        ConstraintLayout.LayoutParams sd1 = (ConstraintLayout.LayoutParams) inputname.getLayoutParams();
        ConstraintLayout.LayoutParams sd2 = (ConstraintLayout.LayoutParams) inputemail.getLayoutParams();
        ConstraintLayout.LayoutParams sd3 = (ConstraintLayout.LayoutParams) department.getLayoutParams();
        ConstraintLayout.LayoutParams ed5 = (ConstraintLayout.LayoutParams) inputrollno.getLayoutParams();
        ConstraintLayout.LayoutParams ed6 = (ConstraintLayout.LayoutParams) inputmobile.getLayoutParams();
        ConstraintLayout.LayoutParams mn1 = (ConstraintLayout.LayoutParams) bg1.getLayoutParams();

        sd1.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        sd1.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        sd2.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        sd2.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        sd3.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        sd3.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        ed5.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        ed5.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.41);
        ed6.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        ed6.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.41);
        mn1.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.425);
        mn1.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.96);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                }else{
                    rollno = inputrollno.getText().toString().trim();
                    mobile = inputmobile.getText().toString().trim();
                    email = inputemail.getText().toString().trim();
                    name = inputname.getText().toString();
                    dept = department.getSelectedItem().toString();
                    if(name.isEmpty() || email.isEmpty() || mobile.isEmpty() || rollno.isEmpty() || dept.equals("-Department-"))
                    {
                        if(name.isEmpty())
                            inputname.setError("Field required");
                        else{
                            for (int i = 0; i <name.length(); i++) {
                                for (int j = 0; j < b2.length(); j++) {
                                    if (name.charAt(i) == b2.charAt(j)) {
                                        inputname.setError("Avoid lower case and special characters EX: VITEL N");
                                        break;
                                    }
                                }
                            }
                        }
                        if(email.isEmpty())
                            inputemail.setError("Field required");
                        else{
                            split = email.split("@");
                            if(split.length == 1){
                                inputemail.setError("Enter a valid mail ID Ex:example@bitsathy.ac.in");
                            }
                            else if(!split[1].trim().equals("bitsathy.ac.in"))
                            {

                                inputemail.setError("Enter a valid mail ID Ex:example@bitsathy.ac.in");
                            }
                        }
                        if(mobile.isEmpty())
                            inputmobile.setError("Field required");
                        else if(mobile.length() != 10)
                            inputmobile.setError("Enter 10 digit mobile number");
                        if(rollno.isEmpty())
                            inputrollno.setError("Field required");
                        else if(rollno.length() != 8)
                            inputrollno.setError("Roll no. contains 8 digts");
                        else{
                            for(int i = 0;i<rollno.length();i++)
                            {
                                for(int j = 0;j<b2.length();j++)
                                {
                                    if(rollno.charAt(i) == b2.charAt(j))
                                    {

                                        inputrollno.setError("Avoid lower case and special characters EX: 191AB100");
                                        break;
                                    }
                                }
                            }
                        }
                        TextView d = (TextView)department.getSelectedView();
                        if(dept.equals("-Department-"))
                            d.setError("Field required");
                    }
                    else {
                        temp = 0;
                        for (int i = 0; i < name.length(); i++) {
                            for (int j = 0; j < b2.length(); j++) {
                                if (name.charAt(i) == b2.charAt(j)) {
                                    temp = temp + 1;
                                    inputname.setError("Avoid lower case and special characters EX: VITEL N");
                                    break;
                                }
                            }
                        }
                        split = email.split("@");
                        if (split.length == 1) {
                            temp = temp + 1;
                            inputemail.setError("Enter a valid mail ID Ex:example@bitsathy.ac.in");
                        } else if (!split[1].trim().equals("bitsathy.ac.in")) {
                            temp = temp + 1;
                            inputemail.setError("Enter a valid mail ID Ex:example@bitsathy.ac.in");
                        }
                        if (rollno.length() != 8) {
                            temp = temp + 1;
                            inputrollno.setError("Roll no. contains 8 characters");
                        } else {
                            for (int i = 0; i < rollno.length(); i++) {
                                for (int j = 0; j < b2.length(); j++) {
                                    if (rollno.charAt(i) == b2.charAt(j)) {
                                        temp = temp + 1;
                                        inputrollno.setError("Avoid lower case and special characters EX: 191AB100");
                                        break;
                                    }
                                }
                            }
                        }
                        if(mobile.length() != 10) {
                            temp = temp + 1;
                            inputmobile.setError("Enter 10 digit mobile number");
                        }
                        if (temp == 0)
                        {
                            register.setVisibility(View.INVISIBLE);
                            done.setVisibility(View.VISIBLE);
                            addItemToSheet();
                            time = new Timer();
                            time.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    Textfieldclear();
                                    Intent intent = new Intent(Register_Student.this,Mainpage.class);
                                    startActivity(intent);

                                }
                            },1000);
                        }
                        else
                            Toast.makeText(Register_Student.this,"Form validation failed, check all fields",Toast.LENGTH_LONG).show();
                    }


                }



            }

        });    }


    private void   addItemToSheet() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbxGHXk3vhQbi2dX2Ahs_OoL5PcpNFvj2bsBPZQvM-SlA0Oi_6IPKB12SA/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

//                        loading.dismiss();

//                        Intent intent = new Intent(getApplicationContext(),Register_Student.class);
//                        startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                //here we pass params
                parmas.put("action","addItem");
                parmas.put("name",name);
                parmas.put("dept",dept);
                parmas.put("rollno",rollno);
                parmas.put("phone",mobile);
                parmas.put("mail",email);
                return parmas;

            }
        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        dept = department.getSelectedItem().toString().trim();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void Textfieldclear()
    {
        inputrollno.getText().clear();
        inputmobile.getText().clear();
        inputemail.getText().clear();
        inputname.getText().clear();
    }
}