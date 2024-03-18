package com.example.lima_project4443.Model;

//do we need this? maybe just arraylist of products? using singleton
public class Cart_Model {

    private int quantity;
    private String color;
    private int size;


    public Cart_Model() {

    }
    public int getQuantity(){
        return this.quantity;
    }
    public String getColor(){
        return this.color;

    }
    public int getSize(){
        return this.size;
    }
    public void setQuantity (int quantity){
        this.quantity = quantity;
    }
    public void setColor (String color){
        this.color = color;
    }
    public void setSize(int size){
        this.size = size;
    }

}
