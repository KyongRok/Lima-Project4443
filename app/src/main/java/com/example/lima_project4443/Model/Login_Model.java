package com.example.lima_project4443.Model;

public class Login_Model {
    private int user_age;
    private int gender;

    private int hours_phone;
    private double completion_time;
    private long id;
    public Login_Model(){

    }

    public void setAge(int age){
        this.user_age = age;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setGender(int gender){
        this.gender = gender;
    }

    public void setHours_Phone(int hours){
        this.hours_phone = hours;
    }

    public void setCompletion_time(double time){
        this.completion_time = time;
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

    public long getId(){
        return this.id;
    }

    public double getCompletionTime(){
        return this.completion_time;
    }

}
