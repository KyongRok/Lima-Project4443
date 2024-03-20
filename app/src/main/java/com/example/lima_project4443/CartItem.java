package com.example.lima_project4443;

public class CartItem {
    private Shoe product;
    private int quantity;
    private int size;

    public CartItem(Shoe product, int quantity, int size){
        this.product = product;
        this.quantity = quantity;
        this.size = size;
    }
    public Shoe getProduct(){
        return this.product;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public int getSize(){
        return this.size;
    }
    public void setProduct(Shoe product){
        this.product = product;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public void setSize(int size){
        this.size = size;
    }



}
