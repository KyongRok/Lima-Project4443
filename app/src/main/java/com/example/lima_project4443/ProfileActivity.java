package com.example.lima_project4443;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity  {

    TextView fname,lname,dob;
    EditText fnameedit,lnameedit,dobedit;

    UserInfo user = UserInfo.getInstance();
    private DatePickerDialog.OnDateSetListener mDateListener;
    boolean edit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Intent intent = getIntent();
        fname = findViewById(R.id.fnamecontent);
        lname = findViewById(R.id.lnamecontent);
        dob = findViewById(R.id.dobcontent);
        fname.setText(user.getfname());
        lname.setText(user.getlname());
        dob.setText(user.getbdate());
        //dob.setText(String.valueOf(user.getbdate().YEAR)+"-"+String.valueOf(user.getbdate().MONTH)+"-"+String.valueOf(user.getbdate().DATE));

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        ProfileActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateListener,
                        Calendar.YEAR,
                        Calendar.MONTH,
                        Calendar.DATE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
            });
        mDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = year + "/"+ month + "/"+ dayOfMonth;
                dob.setText(date);
            }
        };






        //String date = Calendar.MONTH + "/"+Calendar.DATE + "/"+ Calendar.YEAR;





    }







}

