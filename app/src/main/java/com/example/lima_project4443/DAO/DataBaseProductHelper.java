package com.example.lima_project4443.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lima_project4443.Model.Login_Model;
import com.example.lima_project4443.Model.Product_Model;

import java.util.ArrayList;

public class DataBaseProductHelper extends SQLiteOpenHelper {


    public DataBaseProductHelper(@Nullable Context context) {
        super(context, "Product.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTableStatement = "CREATE TABLE PRODUCT_TABLE (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "PRODUCT_NAME TEXT, " +
                "PRICE REAL, " +
                "RATING REAL, " +
                "COLOR TEXT, " +
                "BRAND TEXT, " +
                "IMAGEID INTEGER)";

        db.execSQL(CreateTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertNewProduct(Product_Model product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //inserts into database
        cv.put("PRODUCT_NAME" , product.getProductName());
        cv.put("PRICE" , product.getPrice());
        cv.put("RATING" , product.getRating());
        cv.put("COLOR" , product.getColor());
        cv.put("BRAND" , product.getBrand());
        cv.put("IMAGEID" , product.getImageResourceId());
        long productTable = db.insert("PRODUCT_TABLE", null, cv);
        //this fucntion has to be called here because SQLITE LAST_INSERT_ROWID()
        //gets the row that was last edited in the same db connection, so before db close
        getProductId(product);
        db.close();
        return productTable == 1;
    }

    public void getProductId(Product_Model product){
        //ID of the product are required for shopping cart, wishlist and later adding
        //set the id of the product from auto incremented values
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "SELECT LAST_INSERT_ROWID();";
        Cursor cursor = db.rawQuery(Query,null);
        if(cursor != null){
            cursor.moveToFirst();
            long id = cursor.getInt(0);
            product.setProductId(id);
            cursor.close();
        }
    }

    public ArrayList<Product_Model> filterProduct(ArrayList<String> filter) {
        //filter can be multiple, hence takes in as arraylist
        //returns array list of filtered product model object
        ArrayList<Product_Model> result = new ArrayList<>();
        String target = "";
        for (int i = 0; i < filter.size(); i++) {
            if (i == filter.size() - 1) {
                //if last one dont add ,
                target += "'";
                target += filter.get(i);
                target += "'";
            } else {
                target += "'";
                target += filter.get(i);
                target += "',";
            }
        }
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "SELECT * FROM PRODUCT_TALBE " +
                "WHERE PRODUCT_NAME IN (" + target + ") OR " +
                "BRAND IN (" + target + ") OR " +
                "COLOR IN (" + target + ")";
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                Product_Model p = new Product_Model();
                p.setProductId(cursor.getInt(0));
                p.setProductName(cursor.getString(1));
                p.setPrice(cursor.getFloat(2));
                p.setRating(cursor.getFloat(3));
                p.setColor(cursor.getString(4));
                p.setBrand(cursor.getString(5));
                p.setImageResourceId(cursor.getInt(6));

                result.add(p);
            }
            cursor.close();
        }

        db.close();
        return result;
    }


    public ArrayList<Product_Model> searchProduct(String target){
        //shows up anything related to target string, could be name, brand, ect...
        ArrayList<Product_Model> result = new ArrayList<>();



        return result;
    }

}
