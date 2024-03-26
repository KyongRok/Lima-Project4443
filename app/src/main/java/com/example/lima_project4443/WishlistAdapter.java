package com.example.lima_project4443;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lima_project4443.Model.Product_Model;

import java.util.ArrayList;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> {

    private ArrayList<Product_Model> wishlistProducts;

    public WishlistAdapter(ArrayList<Product_Model> wishlistProducts) {
        this.wishlistProducts = wishlistProducts;
    }

    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item, parent, false);
        return new WishlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder holder, int position) {
        Product_Model product = wishlistProducts.get(position);
        holder.productNameTextView.setText(product.getProductName());
        holder.productImageView.setImageResource(product.getImageResourceId());
        // Load product image into ImageView (You need to implement this)
        // holder.productImageView.setImageResource(product.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return wishlistProducts.size();
    }

    public static class WishlistViewHolder extends RecyclerView.ViewHolder {
        ImageView productImageView;
        TextView productNameTextView;

        public WishlistViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productImageView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
        }
    }
}