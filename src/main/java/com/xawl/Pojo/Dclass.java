package com.xawl.Pojo;


class DClass {

    private long id;
    private long uid;
    private String cname;
    private long pnum;
    private long series;
    private String sdept;
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


    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }


    public long getPnum() {
        return pnum;
    }

    public void setPnum(long pnum) {
        this.pnum = pnum;
    }


    public long getSeries() {
        return series;
    }

    public void setSeries(long series) {
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
