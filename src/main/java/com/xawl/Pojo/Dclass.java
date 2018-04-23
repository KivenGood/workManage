package com.xawl.Pojo;

public class Dclass {
    private Integer id;

    private String cname;
    private Integer pnum;
    private Integer series;
    private String sdept;
    private java.sql.Timestamp startedDate;
    //private Integer pageNum;
    //private Integer pageSize;


    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }


    public Integer getPnum() {
        return pnum;
    }

    public void setPnum(Integer pnum) {
        this.pnum = pnum;
    }


    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }


    public String getSdept() {
        return sdept;
    }

    public void setSdept(String sdept) {
        this.sdept = sdept;
    }


    public java.sql.Timestamp getStarteddate() {
        return startedDate;
    }

    public void setStarteddate(java.sql.Timestamp startedDate) {
        this.startedDate = startedDate;
    }

  /*  public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }*/
}

