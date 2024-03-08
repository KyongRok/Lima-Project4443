package com.example.lima_project4443;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/*
Group Members:
KyongRok Kim, 215813413
Brian Nguyen, 217233966
Seong Su Kim, 215481575
Alexis Estropia, 217146473
 */
public class productDisplayActivity extends AppCompatActivity {

    private static class Shoe {
        private String name;
        private int imageResourceId;

        public Shoe(String name, int imageResourceId) {
            this.name = name;
            this.imageResourceId = imageResourceId;
        }

        public String getName() {
            return name;
        }

        public int getImageResourceId() {
            return imageResourceId;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productlayout);


        /** Attempted to do the
        Shoe pandaShoes = new Shoe("Nike", R.drawable.pandadunks);

        ConstraintLayout itemShoeLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.shoe, null);

        ImageView imageShoe = itemShoeLayout.findViewById(R.id.image_shoe);
        imageShoe.setImageResource(pandaShoes.getImageResourceId());

        TextView textShoeName = itemShoeLayout.findViewById(R.id.text_shoe_name);
        textShoeName.setText(pandaShoes.getName());

        ViewGroup mainLayout = findViewById(R.id.product_layout);
        mainLayout.addView(itemShoeLayout);
        Log.d("MainActivity", "Main layout ID: " + mainLayout);
        **/

        //Bottom Buttons
        ImageButton btnHome = findViewById(R.id.btn_home_bottom);
        ImageButton btnCart = findViewById(R.id.btn_cart);
        ImageButton btnProfile = findViewById(R.id.btn_profile);

        //Buttons from shoe.xml
        Button addToCart = findViewById(R.id.addToCart);

        // Set OnClickListener for all buttons
        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_home_bottom) {
                    // Handle Home button click
                    Log.d("productDisplayActivity", "Home button clicked");
                } else if (v.getId() == R.id.btn_cart) {
                    // Handle Cart button click
                    Log.d("productDisplayActivity", "Cart button clicked");
                } else if (v.getId() == R.id.btn_profile) {
                    // Handle Profile button click
                    Log.d("productDisplayActivity", "Profile button clicked");
                } else if (v.getId() == R.id.addToCart) {
                    // Handle Add to Cart button click
                    Log.d("productDisplayActivity", "Add to Cart button clicked");
                }
            }
        };

        // Assign the click listener to all buttons
        btnHome.setOnClickListener(buttonClickListener);
        btnCart.setOnClickListener(buttonClickListener);
        btnProfile.setOnClickListener(buttonClickListener);
        addToCart.setOnClickListener(buttonClickListener);
    }





    }


