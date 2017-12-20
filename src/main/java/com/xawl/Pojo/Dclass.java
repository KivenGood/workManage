package com.xawl.Pojo;


class DClass {

    private int id;
    private int uid;
    private String cname;
    private int pnum;
    private int series;
    private String sdept;
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


    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }


    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }


    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }


    public String getSdept() {
        return sdept;
    }

    public void setSdept(String sdept) {
        this.sdept = sdept;
    }


    public java.sql.Timestamp getStarteddate() {
        return starteddate;
    }

    public void setStarteddate(java.sql.Timestamp starteddate) {
        this.starteddate = starteddate;
    }

}
