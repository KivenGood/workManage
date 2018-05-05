package com.xawl.Pojo;

public class Practicework {

    private Integer id;
    private Integer uid;
    private Integer type;
    private Integer cid;
    private Integer num;
    private String lname;
    private Double classhours;
    private java.sql.Timestamp startedDate;
    private Integer pass;
    private Integer term;
    private User user;
    private Integer snum;//实际指导人数
    private Integer note;//错误信息
    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }
    public Integer getTerm() {
        return term;
    }
    private String Cname;//班级姓名
    private Integer Cnum;//班级人数

    public Integer getSnum() {
        return snum;
    }

    public void setSnum(Integer snum) {
        this.snum = snum;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public Integer getCnum() {
        return Cnum;
    }

    public void setCnum(Integer cnum) {
        Cnum = cnum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }


    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
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
