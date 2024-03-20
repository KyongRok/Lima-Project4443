package com.example.lima_project4443;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lima_project4443.Model.Product_Model;

import java.util.ArrayList;
import java.util.List;

public class productDisplayAdapter extends RecyclerView.Adapter<productDisplayAdapter.ViewHolder> {

    private static List<Product_Model> shoeList;
    private final ArrayList<Product_Model> filteredShoeList; // Add a filtered list to store filtered items
    private final ArrayList<Product_Model> favouriteShoeList;
    public productDisplayAdapter(List<Product_Model> shoeList) {

        this.shoeList = shoeList;
        this.filteredShoeList = new ArrayList<>(shoeList);
        this.favouriteShoeList = new ArrayList<>(shoeList);
    }

    public void filter(String q){

        filteredShoeList.clear();
        if(q.isEmpty()){
            filteredShoeList.addAll(shoeList);
        }
        else{
            q = q.toLowerCase();
            for (Product_Model shoe: shoeList){
                if (shoe.getProductName().toLowerCase().contains(q)){
                    filteredShoeList.add(shoe);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoe, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public int getItemCount() {

        return filteredShoeList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product_Model shoe = filteredShoeList.get(position);
        holder.imageView.setImageResource(shoe.getImageResourceId());
        holder.textViewName.setText(shoe.getProductName());
        holder.priceViewName.setText(String.valueOf(shoe.getPrice()));



    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewName, priceViewName;
        public Button buttonAddToCart;

        public ImageButton buttonAddToWishList;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image_shoe);
            textViewName = view.findViewById(R.id.text_shoe_name);
            priceViewName = view.findViewById(R.id.price);
            buttonAddToCart = view.findViewById(R.id.addToCart);
            buttonAddToWishList = view.findViewById(R.id.addToWishList);

            // Set OnClickListener for the button
            buttonAddToCart.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View v) {
                                                       int position = getAdapterPosition(); //Based off of which shoe we are interacting with (Shoe 1 = Position 0)
                                                       Log.d("TEST","AddToCartButton");
                                                       Product_Model selectedShoe = shoeList.get(position);
                                                       addToCart(selectedShoe);
                                                   }
                                               }
            );

            buttonAddToWishList.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int position = getAdapterPosition();
                    Log.d("TEST","AddToWishListButton");
                    if(position != RecyclerView.NO_POSITION){
                        Product_Model selectedShoe = shoeList.get(position);
                        addToWishList(selectedShoe);
                    }

                }
            });

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Log.d("TEST", "Image button clicked at position: " + position);
                    if (position != RecyclerView.NO_POSITION) {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ProductDetailActivity.class);
                        intent.putExtra("product_name", shoeList.get(position).getProductName());
                        context.startActivity(intent);
                    }
                }
            });

        }


        private void addToCart(Product_Model selectedShoe) {
        }
        private void addToWishList(Product_Model selectedShoe) {
        }


    }
}