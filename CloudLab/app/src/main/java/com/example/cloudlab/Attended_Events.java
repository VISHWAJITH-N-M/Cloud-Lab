package com.example.cloudlab;

import android.app.DatePickerDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;

import pl.droidsonroids.gif.GifImageView;

public class Attended_Events extends AppCompatActivity {

    EditText cname, tname, ptitle, sname, srollno, semail;
    TextView enddate, startdate;
    String c_name, c_mode, t_name, p_title, s_name, s_rollno, s_email, s_date, e_date, c_status, s_prize, s_dept,b;
    Timer timer;
    String [] split;
    GifImageView done ;
    ImageView bg, bg1;
    int year, month, date, temp;
    Dialog dialog;
    Button event_register, refresh;
    Spinner cmode, sprize, sdept;
    ArrayList<String> list;
    ArrayAdapter <String> adapter ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final dialog2 Dialog1 = new dialog2(Attended_Events.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attended__events);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //progressbar invisible

        //spinner
        sdept = findViewById(R.id.dept);
        list = new ArrayList<String>();
        FirebaseDatabase.getInstance().getReference().child("department").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren())
                    list.add(data.child("dept").getValue().toString());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Attended_Events.this, "Check your Network connection", Toast.LENGTH_SHORT).show();
            }
        });
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        sdept.setAdapter(adapter);
        cmode = findViewById(R.id.contest_mode);
        sprize = findViewById(R.id.prize);


        cname = (EditText) findViewById(R.id.event);
        tname = (EditText) findViewById(R.id.ssig);
        ptitle = (EditText) findViewById(R.id.title);
        sname = (EditText) findViewById(R.id.name);
        srollno = (EditText) findViewById(R.id.roll);
        semail = (EditText) findViewById(R.id.mail);
        bg = findViewById(R.id.imageView28);
        bg1 = findViewById(R.id.imageView29);
        done = findViewById(R.id.gif);
        done.setVisibility(View.INVISIBLE);

        //Date Picker

        startdate = findViewById(R.id.from);
        enddate = findViewById(R.id.to);
        String d = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
        startdate.setText(d);
        enddate.setText(d);
        //Dialog design

        dialog = new Dialog(this);

        //button

        event_register = findViewById(R.id.contest_register);

        ConstraintLayout.LayoutParams sd1 = (ConstraintLayout.LayoutParams) sname.getLayoutParams();
        ConstraintLayout.LayoutParams sd2 = (ConstraintLayout.LayoutParams) semail.getLayoutParams();
        ConstraintLayout.LayoutParams sd3 = (ConstraintLayout.LayoutParams) srollno.getLayoutParams();
        ConstraintLayout.LayoutParams sd4 = (ConstraintLayout.LayoutParams) sdept.getLayoutParams();
        ConstraintLayout.LayoutParams mn = (ConstraintLayout.LayoutParams) bg.getLayoutParams();
        ConstraintLayout.LayoutParams ed1 = (ConstraintLayout.LayoutParams) cname.getLayoutParams();
        ConstraintLayout.LayoutParams ed2 = (ConstraintLayout.LayoutParams) ptitle.getLayoutParams();
        ConstraintLayout.LayoutParams ed3 = (ConstraintLayout.LayoutParams) startdate.getLayoutParams();
        ConstraintLayout.LayoutParams ed4 = (ConstraintLayout.LayoutParams) enddate.getLayoutParams();
        ConstraintLayout.LayoutParams ed5 = (ConstraintLayout.LayoutParams) cmode.getLayoutParams();
        ConstraintLayout.LayoutParams ed6 = (ConstraintLayout.LayoutParams) sprize.getLayoutParams();
        ConstraintLayout.LayoutParams mn1 = (ConstraintLayout.LayoutParams) bg1.getLayoutParams();
        ConstraintLayout.LayoutParams td1 = (ConstraintLayout.LayoutParams) tname.getLayoutParams();

        sd1.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        sd1.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        sd2.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        sd2.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        sd3.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        sd3.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.41);
        sd4.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        sd4.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        mn.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.425);
        mn.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.96);
        ed1.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        ed1.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        ed2.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        ed2.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        ed3.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        ed3.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.41);
        ed4.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        ed4.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.41);
        ed5.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        ed5.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.41);
        ed6.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        ed6.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.41);
        mn1.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.425);
        mn1.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.96);
        td1.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        td1.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.41);


        event_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Network message

                ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {
                    dialog.setContentView(R.layout.alert_dialog);
                    dialog.setCancelable(false);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    refresh = dialog.findViewById(R.id.tryagain);
                    refresh.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            recreate();
                        }
                    });
                    dialog.show();
                } else {
                    c_name = cname.getText().toString();
                    t_name = tname.getText().toString();
                    p_title = ptitle.getText().toString().trim();
                    s_name = sname.getText().toString().trim();
                    s_rollno = srollno.getText().toString().trim();
                    s_email = semail.getText().toString().trim();
                    s_dept = sdept.getSelectedItem().toString().trim();
                    c_mode = cmode.getSelectedItem().toString().trim();
                    s_prize = sprize.getSelectedItem().toString().trim();
                    b = "abcdefghijklmnopqrstuvwxyz.,~#^|$%&*!@";
                    if (s_name.isEmpty() || s_rollno.isEmpty() || s_email.isEmpty() || t_name.isEmpty() || c_name.isEmpty() || s_dept.equals("-Department-") || p_title.isEmpty() || c_mode.equals("-Contest Mode-") || s_prize.equals("-Prize Won-")) {
                        if (c_name.isEmpty())
                            cname.setError("Field required");
                        if (t_name.isEmpty())
                            tname.setError("Field required");
                        else if (tname.length() != 7)
                            tname.setError("SSIG no. contains 7 digits");
                        if (p_title.isEmpty())
                            ptitle.setError("Field required");
                        if (s_name.isEmpty())
                            sname.setError("Field required");
                        else {
                            for (int i = 0; i < s_name.length(); i++) {
                                for (int j = 0; j < b.length(); j++) {
                                    if (s_name.charAt(i) == b.charAt(j)) {
                                        sname.setError("Avoid lower case and special characters EX: VITEL N");
                                        break;
                                    }
                                }
                            }
                        }
                        if (s_rollno.isEmpty())
                            srollno.setError("Field required");
                        else if (s_rollno.length() != 8)
                            srollno.setError("Roll no. contains 8 characters");
                        else {
                            for (int i = 0; i < s_rollno.length(); i++) {
                                for (int j = 0; j < b.length(); j++) {
                                    if (s_rollno.charAt(i) == b.charAt(j)) {

                                        srollno.setError("Avoid lower case and special characters EX: 191AB100");
                                        break;
                                    }
                                }
                            }
                        }
                        if (s_email.isEmpty())
                            semail.setError("Field required");
                        else {
                            split = s_email.split("@");
                            if (split.length == 1) {

                                semail.setError("Enter a valid mail ID Ex:example@bitsathy.ac.in");
                            } else if (!split[1].trim().equals("bitsathy.ac.in")) {

                                semail.setError("Enter a valid mail ID Ex:example@bitsathy.ac.in");
                            }
                        }
                        TextView d = (TextView) sdept.getSelectedView();
                        if (s_dept.equals("-Department-"))
                            d.setError("Field required");
                        TextView m = (TextView) cmode.getSelectedView();
                        if (c_mode.equals("-Contest Mode-"))
                            m.setError("Field required");
                        TextView p = (TextView) sprize.getSelectedView();
                        if (s_prize.equals("-Prize Won-"))
                            p.setError("Field required");
                    } else {
                        temp = 0;
                        for (int i = 0; i < s_name.length(); i++) {
                            for (int j = 0; j < b.length(); j++) {
                                if (s_name.charAt(i) == b.charAt(j)) {
                                    temp = temp + 1;
                                    sname.setError("Avoid lower case and special characters EX: VITEL N");
                                    break;
                                }
                            }
                        }
                        split = s_email.split("@");
                        if (split.length == 1) {
                            temp = temp + 1;
                            semail.setError("Enter a valid mail ID Ex:example@bitsathy.ac.in");
                        } else if (!split[1].trim().equals("bitsathy.ac.in")) {
                            temp = temp + 1;
                            semail.setError("Enter a valid mail ID Ex:example@bitsathy.ac.in");
                        }
                        if (s_rollno.length() != 8) {
                            temp = temp + 1;
                            srollno.setError("Roll no. contains 8 characters");
                        } else {
                            for (int i = 0; i < s_rollno.length(); i++) {
                                for (int j = 0; j < b.length(); j++) {
                                    if (s_rollno.charAt(i) == b.charAt(j)) {
                                        temp = temp + 1;
                                        srollno.setError("Avoid lower case and special characters EX: 191AB100");
                                        break;
                                    }
                                }
                            }
                        }
                        if (tname.length() != 7) {
                            temp = temp + 1;
                            tname.setError("SSIG no. contains 7 digits");
                        }
                        if (temp == 0) {
                            event_register.setVisibility(View.INVISIBLE);
                            done.setVisibility(View.VISIBLE);
                            addItemToSheet();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent i = new Intent(Attended_Events.this, Mainpage.class);
                                    startActivity(i);
                                }
                            }, 1000);
                        } else
                            Toast.makeText(Attended_Events.this, "Form validation failed, check all fields", Toast.LENGTH_LONG).show();

                    }
