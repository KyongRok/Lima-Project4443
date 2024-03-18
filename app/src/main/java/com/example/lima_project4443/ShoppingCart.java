package com.example.lima_project4443;

import java.util.ArrayList;

public class ShoppingCart {

    private static ShoppingCart cartInstance = null;

    public ArrayList<CartItem> cartArray;

    private ShoppingCart(){
        cartArray = new ArrayList<CartItem>();


    }


    public static synchronized ShoppingCart getInstance()
    {
        if (cartInstance == null)
            cartInstance = new ShoppingCart();

        return cartInstance;
    }
}
