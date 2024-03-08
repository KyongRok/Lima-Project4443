package com.example.lima_project4443;

import android.os.Parcel;
import android.os.Parcelable;

public class Shoe{
    private String name;
    private int imageResourceId;
    private String price;

    public Shoe(String name, String price, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.price = price;
    }


    public String getName() {

        return name;
    }

    public String getPrice() {
        return
                price;
    }

    public int getImageResourceId() {

        return imageResourceId;
    }

}
