package com.example.lima_project4443.Model;

import java.io.Serializable;

public class Product_Model implements Serializable {
    private String productName;
    private float price;
    private double rating;
    private String color;
    private long productId;
    private String brand;
    private int imageResourceId;
    public Product_Model(){

    }

    public Product_Model(String name, float price, double rating, String color, String brand, int image){
        this.productName = name;
        this.price = price;
        this.rating = rating;
        this.color = color;
        this.brand = brand;
        this.imageResourceId = image;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
    public void setProductName(String name){
        this.productName = name;
    }
    public void setImageResourceId(int id){this.imageResourceId = id;}

    public void setPrice(float price){
        this.price = price;
    }
    public void setRating(float rating){
        this.rating = rating;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }

    public long getProductId(){
        return this.productId;
    }
    public int getImageResourceId(){return this.imageResourceId;}
    public String getProductName(){
        return this.productName;
    }
    public float getPrice(){
        return this.price;
    }
    public double getRating(){
        return this.rating;
    }
    public String getColor(){
        return this.color;
    }
    public String getBrand(){return this.brand;}
}
