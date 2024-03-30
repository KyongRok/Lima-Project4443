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

    TextView fname,lname,dob,textA,hometext,carttext,profiletext,favtext;
    EditText fnameedit,lnameedit,dobedit;
    ImageButton editButton, cancelButton, homeButton,wlButton,cartButton;
    Button confirmButton;
    String storedDate, type;
    NavigationHandler handler = new NavigationHandler(this);
    UserInfo user;
    private DatePickerDialog.OnDateSetListener mDateListener;
    boolean edit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        user  = UserInfo.getInstance();
        edit = false;
        fname = findViewById(R.id.fnamecontent);
        lname = findViewById(R.id.lnamecontent);
        textA = findViewById(R.id.textforA);
        hometext=findViewById(R.id.hometext);
        carttext=findViewById(R.id.carttext);
        profiletext=findViewById(R.id.profiletext);
        favtext = findViewById(R.id.favtext);
        dob = findViewById(R.id.dobcontent);
        confirmButton = findViewById(R.id.confirmbutton);
        confirmButton.setVisibility(View.GONE);
        editButton = findViewById(R.id.editButton);
        cancelButton = findViewById(R.id.cancelButton);
        homeButton = findViewById(R.id.btn_home_bottom);
        wlButton = findViewById(R.id.btn_fav);
        cartButton = findViewById(R.id.btn_cart);
        fnameedit = findViewById(R.id.fnameedit);
        fnameedit.setVisibility(View.GONE);
        lnameedit = findViewById(R.id.lnameedit);
        lnameedit.setVisibility(View.GONE);
        dobedit = findViewById(R.id.dobedit);
        dobedit.setVisibility(View.GONE);
        fname.setText(user.getfname());
        lname.setText(user.getlname());

        dob.setText(user.getbdate());
        editButton.setOnClickListener(this);
        dob.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        confirmButton.setOnClickListener(this);
        homeButton.setOnClickListener(this);
        wlButton.setOnClickListener(this);
        cartButton.setOnClickListener(this);
        cancelButton.setVisibility(View.GONE);
        if(!type.equals("A")){
            textA.setVisibility(View.GONE);
            hometext.setVisibility(View.GONE);
            carttext.setVisibility(View.GONE);
            profiletext.setVisibility(View.GONE);
            favtext.setVisibility(View.GONE);
        }

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
        if(view == dob && edit){
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
            edit = true;
            fnameedit.setText(user.getfname());
            lnameedit.setText(user.getlname());
            //dobedit.setText(user.getbdate());
            fnameedit.setVisibility(View.VISIBLE);
            lnameedit.setVisibility(View.VISIBLE);
            //dobedit.setVisibility(View.VISIBLE);
            fname.setVisibility(View.GONE);
            lname.setVisibility(View.GONE);
           // dob.setVisibility(View.GONE);
            editButton.setVisibility(View.GONE);
            cancelButton.setVisibility(View.VISIBLE);
            confirmButton.setVisibility(View.VISIBLE);
            textA.setText("Cancel");


        }
        if(view == cancelButton){
            edit = false;
            fnameedit.setVisibility(View.GONE);
            lnameedit.setVisibility(View.GONE);
            dobedit.setVisibility(View.GONE);
            fname.setVisibility(View.VISIBLE);
            lname.setVisibility(View.VISIBLE);
            dob.setVisibility(View.VISIBLE);
            cancelButton.setVisibility(View.GONE);
            editButton.setVisibility(View.VISIBLE);
            confirmButton.setVisibility(View.GONE);
            dob.setText(user.getbdate());
            textA.setText("Edit Profile");


        }
        if(view == confirmButton){
            edit = false;
            //get user input
            fname.setText(fnameedit.getText());
            lname.setText(lnameedit.getText());

            //update info
            user.setfname(fname.getText().toString());
            user.setlname(lname.getText().toString());
            user.setbdate(dob.getText().toString());

            fnameedit.setVisibility(View.GONE);
            lnameedit.setVisibility(View.GONE);
            dobedit.setVisibility(View.GONE);

            fname.setVisibility(View.VISIBLE);
            lname.setVisibility(View.VISIBLE);
            dob.setVisibility(View.VISIBLE);
            cancelButton.setVisibility(View.GONE);
            editButton.setVisibility(View.VISIBLE);
            confirmButton.setVisibility(View.GONE);
            textA.setText("Edit Profile");


        }
        if(view == homeButton){
            handler.navigateToHome(type);

        }
        if(view == wlButton){
            handler.navigateToWishlist(type);
        }
        if(view == cartButton){
            handler.navigateToCart(type);
        }

    }




}

