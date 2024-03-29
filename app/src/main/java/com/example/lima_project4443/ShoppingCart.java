package com.example.lima_project4443;

import java.util.ArrayList;

public class ShoppingCart {

    private static ShoppingCart cartInstance = null;

    public ArrayList<CartItem> cartList;
    public float total;

    private ShoppingCart(){
        cartList = new ArrayList<CartItem>();
        total = 0;
    }

    public void updateTotal(){
        total = 0;
        for(int i = 0; i<cartList.size(); i++){
            total += cartList.get(i).getProduct().getPrice() * cartList.get(i).getQuantity();

        }
    }

    public float getTotal(){
        return this.total;
    }


    public static synchronized ShoppingCart getInstance()
    {
        if (cartInstance == null)
            cartInstance = new ShoppingCart();

        return cartInstance;
    }
}
