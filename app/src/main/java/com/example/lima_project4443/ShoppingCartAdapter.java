package com.example.lima_project4443;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lima_project4443.Model.Product_Model;

import java.text.BreakIterator;
import java.util.ArrayList;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartViewHolder> {

    private ArrayList<Product_Model> ShoppingCartProducts;
    private Wishlist wl = Wishlist.getInstance();
    private ShoppingCart cart = ShoppingCart.getInstance();
    private String type;

    public ShoppingCartAdapter(ArrayList<Product_Model> ShoppingCartProducts,String type) {
        this.ShoppingCartProducts = ShoppingCartProducts;
        this.type = type;
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
        holder.productPrice.setText("$"+Double.toString( product.getPrice() * cart.cartList.get(position).getQuantity()));
        holder.qtyText.setText(Integer.toString(cart.cartList.get(position).getQuantity()));
        holder.sizeText.setText(Integer.toString(cart.cartList.get(position).getSize()));
        holder.removeText.setVisibility(View.GONE);
        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove the item from the wishlist
                cart.cartList.remove(position);
                ShoppingCartProducts.remove(position);
                // Notify adapter about the item removal
                cart.updateTotal();
                notifyDataSetChanged();
                Context context = v.getContext();
                Intent i = new Intent(context,ShoppingCartActivity.class);
                Bundle b = new Bundle();
                b.putString("type",type);
                i.putExtras(b);
                context.startActivity(i);
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

        holder.qtyPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currQty = cart.cartList.get(position).getQuantity();
                cart.cartList.get(position).setQuantity(currQty + 1);
                cart.updateTotal();
                notifyDataSetChanged();
                Context context = v.getContext();
                Intent i = new Intent(context,ShoppingCartActivity.class);
                Bundle b = new Bundle();
                b.putString("type",type);
                i.putExtras(b);
                context.startActivity(i);

            }
        });

        holder.qtyMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currQty = cart.cartList.get(position).getQuantity();
                if(currQty == 1){
                    cart.cartList.remove(position);
                    ShoppingCartProducts.remove(position);
                    // Notify adapter about the item removal
                    cart.updateTotal();
                    notifyDataSetChanged();
                    Context context = v.getContext();
                    Intent i = new Intent(context,ShoppingCartActivity.class);
                    Bundle b = new Bundle();
                    b.putString("type",type);
                    i.putExtras(b);
                    context.startActivity(i);
                }else{
                    cart.cartList.get(position).setQuantity(currQty - 1);
                    cart.updateTotal();
                    notifyDataSetChanged();
                    Context context = v.getContext();
                    Intent i = new Intent(context,ShoppingCartActivity.class);
                    Bundle b = new Bundle();
                    b.putString("type",type);
                    i.putExtras(b);
                    context.startActivity(i);
                }
            }
        });

        holder.sizeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currSize = cart.cartList.get(position).getSize();
                cart.cartList.get(position).setSize(currSize+1);
                notifyDataSetChanged();
            }
        });

        holder.sizeDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currSize = cart.cartList.get(position).getSize();
               if(currSize != 1){
                  cart.cartList.get(position).setSize(currSize-1);
                   notifyDataSetChanged();
               }
            }
        });

    }

    @Override
    public int getItemCount() {
        return ShoppingCartProducts.size();
    }

    public static class ShoppingCartViewHolder extends RecyclerView.ViewHolder {
        TextView productPrice;
        ImageView productImageView;
        TextView productNameTextView, removeText,qtyText,sizeText;
        ImageButton removeButton,qtyPlus,qtyMinus,sizeUp,sizeDown;

        public ShoppingCartViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productImageView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            productPrice = itemView.findViewById(R.id.productPriceTextView);
            removeButton = itemView.findViewById(R.id.removeButton);
            removeText = itemView.findViewById(R.id.removeButtonTextView);
            qtyPlus = itemView.findViewById(R.id.qtyPlus);
            qtyMinus = itemView.findViewById(R.id.qtyMinus);
            sizeUp = itemView.findViewById(R.id.sizePlus);
            sizeDown = itemView.findViewById(R.id.sizeMinus);
            qtyText = itemView.findViewById(R.id.qtytext);
            sizeText = itemView.findViewById(R.id.sizetext);

        }

    }
}