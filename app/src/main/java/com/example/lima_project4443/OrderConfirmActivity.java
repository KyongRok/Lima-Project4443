package com.example.lima_project4443;

import static com.example.lima_project4443.MainActivity.loginmodel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lima_project4443.DAO.DataBaseParticipantsHelper;
import com.example.lima_project4443.Model.Product_Model;

import java.util.ArrayList;

public class OrderConfirmActivity extends AppCompatActivity {

    private Button returnButton;
    private TextView confirmText;
    private String type;
    private Wishlist wl;
    private ShoppingCart sc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //get Intent
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmpage);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        wl = Wishlist.getInstance();
        wl.wList = new ArrayList<Product_Model>();
        sc = ShoppingCart.getInstance();
        sc.cartList = new ArrayList<CartItem>();


        // Record the current time when the activity is created
        long currentTime = System.currentTimeMillis();
        DataBaseParticipantsHelper dbHelper = new DataBaseParticipantsHelper(OrderConfirmActivity.this);

        // Calculate the elapsed time since the start of MainActivity
        double elapsedTime = (currentTime - LoginActivity.startTime)/ 1000.0;
        String formattedTime = String.format("%.2f", elapsedTime);
        Toast.makeText(OrderConfirmActivity.this, "Elapsed Time: " + formattedTime + " seconds", Toast.LENGTH_SHORT).show();
        if(dbHelper.setParticipantCompletionTime(formattedTime,loginmodel,type) == 1){
            //Toast.makeText(OrderConfirmActivity.this,"works",Toast.LENGTH_LONG).show();
        }else{
           // Toast.makeText(OrderConfirmActivity.this,"not",Toast.LENGTH_LONG).show();
        }
        returnButton = findViewById(R.id.returnbutton);

        //if(type == null){
       //     type = "A";
        //}
        confirmText = findViewById(R.id.orderdetailtext);
        String displaythis = "Your order is placed Successfully\n Order #123456\n\nTime took:\n"+formattedTime;
        if(MainActivity.trial>3){

            displaythis = "Your trial is complete!\n\nTime took:\n"+formattedTime+"\nplease return the device!";
            returnButton.setVisibility(View.GONE);
        }
        confirmText.setText(displaythis);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Context context = v.getContext();
                    Bundle b = new Bundle();
                    if(MainActivity.trial==2){
                    if(type.equals("A")){
                        type = "B";
                    }
                    else{
                        type = "A";
                    }
                }
                    b.putString("type",type);
                    Intent i = new Intent(context, LoginActivity.class);
                    i.putExtras(b);
                    MainActivity.trial++;
                    Toast.makeText(OrderConfirmActivity.this, "trial num:"+MainActivity.trial, Toast.LENGTH_SHORT).show();
                    context.startActivity(i);
            }

        });

    }





}
