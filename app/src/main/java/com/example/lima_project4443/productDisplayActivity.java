package com.example.lima_project4443;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lima_project4443.DAO.DataBaseProductHelper;
import com.example.lima_project4443.Model.Product_Model;

import java.util.ArrayList;
import java.util.List;

public class productDisplayActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private productDisplayAdapter adapter;
    private List<Product_Model> productList;
    private List<String> selectedFilters = new ArrayList<>();

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
        productList = dataBaseProduct.searchAll();
        adapter = new productDisplayAdapter(productList);
        recyclerView.setAdapter(adapter);

        ImageButton filterButton = findViewById(R.id.menu_button);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilterMenu();
            }
        });


        ImageButton filterButton = findViewById(R.id.menu_button);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilterMenu();
            }
        });


        searchView = findViewById(R.id.search_view);
        searchView.setQueryHint("Search your shoes"); // set empty query so the "Search your shoes" text will display

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.search(query);
                displaySearchResultsAsString();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.search(newText);
                displaySearchResultsAsString();
                return false;
            }
        });

    }

    private void showFilterMenu() {
        PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.menu_button));
        popupMenu.getMenuInflater().inflate(R.menu.filter_menu, popupMenu.getMenu());

        // Iterate over menu items and set their checked state based on selectedFilters list
        Menu menu = popupMenu.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            String title = menuItem.getTitle().toString();
            menuItem.setChecked(selectedFilters.contains(title)); // Check if the filter is already selected
            if (!title.equals("Brand")) {
                menuItem.setChecked(selectedFilters.contains(title)); // Check if the filter is already selected
            }
        }

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                toggleFilter(item.getTitle().toString()); // Pass the filter title as a string
                item.setChecked(!item.isChecked()); // Toggle the checked state of the item
                return true;
            }
        });
        popupMenu.show();
    }

    private void toggleFilter(String filter) {
        if (selectedFilters.contains(filter)) {
            selectedFilters.remove(filter);
        } else {
            selectedFilters.add(filter);
        }
        String selectedFiltersString = displaySelectedFiltersAsString(selectedFilters);
        Log.d("Filter", selectedFiltersString);
    }


    public String displaySearchResultsAsString() {
        String searchResults = adapter.getSearchShoeListAsString();
        Log.d("Display Search Results", searchResults);
        return searchResults;
    }

    public String displaySelectedFiltersAsString(List<String> selectedFilters) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean isFirstFilter = true;
        for (String filter : selectedFilters) {
            if (filter.equals("Brand") || filter.equals("Color")) {
                continue;
            }
            if (!isFirstFilter) {
                stringBuilder.append(",");
            } else {
                isFirstFilter = false;
            }
            stringBuilder.append(filter);
        }
        return stringBuilder.toString();
    }



}