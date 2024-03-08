package com.example.lima_project4443;

import android.os.Bundle;

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
public class productDisplayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<Shoe> shoeList; //List of shoes added to the Recycler View

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
        shoeList.add(new Shoe("Nike Panda Dunks", R.drawable.pandadunks));
        shoeList.add(new Shoe("Nike", R.drawable.pandadunks));
        shoeList.add(new Shoe("Nike", R.drawable.pandadunks));
        shoeList.add(new Shoe("Nike", R.drawable.pandadunks));


        // Initialize adapter with the shoeList
        adapter = new MyAdapter(shoeList);

        // Set adapter to RecyclerView
        recyclerView.setAdapter(adapter);


    }


}
