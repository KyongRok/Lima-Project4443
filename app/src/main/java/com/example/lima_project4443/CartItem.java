package com.example.lima_project4443;

import com.example.lima_project4443.Model.Product_Model;

public class CartItem {
    private Product_Model product;
    private int quantity;
    private int size;

    public CartItem(Product_Model product, int quantity, int size){
        this.product = product;
        this.quantity = quantity;
        this.size = size;
    }
    public Product_Model getProduct(){
        return this.product;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public int getSize(){
        return this.size;
    }
    public void setProduct(Product_Model product){
        this.product = product;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public void setSize(int size){
        this.size = size;
    }



}
