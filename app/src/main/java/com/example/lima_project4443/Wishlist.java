package com.example.lima_project4443;

import com.example.lima_project4443.Model.Product_Model;

import java.util.ArrayList;

public class Wishlist {

    private static Wishlist wishInstance = null;

    public ArrayList<Product_Model> wList;

    private Wishlist(){
        wList = new ArrayList<Product_Model>();
    }
    public static synchronized Wishlist getInstance()
    {
        if (wishInstance == null)
            wishInstance = new Wishlist();

        return wishInstance;
    }
}
