package com.example.lima_project4443;

import java.util.Calendar;
import java.util.Date;

public class UserInfo {
    private static UserInfo userInstance = null;
    private String fname;
    private String lname;
    private String bdate;
    //private int byear;
   // private int bmonth;
    //private int bday;
    private UserInfo(){
        fname = "Jane";
        lname = "Doe";
        bdate = "1989/2/18";

    }

    public static synchronized UserInfo getInstance(){
        if(userInstance == null){
            userInstance = new UserInfo();
        }
        return userInstance;

    }

    public void reset(){
        fname = "Jane";
        lname = "Doe";
        //Calendar cal = Calendar.getInstance();
        bdate = "1989/2/18";
        //bdate = cal;

    }
    public String getfname(){
        return this.fname;
    }
    public String getlname(){
        return this.lname;
    }
    public String getbdate(){
        return this.bdate;
    }
    public void setfname(String fname){
        this.fname = fname;
    }
    public void setlname(String lname){
        this.lname = lname;
    }
   // public void setbdate(int year,int month,int date){
    //    bdate=(year +"/"+month+"/"+date);
   // }
   public void setbdate(String dob){
       bdate=dob;
   }




    //method to reset it



}
