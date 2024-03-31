package com.example.lima_project4443;

import android.content.Context;
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

    private TextView productName, price, gobacktext, cartText,hometext,carttext,profiletext,favtext;
    private RatingBar rating;
    private ImageButton addcartButton, addwishlistButton, backButton,sleekwishButton, homeButton, profileButton, wishlistButton, cartButton;
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

        type = intent.getStringExtra("type");
       // if (type == null){
       //     type = "A";
      //  }
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
        cartButton = findViewById(R.id.btn_cart);
        wishlistButton = findViewById(R.id.btn_fav);
        profileButton = findViewById(R.id.btn_profile);
        addwishlistButton = findViewById(R.id.wishlistButton);
        sleekwishButton = findViewById(R.id.wishButtonB);
        addcartButton = findViewById(R.id.buttoncart);
        cartText = findViewById(R.id.addcarttextView);
        gobacktext = findViewById(R.id.gobacktext);
        size = findViewById(R.id.spinner);
        homeButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        profileButton.setOnClickListener(this);
        addwishlistButton.setOnClickListener(this);
        wishlistButton.setOnClickListener(this);
        sleekwishButton.setOnClickListener(this);
        addcartButton.setOnClickListener(this);
        cartButton.setOnClickListener(this);
        hometext=findViewById(R.id.hometext);
        carttext=findViewById(R.id.carttext);
        profiletext=findViewById(R.id.profiletext);
        favtext = findViewById(R.id.favtext);

        productName.setText(currentProduct.getProductName());
        price.setText("$" + String.valueOf(currentProduct.getPrice()));
        productImage.setImageResource(currentProduct.getImageResourceId());
        //productImage.setImageResource(R.drawable.oldskool);


        //DELETE LATER!


        //get rid of unnecessary components for design A (Text + Icon)
        if(type.equals("A")){
            sleekwishButton.setVisibility(View.GONE);

        }
        //... for design B (Icon only)
        else{
            addwishlistButton.setVisibility(View.GONE);
            gobacktext.setVisibility(View.GONE);
            cartText.setVisibility(View.GONE);
            hometext.setVisibility(View.GONE);
            carttext.setVisibility(View.GONE);
            profiletext.setVisibility(View.GONE);
            favtext.setVisibility(View.GONE);
        }
        //addcartButton.setImageResource(R.drawable.addcart);




    }
    @Override
    public void onClick (View v){
        if (v == addcartButton) {
            String selectedSize = (String) size.getSelectedItem();
            //add it to the db
            int flag_found = 0;
            int index = 0;
            for(int i =0; i < cart.cartList.size(); i++){
                if(currentProduct.getProductId() == cart.cartList.get(i).getProduct().getProductId()){
                    flag_found = 1;
                    index = i;
                }
            }
            if(flag_found == 1){
                int current_qty = cart.cartList.get(index).getQuantity();
                cart.cartList.get(index).setQuantity(current_qty + 1);
            }else{
                cart.cartList.add(new CartItem(currentProduct,1,5));
            }

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
            Context context = v.getContext();
            Intent intent = new Intent(context, productDisplayActivity.class);
            Bundle b = new Bundle();
            b.putString("type",type);
            intent.putExtras(b);
            context.startActivity(intent);

            //startActivity(new Intent(ProductDetailActivity.this,productDisplayActivity.class));

        }
        else if (v == homeButton){
            //Toast.makeText(ProductDetailActivity.this, "Going Home", Toast.LENGTH_LONG).show();

            handler.navigateToHome(type);
        }
        else if(v == profileButton){
            handler.navigateToProfile(type);

        }
        else if (v == wishlistButton){
            handler.navigateToWishlist(type);

        }else if (v == cartButton){
            //Toast.makeText(ProductDetailActivity.this, "hi", Toast.LENGTH_LONG).show();

            handler.navigateToCart(type);

        }

        else {
            Toast.makeText(ProductDetailActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();

        }
    }

}
