package com.example.lima_project4443.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lima_project4443.Model.Product_Model;
import com.example.lima_project4443.R;

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

    public boolean databasePopulated(){
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "SELECT * FROM PRODUCT_TABLE ";
        Cursor cursor = db.rawQuery(Query, null);

        if(cursor.getCount() > 0){
            cursor.close();
            return true;
        }else{
            cursor.close();
            return false;
        }
    }

    public void populateInitialDatabase(){
        Product_Model Nike_air_max = new Product_Model("Nike Air Max", 200, 4.5, "Black" , "Nike" , R.drawable.nike_air_max);
        Product_Model UltraBoost = new Product_Model("Adidas UltraBoost", 180, 4.2, "White" , "Adidas" , R.drawable.ultraboost);
        Product_Model Puma_RSX = new Product_Model("Puma RS-X", 160, 4.0, "White" , "Puma" , R.drawable.rsx);
        Product_Model NewBalance574 = new Product_Model("New Balance 574", 130, 4.8, "Khaki" , "New Balance" , R.drawable.nb574);
        Product_Model Reebok_classic = new Product_Model("Reebok Classic", 150, 4.2, "White" , "Reebok" , R.drawable.reebokclassic);
        Product_Model UA_Curry = new Product_Model("Under Armour Curry", 170, 4.6, "Yellow" , "Under Armour" , R.drawable.curry);
        Product_Model Vans_oldSkool = new Product_Model("Vans Old Skool", 90, 4.9, "Black" , "Vans" , R.drawable.oldskool);
        Product_Model Converse_Chuck = new Product_Model("Converse Chuck Taylor", 80, 4.8, "Black" , "Converse" , R.drawable.chucktaylor);

        insertNewProduct(Nike_air_max);
        insertNewProduct(UltraBoost);
        insertNewProduct(Puma_RSX);
        insertNewProduct(NewBalance574);
        insertNewProduct(Reebok_classic);
        insertNewProduct(UA_Curry);
        insertNewProduct(Vans_oldSkool);
        insertNewProduct(Converse_Chuck);
    }


    public void insertNewProduct(Product_Model product){
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
        String Query = "SELECT * FROM PRODUCT_TABLE " +
                "WHERE PRODUCT_NAME IN (" + target + ") OR " +
                "BRAND IN (" + target + ") OR " +
                "COLOR IN (" + target + ")";
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Product_Model f = new Product_Model();
            f.setProductId(cursor.getInt(0));
            f.setProductName(cursor.getString(1));
            f.setPrice(cursor.getFloat(2));
            f.setRating(cursor.getFloat(3));
            f.setColor(cursor.getString(4));
            f.setBrand(cursor.getString(5));
            f.setImageResourceId(cursor.getInt(6));
            result.add(f);
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
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "SELECT * FROM PRODUCT_TABLE " +
                "WHERE " +
                "PRODUCT_NAME LIKE '%" + target +"%' OR " +
                "COLOR LIKE '%" + target + "%' OR " +
                "BRAND LIKE '%" + target + "%'";
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Product_Model f = new Product_Model();
            f.setProductId(cursor.getInt(0));
            f.setProductName(cursor.getString(1));
            f.setPrice(cursor.getFloat(2));
            f.setRating(cursor.getFloat(3));
            f.setColor(cursor.getString(4));
            f.setBrand(cursor.getString(5));
            f.setImageResourceId(cursor.getInt(6));
            result.add(f);
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

    public ArrayList<Product_Model> searchAll(){
        ArrayList<Product_Model> result = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "SELECT * FROM PRODUCT_TABLE ";
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Product_Model f = new Product_Model();
            f.setProductId(cursor.getInt(0));
            f.setProductName(cursor.getString(1));
            f.setPrice(cursor.getFloat(2));
            f.setRating(cursor.getFloat(3));
            f.setColor(cursor.getString(4));
            f.setBrand(cursor.getString(5));
            f.setImageResourceId(cursor.getInt(6));
            result.add(f);
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

}
