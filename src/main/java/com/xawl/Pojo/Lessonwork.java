package com.xawl.Pojo;


public class Lessonwork {

    private long id;
    private long uid;
    private long cid;
    private long type;
    private String lname;
    private long pclasshours;
    private String part;
    private double coe;
    private long classhours;
    private long pass;
    private java.sql.Timestamp starteddate;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }


    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }


    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }


    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }


    public long getPclasshours() {
        return pclasshours;
    }

    public void setPclasshours(long pclasshours) {
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


    public long getClasshours() {
        return classhours;
    }

    public void setClasshours(long classhours) {
        this.classhours = classhours;
    }


    public long getPass() {
        return pass;
    }

    public void setPass(long pass) {
        this.pass = pass;
    }


    public java.sql.Timestamp getStarteddate() {
        return starteddate;
    }

    public void setStarteddate(java.sql.Timestamp starteddate) {
        this.starteddate = starteddate;
    }

}
