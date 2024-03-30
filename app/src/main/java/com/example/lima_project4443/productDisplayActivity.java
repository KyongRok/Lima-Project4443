package com.example.lima_project4443;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lima_project4443.DAO.DataBaseProductHelper;
import com.example.lima_project4443.Model.Product_Model;

import java.util.ArrayList;
import java.util.List;

public class productDisplayActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private productDisplayAdapter adapter;
    private List<Product_Model> productList;
    private List<String> selectedFilters = new ArrayList<>();
    private NavigationHandler handler ;
    private ImageButton homebutton,wlbutton,cartbutton,profilebutton;
    private String type;
    private TextView welcome,hometext,carttext,profiletext,favtext;
    DataBaseProductHelper dataBaseProduct = new DataBaseProductHelper(productDisplayActivity.this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        if(type == null){
            type = "A";
        }
        setContentView(R.layout.productlayout);
        homebutton = findViewById(R.id.btn_home_bottom);
        homebutton.setOnClickListener(this);
        wlbutton = findViewById(R.id.btn_fav);
        wlbutton.setOnClickListener(this);
        cartbutton = findViewById(R.id.btn_cart);
        cartbutton.setOnClickListener(this);
        profilebutton = findViewById(R.id.btn_profile);
        profilebutton.setOnClickListener(this);
        welcome = findViewById(R.id.textView2);
        welcome.setText("Welcome Back, "+ UserInfo.getInstance().getfname());
        hometext=findViewById(R.id.hometext);
        carttext=findViewById(R.id.carttext);
        profiletext=findViewById(R.id.profiletext);
        favtext = findViewById(R.id.favtext);
        if(!type.equals("A")){
            hometext.setVisibility(View.GONE);
            carttext.setVisibility(View.GONE);
            profiletext.setVisibility(View.GONE);
            favtext.setVisibility(View.GONE);
        }
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        productList = new ArrayList<>();
        handler = new NavigationHandler(this);
        if(!dataBaseProduct.databasePopulated()){
            dataBaseProduct.populateInitialDatabase();
        }
        productList = dataBaseProduct.searchAll();
        //productList = dataBaseProduct.searchProduct("adidas");
        adapter = new productDisplayAdapter(productList);
        recyclerView.setAdapter(adapter);

        ImageButton filterButton = findViewById(R.id.menu_button);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilterMenu();
            }
        });


        SearchView searchView = findViewById(R.id.search_view);
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

    @Override
    public void onClick (View v){
        if(v== homebutton){
            handler.navigateToHome(type);
        }
        if(v==wlbutton){
            handler.navigateToWishlist(type);
        }
        if(v==cartbutton){
            handler.navigateToCart(type);
        }
        if(v==profilebutton){
            handler.navigateToProfile(type);
        }

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
        adapter.searchFilter(selectedFilters);
    }


    public String displaySearchResultsAsString() {
        String searchResults = adapter.getSearchShoeListAsString();

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