//

                }


            }

        });


        //Date picker action

        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                date = c.get(Calendar.DATE);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Attended_Events.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                startdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                s_date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                                System.out.println("sdate : " + s_date);
                            }
                        }, year, month, date);
                datePickerDialog.show();
            }
        });
        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                date = c.get(Calendar.DATE);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Attended_Events.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                enddate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                e_date = enddate.getText().toString().trim();
                                if (e_date == "") {
                                    e_date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                                }
                                System.out.println("edate : " + e_date);


                            }
                        }, year, month, date);
                datePickerDialog.show();
            }
        });


        // end of main function

    }

    private void  addItemToSheet() {

//        final ProgressDialog loading = ProgressDialog.show(this,"Adding Item","Please wait");


//System.out.println("ooutput"+c_name+t_name+p_title+s_name+s_rollno+s_email);

        System.out.println("ooutput#: "+e_date);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbxGHXk3vhQbi2dX2Ahs_OoL5PcpNFvj2bsBPZQvM-SlA0Oi_6IPKB12SA/exec",
        new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


//                        loading.dismiss();

//                        Intent intent = new Intent(getApplicationContext(),Attended_Events.class);
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
System.out.println("ooutput3 : "+e_date);
                //here we pass params
                parmas.put("action","add_reward_point");
                parmas.put("name",s_name);
                parmas.put("mail",s_email);
                parmas.put("rollno",s_rollno);
                parmas.put("ssig",t_name);
                parmas.put("dept",s_dept);
                parmas.put("ename",c_name);
                parmas.put("ptitle",p_title);
                parmas.put("regdate",s_date);
                parmas.put("enddate",e_date);
                parmas.put("emode",c_mode);
                parmas.put("prize",s_prize);

                return parmas;
            }
        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);

    }
}

