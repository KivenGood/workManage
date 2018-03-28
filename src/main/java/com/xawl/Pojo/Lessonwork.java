package com.xawl.Pojo;


public class Lessonwork {

    private Integer id;
    private Integer uid;
    private Integer cid;
    private Integer type;
    private String lname;
    private Integer pclasshours;
    private String part;
    private Double coe;
    private Double classhours;
    private Integer pass;

    private java.sql.Timestamp startedDate;
    private Integer term;
    private User user;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }


    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }


    public Integer getPclasshours() {
        return pclasshours;
    }

    public void setPclasshours(Integer pclasshours) {
        this.pclasshours = pclasshours;
    }


    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }


    public Double getCoe() {
        return coe;
    }

    public void setCoe(Double coe) {
        this.coe = coe;
    }


    public Double getClasshours() {
        return classhours;
    }

    public void setClasshours(Double classhours) {
        this.classhours = classhours;
    }


    public Integer getPass() {
        return pass;
    }

    public void setPass(Integer pass) {
        this.pass = pass;
    }


    public java.sql.Timestamp getStarteddate() {
        return startedDate;
    }

    public void setStarteddate(java.sql.Timestamp startedDate) {
        this.startedDate = startedDate;
    }

}
