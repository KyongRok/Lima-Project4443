package com.example.lima_project4443;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lima_project4443.Model.Product_Model;

import java.util.ArrayList;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> {

    private ArrayList<Product_Model> wishlistProducts;
    private ImageButton homeButton,profileButton,deleteButton,cartButton;

    public WishlistAdapter(ArrayList<Product_Model> wishlistProducts) {
        this.wishlistProducts = wishlistProducts;
    }

    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist, parent, false);
        return new WishlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder holder, int position) {
        Product_Model product = wishlistProducts.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return wishlistProducts.size();
    }

    public static class WishlistViewHolder extends RecyclerView.ViewHolder {
        private TextView productName;

        public WishlistViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productNameTextView);
        }

        public void bind(Product_Model product) {
            productName.setText(product.getProductName());
            // Bind other product information here if needed
        }
    }
}
