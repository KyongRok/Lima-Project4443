package com.example.lima_project4443;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lima_project4443.Model.Product_Model;

import java.util.ArrayList;
import java.util.List;

public class productDisplayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private productDisplayAdapter adapter;
    private List<Product_Model> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productlayout);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        productList = new ArrayList<>();
        productList.add(new Product_Model("Nike Air Max", 200, R.drawable.pandadunks));
        productList.add(new Product_Model("Adidas UltraBoost", 180, R.drawable.pandadunks));
        productList.add(new Product_Model("Puma RS-X", 160, R.drawable.pandadunks));
        productList.add(new Product_Model("New Balance 574", 130, R.drawable.pandadunks));

        productList.add(new Product_Model("Reebok Classic", 150, R.drawable.pandadunks));
        productList.add(new Product_Model("Under Armour Curry", 170, R.drawable.pandadunks));
        productList.add(new Product_Model("Vans Old Skool", 90, R.drawable.pandadunks));
        productList.add(new Product_Model("Converse Chuck Taylor", 80, R.drawable.pandadunks));

        adapter = new productDisplayAdapter(productList);
        recyclerView.setAdapter(adapter);

        SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return false;
            }
        });

        ImageButton wishListButton = findViewById(R.id.btn_fav);
        wishListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "Hello");
                openWishlistActivity();
            }
        });
    }

    public void openWishlistActivity() {
        // Implement opening WishlistActivity here
    }
}
