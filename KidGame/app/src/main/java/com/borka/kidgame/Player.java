package com.borka.kidgame;

public class Player {
    int id;
    String fname;
    String lname;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public String getDate() {
        return Date;
    }


    public void setDate(String date) {
        Date = date;
    }
    String username;
    String password;
    int score;
    String Date;
    public Player()
    {

    }
    public Player(int id,String fname,String lname,String username,String password, int score,String Date)
    {
        this.id=id;
        this.fname=fname;
        this.lname=lname;
        this.username=username;
        this.password=password;
        this.score=score;
        this.Date=Date;
    }

    public Player( String fname,String lname,String username,String password, int score,String Date)
    {
        this.fname=fname;
        this.lname=lname;
        this.username=username;
        this.password=password;
        this.score=score;
        this.Date=Date;
    }
}









