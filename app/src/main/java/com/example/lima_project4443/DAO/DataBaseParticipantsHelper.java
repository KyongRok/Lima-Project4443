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
                "ID INTEGER PRIMARY KEY, " +
                "AGE INTEGER, " +
                "PHONE_USE INTEGER, " +
                "GENDER INTEGER)";
        String CreateTableStatement_Records = "CREATE TABLE PARTICIPANT_RECORDS (" +
                "ID INTEGER, " +
                "TYPE TEXT, " +
                "COMPLETION_TIME TEXT)";

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
        cv.put("ID" , login_model.getId());
        cv.put("AGE",login_model.getAge());
        cv.put("PHONE_USE", login_model.getHours_phone());
        cv.put("gender", login_model.getGender());
        long participantTable = db.insert("PARTICIPANT_TABLE", null, cv);
        db.close();
        return participantTable == 1;
    }

    public long setParticipantCompletionTime(String time, Login_Model login_model,String type){
        //used to insert completion time for the participants
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //inserts into database
        cv.put("ID",login_model.getId());
        cv.put("TYPE", type);
        cv.put("COMPLETION_TIME", time);
        long participantRecord = db.insert("PARTICIPANT_RECORDS", null, cv);
        db.close();
        return participantRecord;
    }

    public void updateParticipantTestType(Login_Model login_model){
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "UPDATE PARTICIPANT_TABLE" +
                "SET TYPE = '" + login_model.getType() + "'" +
                "WHERE ID = '" + login_model.getId() + "'";
        db.execSQL(Query);
        db.close();
    }

}
