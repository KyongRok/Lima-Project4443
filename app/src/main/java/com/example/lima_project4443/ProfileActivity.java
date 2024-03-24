package com.example.lima_project4443;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener  {

    TextView fname,lname,dob;
    EditText fnameedit,lnameedit,dobedit;
    ImageButton editButton, cancelButton;

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
        editButton = findViewById(R.id.editButton);
        cancelButton = findViewById(R.id.cancelButton);
        fnameedit = findViewById(R.id.fnameedit);
        fnameedit.setVisibility(View.GONE);
        lnameedit = findViewById(R.id.lnameedit);
        lnameedit.setVisibility(View.GONE);
        dobedit = findViewById(R.id.dobedit);
        dobedit.setVisibility(View.GONE);
        fname.setText(user.getfname());
        lname.setText(user.getlname());
        fnameedit.setText(user.getfname());
        lnameedit.setText(user.getlname());
        dobedit.setText(user.getbdate());
        dob.setText(user.getbdate());
        editButton.setOnClickListener(this);
        dob.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        cancelButton.setVisibility(View.GONE);

        //dob.setText(String.valueOf(user.getbdate().YEAR)+"-"+String.valueOf(user.getbdate().MONTH)+"-"+String.valueOf(user.getbdate().DATE));

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


    @Override
    public void onClick(View view) {
        if(view == dob){
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
        if(view == editButton){
            fnameedit.setVisibility(View.VISIBLE);
            lnameedit.setVisibility(View.VISIBLE);
            dobedit.setVisibility(View.VISIBLE);
            fname.setVisibility(View.GONE);
            lname.setVisibility(View.GONE);
            dob.setVisibility(View.GONE);
            editButton.setVisibility(View.GONE);
            cancelButton.setVisibility(View.VISIBLE);

        }
        if(view == cancelButton){
            fnameedit.setVisibility(View.GONE);
            lnameedit.setVisibility(View.GONE);
            dobedit.setVisibility(View.GONE);
            fname.setVisibility(View.VISIBLE);
            lname.setVisibility(View.VISIBLE);
            dob.setVisibility(View.VISIBLE);
            cancelButton.setVisibility(View.GONE);
            editButton.setVisibility(View.VISIBLE);

        }

    }




}

