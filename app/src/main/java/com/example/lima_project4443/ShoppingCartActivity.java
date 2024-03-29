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

public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ShoppingCartAdapter shoppingcartAdapter;
    private ArrayList<Product_Model> shoppingcartProducts;
    private ShoppingCart sc = ShoppingCart.getInstance();
    private ImageButton homebutton;
    private TextView  total_price;
    private String type;
    NavigationHandler handler = new NavigationHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoppingcart);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        homebutton = findViewById(R.id.btn_home_bottom);
        homebutton.setOnClickListener(this);
        total_price = (TextView) findViewById(R.id.TotalPrice);
         //Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize WishlistAdapter with empty list
        shoppingcartProducts = new ArrayList<>();
        shoppingcartAdapter = new ShoppingCartAdapter(shoppingcartProducts);
        recyclerView.setAdapter(shoppingcartAdapter);

        // Load wishlist products
        loadShoppingCartProducts();
        setTextPrice();
    }

    // Method to load wishlist products
    private void loadShoppingCartProducts() {
        // Example: Adding dummy products to the wishlist

        if(sc.cartList.size()>0){
            for(int i=0;i < sc.cartList.size();i++){
                shoppingcartProducts.add(sc.cartList.get(i).getProduct());
            }
            sc.updateTotal();
        }


        // Notify adapter about data changes
        setTextPrice();
        shoppingcartAdapter.notifyDataSetChanged();
    }
    public void setTextPrice(){
        String price = "Your Total: " + sc.getTotal();
        total_price.setText(price);
    }

    @Override
    public void onClick (View v){
        if (v == homebutton){
            //Toast.makeText(ProductDetailActivity.this, "Going Home", Toast.LENGTH_LONG).show();

            handler.navigateToHome(type);
        }


    }
}

