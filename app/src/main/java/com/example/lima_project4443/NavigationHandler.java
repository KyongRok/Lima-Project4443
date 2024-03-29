package com.example.lima_project4443;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class NavigationHandler {

    private Context context;


    public NavigationHandler(Context context){
        this.context = context;
    }

    //add type?
    public void navigateToHome(String type) {
        Intent i = new Intent(context, productDisplayActivity.class);
        Bundle b = new Bundle();
        b.putString("type",type);
        i.putExtras(b);
        context.startActivity(i);
    }
    public void navigateToProfile(String type){
        Intent i = new Intent(context, ProfileActivity.class);
        Bundle b = new Bundle();
        b.putString("type",type);
        i.putExtras(b);
        context.startActivity(i);
    }
    public void navigateToCart(String type){
        Intent i = new Intent(context, ShoppingCartActivity.class);
        Bundle b = new Bundle();
        b.putString("type",type);
        i.putExtras(b);
        context.startActivity(i);
    }
    public void navigateToWishlist(String type){
        Intent i = new Intent(context, WishlistActivity.class);
        Bundle b = new Bundle();
        b.putString("type",type);
        i.putExtras(b);
        context.startActivity(i);
    }

    ///more to come
}
