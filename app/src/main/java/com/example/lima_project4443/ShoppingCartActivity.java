package com.example.lima_project4443;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
    private ImageButton homebutton,wlbutton,profilebutton,checkoutbutton;
    private TextView  total_price,checkouttext,hometext,carttext,profiletext,favtext;
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
        wlbutton = findViewById(R.id.btn_fav);
        wlbutton.setOnClickListener(this);
        profilebutton = findViewById(R.id.btn_profile);
        profilebutton.setOnClickListener(this);
        checkoutbutton = findViewById(R.id.checkoutbtn);
        checkoutbutton.setOnClickListener(this);
        checkouttext = findViewById(R.id.checkoutText);
        hometext=findViewById(R.id.hometext);
        carttext=findViewById(R.id.carttext);
        profiletext=findViewById(R.id.profiletext);
        favtext = findViewById(R.id.favtext);
        checkouttext = findViewById(R.id.checkoutText);
        checkouttext.setOnClickListener(this);
        if(!type.equals("A")){
            hometext.setVisibility(View.GONE);
            carttext.setVisibility(View.GONE);
            profiletext.setVisibility(View.GONE);
            favtext.setVisibility(View.GONE);
            checkouttext.setVisibility(View.GONE);
        }
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
        if(v==checkoutbutton ||v==checkouttext){
            if(sc.cartList.size() > 0) {
                Context context = v.getContext();
                Intent intent = new Intent(context, Checkout.class);
                Bundle b = new Bundle();
                b.putString("type", type);
                intent.putExtras(b);
                context.startActivity(intent);
            }
            else{
                Toast.makeText(ShoppingCartActivity.this, "The cart is empty", Toast.LENGTH_SHORT).show();

            }
        }
        if (v == homebutton){
            //Toast.makeText(ProductDetailActivity.this, "Going Home", Toast.LENGTH_LONG).show();

            handler.navigateToHome(type);
        }
        if(v==wlbutton){
            handler.navigateToWishlist(type);
        }
        if(v==profilebutton){
            handler.navigateToProfile(type);
        }




    }
}

