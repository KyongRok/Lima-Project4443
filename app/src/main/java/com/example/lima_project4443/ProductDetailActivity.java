package com.example.lima_project4443;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView productName, price, desc;
    private RatingBar rating;
    private ImageButton addcartButton, addwishlistButton;
    private Spinner size;
    private ImageView productImage;
    private int productId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //pick up the product info from previous activity
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productdetailpage);
        productImage = findViewById(R.id.image_shoe);
        productImage.setImageResource(R.drawable.pandadunks);
        //addcartButton.setImageResource(R.drawable.addcart);




    }
    public void onClick (View v){
        if (v == addcartButton) {
            Toast.makeText(ProductDetailActivity.this, "Added to your shopping cart", Toast.LENGTH_LONG).show();

        } else if (v == addwishlistButton) {
            //Also, check if the product is already in these lists
            Toast.makeText(ProductDetailActivity.this, "Added to your wishlist", Toast.LENGTH_LONG).show();


        } else {
            Toast.makeText(ProductDetailActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();


        }
    }

}
