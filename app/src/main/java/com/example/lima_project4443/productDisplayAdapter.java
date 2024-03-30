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

    private static ArrayList<Product_Model> searchShoeList; // Add a filtered list to store filtered items
    private final ArrayList<Product_Model> favouriteShoeList;
    private List<String> brands;
    private List<String> colors;
    public productDisplayAdapter(List<Product_Model> shoeList) {
        brands = new ArrayList<String>();
        colors = new ArrayList<String>();
        brands.add("Nike"); brands.add("Adidas"); brands.add("Puma"); brands.add("New Balance");
        brands.add("Reebok"); brands.add("Under Armour"); brands.add("Vans"); brands.add("Converse");

        colors.add("Red");  colors.add("Blue");  colors.add("Black");
        colors.add("Yellow");  colors.add("Green");  colors.add("White");
        this.shoeList = shoeList;
        this.searchShoeList = new ArrayList<>(shoeList);

        this.favouriteShoeList = new ArrayList<>(shoeList);
    }

    public void search(String q){

        searchShoeList.clear();
        if(q.isEmpty()){
            searchShoeList.addAll(shoeList);
        }
        else{
            q = q.toLowerCase();
            for (Product_Model shoe: shoeList){
                if (shoe.getProductName().toLowerCase().contains(q)){
                    searchShoeList.add(shoe);
                }
            }
        }
        notifyDataSetChanged();
        // Display search results as a string

    }

    public void searchFilter(List<String> arr){
        searchShoeList.clear();
        if(arr.isEmpty()){
            searchShoeList.addAll(shoeList);
        }
        else{
            for(String str : arr){
                if(brands.contains(str)){

                    for(Product_Model p : shoeList){
                        if(p.getBrand().equals(str)){
                            searchShoeList.add(p);
                        }
                    }
                }else if(colors.contains(str)){

                    for(Product_Model p : shoeList){
                        if(p.getColor().equals(str)){
                            searchShoeList.add(p);
                        }
                    }
                }


            }
        }
        notifyDataSetChanged();
    }

    public String getSearchShoeListAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Product_Model shoe : searchShoeList) {
            stringBuilder.append(shoe.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoe, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public int getItemCount() {

        return searchShoeList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product_Model shoe = searchShoeList.get(position);
        holder.imageView.setImageResource(shoe.getImageResourceId());
        holder.textViewName.setText(shoe.getProductName());
        holder.priceViewName.setText(String.valueOf(shoe.getPrice()));



    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewName, priceViewName;
        public Button buttonAddToCart;

        public ImageButton buttonAddToWishList;
        private ShoppingCart cart = ShoppingCart.getInstance();

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
                        intent.putExtra("product_name", searchShoeList.get(position).getProductName());
                        context.startActivity(intent);
                    }
                }
            });

        }


        private void addToCart(Product_Model selectedShoe) {
            int flag_found = 0;
            int index = 0;
            for(int i =0; i < cart.cartList.size(); i++){
                if(selectedShoe.getProductId() == cart.cartList.get(i).getProduct().getProductId()){
                    flag_found = 1;
                    index = i;
                }
            }
            if(flag_found == 1){
                int current_qty = cart.cartList.get(index).getQuantity();
                cart.cartList.get(index).setQuantity(current_qty + 1);
            }else{
                cart.cartList.add(new CartItem(selectedShoe,1,5));
            }
        }
        private void addToWishList(Product_Model selectedShoe) {
        }


    }
}