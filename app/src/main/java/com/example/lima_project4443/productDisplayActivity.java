package com.example.lima_project4443;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lima_project4443.DAO.DataBaseProductHelper;
import com.example.lima_project4443.Model.Product_Model;

import java.util.ArrayList;
import java.util.List;

public class productDisplayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private productDisplayAdapter adapter;
    private List<Product_Model> productList;
    DataBaseProductHelper dataBaseProduct = new DataBaseProductHelper(productDisplayActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productlayout);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        productList = new ArrayList<>();
        if(!dataBaseProduct.databasePopulated()){
            dataBaseProduct.populateInitialDatabase();
        }
        //productList = dataBaseProduct.searchAll();
        productList = dataBaseProduct.searchProduct("adidas");
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