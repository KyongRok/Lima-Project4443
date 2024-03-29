package com.example.lima_project4443;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderConfirmActivity extends AppCompatActivity {

    private Button returnButton;
    private TextView confirmText;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //get Intent
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmpage);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        confirmText = findViewById(R.id.orderdetailtext);
        confirmText.setText("Your order is placed Successfully\n Order #123456");


        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Context context = v.getContext();
                    Bundle b = new Bundle();
                    if(type.equals("A")){
                        type = "B";
                    }
                    else{
                        type = "A";
                    }
                    b.putString("type",type);
                    Intent i = new Intent(context, LoginActivity.class);
                    i.putExtras(b);
                    context.startActivity(i);

            }

        });

    }





}
