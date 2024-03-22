package com.example.lima_project4443;

import java.util.ArrayList;

public class Wishlist {

    private static Wishlist wishInstance = null;

    public ArrayList<Shoe> wList;

    private Wishlist(){
        wList = new ArrayList<Shoe>();
    }
    public static synchronized Wishlist getInstance()
    {
        if (wishInstance == null)
            wishInstance = new Wishlist();

        return wishInstance;
    }
}
