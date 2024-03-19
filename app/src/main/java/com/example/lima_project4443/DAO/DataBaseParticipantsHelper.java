package com.example.lima_project4443.DAO;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.lima_project4443.Model.Login_Model;

public class DataBaseParticipantsHelper extends SQLiteOpenHelper {


    public DataBaseParticipantsHelper(@Nullable Context context) {
        super(context, "participants.db", null, 1);
    }

    //called the first time when database is accessed
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTableStatement = "CREATE TABLE PARTICIPANT_TABLE (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "AGE INTEGER, " +
                "PHONE_USE INTEGER, " +
                "GENDER INTEGER)";
        String CreateTableStatement_Records = "CREATE TABLE PARTICIPANT_RECORDS (" +
                "ID INTEGER PRIMARY KEY, " +
                "TYPE TEXT, " +
                "COMPLETION_TIME REAL)";

        db.execSQL(CreateTableStatement_Records);
        db.execSQL(CreateTableStatement);
    }


    //called whenever database version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addParticipants(Login_Model login_model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //inserts into database
        cv.put("AGE",login_model.getAge());
        cv.put("PHONE_USE", login_model.getHours_phone());
        cv.put("gender", login_model.getGender());
        long participantTable = db.insert("PARTICIPANT_TABLE", null, cv);
        getParticipantsId(login_model);
        db.close();
        return participantTable == 1;
    }

    public void getParticipantsId(Login_Model login_model){
        //ID of the participants are required for shopping cart, wishlist and later adding
        //result of the experiment into the participants data,
        //sets the id of the participants by getting the auto incremented id of the participants
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "SELECT LAST_INSERT_ROWID();";
        Cursor cursor = db.rawQuery(Query,null);
        if(cursor != null){
            cursor.moveToFirst();
            long id = cursor.getInt(0);
            login_model.setId(id);
            cursor.close();
        }
    }

    public void setParticipantCompletionTime(double time, Login_Model login_model){
        //used to insert completion time for the participants
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //inserts into database
        cv.put("ID",login_model.getId());
        cv.put("TYPE", login_model.getType());
        cv.put("COMPLETION_TIME", time);
        long participantTable = db.insert("PARTICIPANT_RECORDS", null, cv);
        getParticipantsId(login_model);
        db.close();

    }

}
