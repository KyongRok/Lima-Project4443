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

import java.util.Objects;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView productName, price, desc, cartText;
    private RatingBar rating;
    private ImageButton addcartButton, addwishlistButton, backButton,sleekwishButton, homeButton;
    private Spinner size;
    private ImageView productImage;
    private int productId;
    private String type;
    NavigationHandler handler = new NavigationHandler(this);
    private ShoppingCart cart = ShoppingCart.getInstance();
    private Wishlist wl = Wishlist.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //pick up the product info from previous activity (Shoe object)

        super.onCreate(savedInstanceState);
        setContentView(R.layout.productdetailpage);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("product_id")) {
            productId = intent.getIntExtra("product_id", -1); // -1 is the default value if the product ID is not found
        }
        else {
            // Handle the case where product ID is not provided
            Toast.makeText(this, "Product ID not provided", Toast.LENGTH_SHORT).show();
            finish(); // Finish the activity if product ID is not provided
            return;
        }



        productImage = findViewById(R.id.image_shoe);
        productImage.setImageResource(R.drawable.pandadunks);
        backButton = findViewById(R.id.backButton);
        homeButton = findViewById(R.id.btn_home_bottom);
        addwishlistButton = findViewById(R.id.wishlistButton);
        sleekwishButton = findViewById(R.id.wishButtonB);
        addcartButton = findViewById(R.id.buttoncart);
        cartText = findViewById(R.id.addcarttextView);
        size = findViewById(R.id.spinner);
        homeButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        addwishlistButton.setOnClickListener(this);
        sleekwishButton.setOnClickListener(this);
        addcartButton.setOnClickListener(this);

        //DELETE LATER!
        type = "A";

        //get rid of unnecessary components for design A (Text + Icon)
        if(type.equals("A")){
            sleekwishButton.setVisibility(View.GONE);

        }
        //... for design B (Icon only)
        else{
            addwishlistButton.setVisibility(View.GONE);
            cartText.setVisibility(View.GONE);
        }
        //addcartButton.setImageResource(R.drawable.addcart);




    }
    @Override
    public void onClick (View v){
        if (v == addcartButton) {
            String selectedSize = (String) size.getSelectedItem();
            //add it to the db
            //cart.cartList.add();

            Toast.makeText(ProductDetailActivity.this, "Added to your shopping cart", Toast.LENGTH_LONG).show();

        } else if (v == addwishlistButton || v == sleekwishButton) {
            //Also, check if the product is already in these lists
            Toast.makeText(ProductDetailActivity.this, "Added to your wishlist", Toast.LENGTH_LONG).show();

        }
        else if(v == backButton ){
            //Toast.makeText(ProductDetailActivity.this, "Going back", Toast.LENGTH_LONG).show();

            startActivity(new Intent(ProductDetailActivity.this,productDisplayActivity.class));

        }
        else if (v == homeButton){
            //Toast.makeText(ProductDetailActivity.this, "Going Home", Toast.LENGTH_LONG).show();

            handler.navigateToHome();
        }

        else {
            Toast.makeText(ProductDetailActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();

        }
    }

}
