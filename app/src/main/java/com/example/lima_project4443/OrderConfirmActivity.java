package com.example.lima_project4443;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderConfirmActivity extends AppCompatActivity {

    private Button returnButton;
    private TextView confirmText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //get Intent
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmpage);
        confirmText = findViewById(R.id.orderdetailtext);
        confirmText.setText("Your order is placed Successfully\n Order #123456");

        /**
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //i

            }

        });**/

    }





}