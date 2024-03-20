package com.example.lima_project4443;

import android.content.Context;
import android.content.Intent;

public class NavigationHandler {

    private Context context;


    public NavigationHandler(Context context){
        this.context = context;
    }
    public void navigateToHome() {
        context.startActivity(new Intent(context, productDisplayActivity.class));
    }
    public void navigateToProfile(){
        context.startActivity(new Intent(context,ProfileActivity.class));
    }
    public void navigateToCart(){
        //navigate to Cart
    }
    public void navigateToWishlist(){
        //navigate to Wishlist
    }

    ///more to come
}
