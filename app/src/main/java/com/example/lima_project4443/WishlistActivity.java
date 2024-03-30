package com.example.lima_project4443;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lima_project4443.Model.Product_Model;

import java.util.ArrayList;

public class WishlistActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private WishlistAdapter wishlistAdapter;
    private ArrayList<Product_Model> wishlistProducts;
    private Wishlist wl = Wishlist.getInstance();
    private ImageButton homebutton,cartbutton,profilebutton;
    private String type;
    private TextView hometext,carttext,profiletext,favtext;
    NavigationHandler handler = new NavigationHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wishlist);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        homebutton = findViewById(R.id.btn_home_bottom);
        homebutton.setOnClickListener(this);
        cartbutton = findViewById(R.id.btn_cart);
        cartbutton.setOnClickListener(this);
        profilebutton = findViewById(R.id.btn_profile);
        profilebutton.setOnClickListener(this);
        hometext=findViewById(R.id.hometext);
        carttext=findViewById(R.id.carttext);
        profiletext=findViewById(R.id.profiletext);
        favtext = findViewById(R.id.favtext);
        if(type.equals("B")){
            hometext.setVisibility(View.GONE);
            carttext.setVisibility(View.GONE);
            profiletext.setVisibility(View.GONE);
            favtext.setVisibility(View.GONE);
        }
        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize WishlistAdapter with empty list
        wishlistProducts = new ArrayList<>();
        wishlistAdapter = new WishlistAdapter(wishlistProducts,type);
        recyclerView.setAdapter(wishlistAdapter);

        // Load wishlist products
        loadWishlistProducts();
    }

    // Method to load wishlist products
    private void loadWishlistProducts() {
        // Example: Adding dummy products to the wishlist
        if(wl.wList.size()>0){
            for(int i=0;i < wl.wList.size();i++){
                wishlistProducts.add(wl.wList.get(i));
            }

        }


        // Notify adapter about data changes
        wishlistAdapter.notifyDataSetChanged();
    }
    @Override
    public void onClick (View v){
        if (v == homebutton){
            //Toast.makeText(ProductDetailActivity.this, "Going Home", Toast.LENGTH_LONG).show();

            handler.navigateToHome(type);
        }
        if(v== cartbutton){
            handler.navigateToCart(type);
        }
        if(v==profilebutton){
            handler.navigateToProfile(type);
        }


    }
}

