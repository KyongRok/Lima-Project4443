package com.example.lima_project4443;

import android.content.Context;
import android.content.Intent;
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

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartViewHolder> {

    private ArrayList<Product_Model> ShoppingCartProducts;
    private Wishlist wl = Wishlist.getInstance();
    private ShoppingCart cart = ShoppingCart.getInstance();

    public ShoppingCartAdapter(ArrayList<Product_Model> ShoppingCartProducts) {
        this.ShoppingCartProducts = ShoppingCartProducts;
    }

    @NonNull
    @Override
    public ShoppingCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoppingcart_item, parent, false);
        return new ShoppingCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingCartViewHolder holder, int position) {
        Product_Model product = ShoppingCartProducts.get(position);
        holder.productNameTextView.setText(product.getProductName());
        holder.productImageView.setImageResource(product.getImageResourceId());
        holder.removeText.setVisibility(View.GONE);
        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove the item from the wishlist
                cart.cartList.remove(position);
                ShoppingCartProducts.remove(position);
                // Notify adapter about the item removal
                notifyDataSetChanged();
            }
        });
        holder.productImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Context context = v.getContext();
                String productName = cart.cartList.get(position).getProductName();
                Intent i = new Intent(context,ProductDetailActivity.class);
                i.putExtra("product_name",productName);
                context.startActivity(i);
            }
        });

        /**
         //Turn the product into cartItem, and
         holder.addCartButton.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
        CartItem ci = new CartItem() wl.wList.get(position);
        }
        });
         **/
    }

    @Override
    public int getItemCount() {
        return ShoppingCartProducts.size();
    }

    public static class ShoppingCartViewHolder extends RecyclerView.ViewHolder {
        ImageView productImageView;
        TextView productNameTextView, removeText;
        ImageButton removeButton,addCartButton;

        public ShoppingCartViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productImageView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            removeButton = itemView.findViewById(R.id.removeButton);
            removeText = itemView.findViewById(R.id.removeButtonTextView);
        }

    }
}