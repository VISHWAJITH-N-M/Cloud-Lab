package com.example.cloudlab;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifImageView;

public class Odproof_generate extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private static final int STORAGE_CODE = 1000;
    int year, month, date, hour, minute;
    TextView enddate, startdate, starttime, endtime;
    int temp;
    GifImageView done ;
    String s_name, s_rollno, s_dept, od_type,m_email, act_id, c_name, start_date, start_time, end_date, end_time, mentor_name,s_email,s_ssig,p_title,b3="abcdefghijklmnopqrstuvwxyz.,~#^|$%&*!@",h,m;
    String [] split,time;
    Button savebtn,refresh;
    Bitmap bmp;
    Dialog dialog;
    Timer times;
    ImageView image,image1,image2,image3;
    Spinner dept, ODtype;

    EditText sname, srollnumber, activityid, cevent, m_name, m_mail, semail, title, ssig;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odproof_generate);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        //Edit Text

        sname = findViewById(R.id.name);
        srollnumber = findViewById(R.id.roll);
        activityid = findViewById(R.id.activity);
        cevent = findViewById(R.id.event);
        title =  findViewById(R.id.title);
        image = findViewById(R.id.imageView28);
        image1 = findViewById(R.id.imageView29);
        image2 = findViewById(R.id.imageView30);
        image3 = findViewById(R.id.imageView);
        m_name = findViewById(R.id.m_name);
        m_mail = findViewById(R.id.m_mail);
        semail = findViewById(R.id.mail);
        ssig = findViewById(R.id.ssig);
        done = findViewById(R.id.gif);
        done.setVisibility(View.INVISIBLE);
        dialog=new Dialog(this);
        dept = findViewById(R.id.dept);
        dept.setOnItemSelectedListener(this);

        ODtype = findViewById(R.id.odtype);
        ODtype.setOnItemSelectedListener(this);


        //Date Picker

        startdate = findViewById(R.id.from);
        String d = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
        startdate.setText(d);

        enddate = findViewById(R.id.to);
        enddate.setText(d);

        //Time Picker
        final Date currenttime = Calendar.getInstance().getTime();
        time = currenttime.toString().split(" ");
        starttime = findViewById(R.id.t_from);
        starttime.setText(time[3]);
        endtime = findViewById(R.id.t_to);
        endtime.setText(time[3]);


        //button save
        savebtn = findViewById(R.id.register);


        ConstraintLayout.LayoutParams sd1 = (ConstraintLayout.LayoutParams) sname.getLayoutParams();
        ConstraintLayout.LayoutParams sd2 = (ConstraintLayout.LayoutParams) semail.getLayoutParams();
        ConstraintLayout.LayoutParams sd3 = (ConstraintLayout.LayoutParams) srollnumber.getLayoutParams();
        ConstraintLayout.LayoutParams sd4 = (ConstraintLayout.LayoutParams) dept.getLayoutParams();
        ConstraintLayout.LayoutParams sd5 = (ConstraintLayout.LayoutParams) ssig.getLayoutParams();
        ConstraintLayout.LayoutParams mn = (ConstraintLayout.LayoutParams) image.getLayoutParams();
        ConstraintLayout.LayoutParams ed1 = (ConstraintLayout.LayoutParams) cevent.getLayoutParams();
        ConstraintLayout.LayoutParams ed2 = (ConstraintLayout.LayoutParams) title.getLayoutParams();
        ConstraintLayout.LayoutParams od1 = (ConstraintLayout.LayoutParams) startdate.getLayoutParams();
        ConstraintLayout.LayoutParams od2 = (ConstraintLayout.LayoutParams) enddate.getLayoutParams();
        ConstraintLayout.LayoutParams od3 = (ConstraintLayout.LayoutParams) starttime.getLayoutParams();
        ConstraintLayout.LayoutParams od4 = (ConstraintLayout.LayoutParams) endtime.getLayoutParams();
        ConstraintLayout.LayoutParams od5 = (ConstraintLayout.LayoutParams) activityid.getLayoutParams();
        ConstraintLayout.LayoutParams od6 = (ConstraintLayout.LayoutParams) ODtype.getLayoutParams();
        ConstraintLayout.LayoutParams mn1 = (ConstraintLayout.LayoutParams) image1.getLayoutParams();
        ConstraintLayout.LayoutParams mn2 = (ConstraintLayout.LayoutParams) image2.getLayoutParams();
        ConstraintLayout.LayoutParams mn3 = (ConstraintLayout.LayoutParams) image3.getLayoutParams();
        ConstraintLayout.LayoutParams md1 = (ConstraintLayout.LayoutParams) m_name.getLayoutParams();
        ConstraintLayout.LayoutParams md2 = (ConstraintLayout.LayoutParams) m_mail.getLayoutParams();

        sd1.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        sd1.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        sd2.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        sd2.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        sd3.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        sd3.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.41);
        sd4.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        sd4.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        sd5.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        sd5.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.41);
        ed1.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        ed1.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        ed2.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        ed2.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        od1.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        od1.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.41);
        od2.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        od2.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.41);
        od3.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        od3.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.41);
        od4.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        od4.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.41);
        od5.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        od5.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        od6.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        od6.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        md1.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        md1.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        md2.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.076);
        md2.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        mn.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.42);
        mn.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.96);
        mn1.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.236);
        mn1.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.96);
        mn2.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.42);
        mn2.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.96);
        mn3.height = (int) (getResources().getDisplayMetrics().heightPixels * 0.236);
        mn3.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.96);

        //save function call

        savebtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
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

                    s_name = sname.getText().toString();
                    s_rollno = srollnumber.getText().toString();
                    act_id = activityid.getText().toString();
                    s_ssig = ssig.getText().toString();
                    p_title = title.getText().toString();
                    c_name = cevent.getText().toString();
                    s_email = semail.getText().toString();
                    m_email = m_mail.getText().toString();
                    mentor_name = m_name.getText().toString();
                    s_dept = dept.getSelectedItem().toString();
                    od_type = ODtype.getSelectedItem().toString();
                    if (s_name.isEmpty() || s_rollno.isEmpty() || s_email.isEmpty() || s_ssig.isEmpty() || s_dept.equals("-Department-") || c_name.isEmpty() || p_title.isEmpty() || od_type.isEmpty() || act_id.isEmpty() || m_email.isEmpty() || mentor_name.isEmpty())
                    {
                        if (s_ssig.isEmpty())
                            ssig.setError("Field required");
                        else if (s_ssig.length() != 7)
                            ssig.setError("SSIG no. contains 7 digits");
                        if (p_title.isEmpty())
                            title.setError("Field required");
                        if (s_name.isEmpty())
                            sname.setError("Field required");
                        else {
                            for (int i = 0; i < s_name.length(); i++) {
                                for (int j = 0; j < b3.length(); j++) {
                                    if (s_name.charAt(i) == b3.charAt(j)) {
                                        sname.setError("Avoid lower case and special characters EX: VITEL N");
                                        break;
                                    }
                                }
                            }
                        }
                        if(c_name.isEmpty())
                            cevent.setError("Field required");
                        if (s_rollno.isEmpty())
                            srollnumber.setError("Field required");
                        else if (s_rollno.length() != 8)
                            srollnumber.setError("Roll no. contains 8 characters");
                        else {
                            for (int i = 0; i < s_rollno.length(); i++) {
                                for (int j = 0; j < b3.length(); j++) {
                                    if (s_rollno.charAt(i) == b3.charAt(j)) {
                                        srollnumber.setError("Avoid lower case and special characters EX: 191AB100");
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
                        TextView d = (TextView) dept.getSelectedView();
                        if (s_dept.equals("-Department-"))
                            d.setError("Field required");
                        if (act_id.isEmpty())
                            activityid.setError("Field required");
                        else if (act_id.length() != 5)
                            activityid.setError("Activity number contains 5 digits");
                        TextView o = (TextView) ODtype.getSelectedView();
                        if (od_type.equals("Select OD type"))
                            o.setError("Field required");
                        if (m_email.isEmpty())
                            m_mail.setError("Field required");
                        else {
                            split = m_email.split("@");
                            if (split.length == 1) {

                                m_mail.setError("Enter a valid mail ID Ex:example@bitsathy.ac.in");
                            } else if (!split[1].trim().equals("bitsathy.ac.in")) {
                                m_mail.setError("Enter a valid mail ID Ex:example@bitsathy.ac.in");
                            }
                        }
                        if (mentor_name.isEmpty())
                            m_name.setError("Field required");
                        else {
                            for (int i = 0; i < mentor_name.length(); i++) {
                                for (int j = 0; j < b3.length(); j++) {
                                    if (mentor_name.charAt(i) == b3.charAt(j)) {
                                        m_name.setError("Avoid lower case and special characters EX: VITEL N");
                                        break;
                                    }
                                }
                            }
                        }

                    } else {
                        temp = 0;
                        for (int i = 0; i < s_name.length(); i++) {
                            for (int j = 0; j < b3.length(); j++) {
                                if (s_name.charAt(i) == b3.charAt(j)) {
                                    temp = temp + 1;
                                    sname.setError("Avoid lower case and special characters EX: VITEL N");
                                    break;
                                }
                            }
                        }
                        for (int i = 0; i < mentor_name.length(); i++) {
                            for (int j = 0; j < b3.length(); j++) {
                                if (mentor_name.charAt(i) == b3.charAt(j)) {
                                    temp = temp + 1;
                                    m_name.setError("Avoid lower case and special characters EX: VITEL N");
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
                        split = m_email.split("@");
                        if (split.length == 1) {
                            temp = temp + 1;
                            m_mail.setError("Enter a valid mail ID Ex:example@bitsathy.ac.in");
                        } else if (!split[1].trim().equals("bitsathy.ac.in")) {
                            temp = temp + 1;
                            m_mail.setError("Enter a valid mail ID Ex:example@bitsathy.ac.in");
                        }
                    if (s_rollno.length() != 8) {
                        temp = temp + 1;
                        srollnumber.setError("Roll no. contains 8 characters");
                    } else {
                        for (int i = 0; i < s_rollno.length(); i++) {
                            for (int j = 0; j < b3.length(); j++) {
                                if (s_rollno.charAt(i) == b3.charAt(j)) {
                                    temp = temp + 1;
                                    srollnumber.setError("Avoid lower case and special characters EX: 191AB100");
                                    break;
                                }
                            }
                        }
                    }
                    if (s_ssig.length() != 7) {
                        temp = temp + 1;
                        ssig.setError("SSIG no. contains 7 digits");
                    }
                    if (act_id.length() != 5)
                            activityid.setError("Activity number contains 5 digits");
                    if (temp == 0) {
                        savebtn.setVisibility(View.INVISIBLE);
                        done.setVisibility(View.VISIBLE);
                        addItemToSheet();
                        times = new Timer();
                        times.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Textfieldclear();
                                Intent intent = new Intent(Odproof_generate.this,odproof_response.class);
                                intent.putExtra("value",act_id);
                                startActivity(intent);
                            }
                        },1000);

                    }
                    else
                        Toast.makeText(Odproof_generate.this,"Form validation failed, check all fields",Toast.LENGTH_LONG).show();
                }
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


                DatePickerDialog datePickerDialog = new DatePickerDialog(Odproof_generate.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                startdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                start_date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
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


                DatePickerDialog datePickerDialog = new DatePickerDialog(Odproof_generate.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                enddate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                end_date=dayOfMonth+"-"+(monthOfYear+1)+"-"+year;

                            }
                        }, year, month, date);
                datePickerDialog.show();
            }
        });

        //Time picker action

        starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(Odproof_generate.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                starttime.setText(hourOfDay + ":" + minute);
                                start_time=hourOfDay + ":" + minute;
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
            }

        });

        endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(Odproof_generate.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                endtime.setText(hourOfDay + ":" + minute);
                                end_time=hourOfDay + ":" + minute;
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
            }

        });


        //end main function

    }


    //Drop down

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        s_dept = dept.getSelectedItem().toString().trim();
        od_type = ODtype.getSelectedItem().toString().trim();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    //Add details to sheet

    private void  addItemToSheet() {

//        final ProgressDialog loading = ProgressDialog.show(this,"Adding Item","Please wait");


        //System.out.println("ooutput"+c_name+t_name+p_title+s_name+s_rollno+s_email);


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
                System.out.println("ooutput3 : "+start_date+end_date+start_time+start_time);
                //here we pass params
                parmas.put("action","od_proof");
                parmas.put("name",s_name);
                parmas.put("mail",s_email);
                parmas.put("rollno",s_rollno);
                parmas.put("ssig",s_ssig);
                parmas.put("dept",s_dept);
                parmas.put("ename",c_name);
                parmas.put("ptitle",p_title);
                parmas.put("fromdate",start_date);
                parmas.put("todate",end_date);
                parmas.put("fromtime",start_time);
                parmas.put("totime",end_time);
                parmas.put("activityno",act_id);
                parmas.put("odtype",od_type);
                parmas.put("m_name",mentor_name);
                parmas.put("m_mail",m_email);

                return parmas;

            }
        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);


    }



    //text clear

    public void Textfieldclear()
    {
        sname.getText().clear();
        srollnumber.getText().clear();
        semail.getText().clear();
        ssig.getText().clear();
        cevent.getText().clear();
        startdate.setText("");
        enddate.setText("");
        starttime.setText("");
        endtime.setText("");
        title.getText().clear();
        activityid.getText().clear();
        m_name.getText().clear();
        m_mail.getText().clear();
        dept.getSelectedItem();

    }
//end of class
}