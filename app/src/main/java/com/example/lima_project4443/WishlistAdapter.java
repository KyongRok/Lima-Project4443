package com.example.lima_project4443;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> {

    private ArrayList<Product_Model> wishlistProducts;
    private Wishlist wl = Wishlist.getInstance();
    private ShoppingCart cart = ShoppingCart.getInstance();
    private String type;

    public WishlistAdapter(ArrayList<Product_Model> wishlistProducts,String type) {
        this.wishlistProducts = wishlistProducts;
        this.type = type;
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
        if(type.equals("B")) {
            holder.removeText.setVisibility(View.GONE);
        }
        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove the item from the wishlist
                wl.wList.remove(position);
                wishlistProducts.remove(position);
                // Notify adapter about the item removal
                notifyDataSetChanged();
            }
        });
        holder.productImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Context context = v.getContext();
                String productName = wl.wList.get(position).getProductName();
                Intent i = new Intent(context,ProductDetailActivity.class);
                i.putExtra("product_name",productName);
                context.startActivity(i);
            }
        });


          //Turn the product into cartItem, and
        holder.addCartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               Product_Model selectedShoe = wl.wList.get(position);
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
                    Toast.makeText(v.getContext(), "You have "+current_qty+" of this item in your cart", Toast.LENGTH_SHORT).show();
                }else{
                    cart.cartList.add(new CartItem(selectedShoe,1,5));
                    Toast.makeText(v.getContext(), "Item has been added to your cart", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return wishlistProducts.size();
    }

    public static class WishlistViewHolder extends RecyclerView.ViewHolder {
        ImageView productImageView;
        TextView productNameTextView, removeText;
        ImageButton removeButton,addCartButton;

        public WishlistViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productImageView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            removeButton = itemView.findViewById(R.id.removeButton);
            removeText = itemView.findViewById(R.id.removeButtonTextView);
            addCartButton = itemView.findViewById(R.id.addcartButton);
        }
    }
}