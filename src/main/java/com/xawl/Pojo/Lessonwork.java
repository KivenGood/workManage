package com.xawl.Pojo;


public class Lessonwork {

    private int id;
    private int uid;
    private int cid;
    private int type;
    private String lname;
    private int pclasshours;
    private String part;
    private double coe;
    private int classhours;
    private int pass;
    private java.sql.Timestamp starteddate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }


    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }


    public int getPclasshours() {
        return pclasshours;
    }

    public void setPclasshours(int pclasshours) {
        this.pclasshours = pclasshours;
    }


    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }


    public double getCoe() {
        return coe;
    }

    public void setCoe(double coe) {
        this.coe = coe;
    }


    public int getClasshours() {
        return classhours;
    }

    public void setClasshours(int classhours) {
        this.classhours = classhours;
    }


    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }


    public java.sql.Timestamp getStarteddate() {
        return starteddate;
    }

    public void setStarteddate(java.sql.Timestamp starteddate) {
        this.starteddate = starteddate;
    }

}
