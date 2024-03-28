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

import com.example.lima_project4443.DAO.DataBaseProductHelper;
import com.example.lima_project4443.Model.Product_Model;

import java.util.Objects;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView productName, price, desc, cartText;
    private RatingBar rating;
    private ImageButton addcartButton, addwishlistButton, backButton,sleekwishButton, homeButton, profileButton, wishlistButton;
    private Spinner size;
    private ImageView productImage;
    private String passedProductName;
    private String type;
    NavigationHandler handler = new NavigationHandler(this);
    private ShoppingCart cart = ShoppingCart.getInstance();
    private Wishlist wl = Wishlist.getInstance();
    private Product_Model currentProduct;
    DataBaseProductHelper dataBaseProduct = new DataBaseProductHelper(ProductDetailActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //pick up the product info from previous activity (Shoe object)

        super.onCreate(savedInstanceState);
        setContentView(R.layout.productdetailpage);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("product_name")) {
            passedProductName = intent.getStringExtra("product_name"); // -1 is the default value if the product ID is not found

            currentProduct = dataBaseProduct.searchProduct(passedProductName).get(0);}

        else {
            // Handle the case where product ID is not provided
            Toast.makeText(this, "Product ID not provided", Toast.LENGTH_SHORT).show();
            finish(); // Finish the activity if product ID is not provided
            return;
        }

        productName = findViewById(R.id.textViewProductName);
        price = findViewById(R.id.price);
        productImage = findViewById(R.id.image_shoe);
        backButton = findViewById(R.id.backButton);
        homeButton = findViewById(R.id.btn_home_bottom);
        wishlistButton = findViewById(R.id.btn_fav);
        profileButton = findViewById(R.id.btn_profile);
        addwishlistButton = findViewById(R.id.wishlistButton);
        sleekwishButton = findViewById(R.id.wishButtonB);
        addcartButton = findViewById(R.id.buttoncart);
        cartText = findViewById(R.id.addcarttextView);
        size = findViewById(R.id.spinner);
        homeButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        profileButton.setOnClickListener(this);
        addwishlistButton.setOnClickListener(this);
        wishlistButton.setOnClickListener(this);
        sleekwishButton.setOnClickListener(this);
        addcartButton.setOnClickListener(this);

        productName.setText(currentProduct.getProductName());
        price.setText("$" + String.valueOf(currentProduct.getPrice()));
        productImage.setImageResource(currentProduct.getImageResourceId());
        //productImage.setImageResource(R.drawable.oldskool);


        //DELETE LATER!
        type = "B";

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
            if(!wl.wList.contains(currentProduct)) {
                wl.wList.add(currentProduct);
                Toast.makeText(ProductDetailActivity.this, "Added to your wishlist", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(ProductDetailActivity.this, "The product is already in your wishlist", Toast.LENGTH_LONG).show();

            }
        }
        else if(v == backButton ){
            //Toast.makeText(ProductDetailActivity.this, "Going back", Toast.LENGTH_LONG).show();

            startActivity(new Intent(ProductDetailActivity.this,productDisplayActivity.class));

        }
        else if (v == homeButton){
            //Toast.makeText(ProductDetailActivity.this, "Going Home", Toast.LENGTH_LONG).show();

            handler.navigateToHome();
        }
        else if(v == profileButton){
            handler.navigateToProfile();

        }
        else if (v == wishlistButton){
            handler.navigateToWishlist();
        }

        else {
            Toast.makeText(ProductDetailActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();

        }
    }

}
