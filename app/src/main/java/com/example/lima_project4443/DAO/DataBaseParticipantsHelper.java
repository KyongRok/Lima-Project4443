package com.example.lima_project4443.DAO;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "AGE INTEGER, " +
                "PHONE_USE INTEGER, " +
                "GENDER)";

        db.execSQL(CreateTableStatement);
    }


    //called whenever database version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addParticipants(Login_Model login_model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("AGE",login_model.getAge());
        cv.put("PHONE_USE", login_model.getHours_phone());
        cv.put("gender", login_model.getGender());
        long participantTable = db.insert("PARTICIPANT_TABLE", null, cv);

        return participantTable == 1;

    }
}
