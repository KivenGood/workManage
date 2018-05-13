package com.xawl.Pojo;

import java.sql.Timestamp;

public class DbSum {

  private Integer id;
  private Integer uid;
  private Integer type;
  private Double pclass;
  private Integer pass;
  private java.sql.Timestamp startedDate;
  private User user;

  public String getCname() {
    return cname;
  }

  public void setCname(String cname) {
    this.cname = cname;
  }

  private String cname;
  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }

/*  private String techno;
  private String name;
  private String level;

  public String getTechno() {
    return techno;
  }

  public void setTechno(String techno) {
    this.techno = techno;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }*/

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

  public Double getPclass() {
    return pclass;
  }

  public void setPclass(Double pclass) {
    this.pclass = pclass;
  }

  public Integer getPass() {
    return pass;
  }

  public void setPass(Integer pass) {
    this.pass = pass;
  }

  public Timestamp getStartedDate() {
    return startedDate;
  }

  public void setStartedDate(Timestamp startedDate) {
    this.startedDate = startedDate;
  }
}
