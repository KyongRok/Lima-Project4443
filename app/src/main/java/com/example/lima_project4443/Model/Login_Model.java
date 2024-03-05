package com.example.lima_project4443.Model;

public class Login_Model {
    private int user_age;
    private int gender;

    private int hours_phone;

    public Login_Model(){


    }

    public void setAge(int age){
        this.user_age = age;
    }

    public void setGender(int gender){
        this.gender = gender;
    }

    public void setHours_Phone(int hours){
        this.hours_phone = hours;
    }

    public int getAge(){
        return this.user_age;
    }

    public int getGender(){
        return this.gender;
    }

    public int getHours_phone(){
        return this.hours_phone;
    }

}
