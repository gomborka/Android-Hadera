package com.borka.targilaugust;



public class Worker {

    private int Id;
    private String wname;
    private String password;
    private String intime;
    private String outtime;
    private String applocation;
    private String userlocation;

    public Worker(int id, String wname,String password, String intime, String outtime, String applocation, String userlocation) {
        Id = id;
        this.wname = wname;
        this.password = password;
        this.intime = intime;
        this.outtime = outtime;
        this.applocation = applocation;
        this.userlocation = userlocation;
    }

    public Worker(String wname, String password,String intime, String outtime, String applocation, String userlocation) {
        this.wname = wname;
        this.password = password;
        this.intime = intime;
        this.outtime = outtime;
        this.applocation = applocation;
        this.userlocation = userlocation;
    }

    public Worker() {
    }

    public int getId() {
        return Id;
    }

    public String getWname() {
        return wname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIntime() {
        return intime;
    }

    public String getOuttime() {
        return outtime;
    }

    public String getApplocation() {
        return applocation;
    }

    public String getUserlocation() {
        return userlocation;
    }


    public void setId(int id) {
        Id = id;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }

    public void setApplocation(String applocation) {
        this.applocation = applocation;
    }

    public void setUserlocation(String userlocation) {
        this.userlocation = userlocation;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "Id=" + Id +
                ", wname='" + wname + '\'' +
                ", password='" + password + '\'' +
                ", intime='" + intime + '\'' +
                ", outtime='" + outtime + '\'' +
                ", applocation='" + applocation + '\'' +
                ", userlocation='" + userlocation + '\'' +
                '}';
    }
}
