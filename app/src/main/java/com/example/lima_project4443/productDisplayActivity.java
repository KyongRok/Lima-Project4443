package com.example.lima_project4443;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/*
Group Members:
KyongRok Kim, 215813413
Brian Nguyen, 217233966
Seong Su Kim, 215481575
Alexis Estropia, 217146473
 */
public class productDisplayActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private productDisplayAdapter adapter;
    private List<Shoe> shoeList; //List of shoes added to the Recycler View
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productlayout);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        int numberOfColumns = 2; // Change this number as needed
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        // Initialize shoeList (populate it with your data)
        shoeList = new ArrayList<>();
        shoeList.add(new Shoe("Nike Air Max", "$200", R.drawable.pandadunks));
        shoeList.add(new Shoe("Adidas UltraBoost", "$180", R.drawable.pandadunks));
        shoeList.add(new Shoe("Puma RS-X", "$160", R.drawable.pandadunks));
        shoeList.add(new Shoe("New Balance 574", "$130", R.drawable.pandadunks));

        shoeList.add(new Shoe("Reebok Classic", "$150", R.drawable.pandadunks));
        shoeList.add(new Shoe("Under Armour Curry", "$170", R.drawable.pandadunks));
        shoeList.add(new Shoe("Vans Old Skool", "$90", R.drawable.pandadunks));
        shoeList.add(new Shoe("Converse Chuck Taylor", "$80", R.drawable.pandadunks));



        // Initialize adapter with the shoeList
        adapter = new productDisplayAdapter(shoeList);

        // Set adapter to RecyclerView
        recyclerView.setAdapter(adapter);

        searchView = findViewById(R.id.search_view);
        searchView.setQueryHint("Search your shoes"); // set empty query so the "Search your shoes" text will display

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

        LinearLayout searchContainer = findViewById(R.id.searchContainer);

        // Set onClickListener to the search container
        searchContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Request focus on the SearchView to expand it
                searchView.setIconified(false);
                searchView.requestFocus();
            }
        });

    }

}
