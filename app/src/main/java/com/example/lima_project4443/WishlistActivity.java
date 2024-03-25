package com.example.lima_project4443;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WishlistActivity extends AppCompatActivity {

    private Wishlist wishlistInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wishlist);

        wishlistInstance = Wishlist.getInstance();
        //wishlistInstance.wList.add()
        RecyclerView recyclerView = findViewById(R.id.wishlistRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        WishlistAdapter adapter = new WishlistAdapter(wishlistInstance.wList);
        recyclerView.setAdapter(adapter);
    }
}

