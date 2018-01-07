package com.xawl.Pojo;


public class Message {

  private Integer id;
  private Integer uid;
  private Integer fromid;
  private Integer type;
  private String data;
  private Integer dataid;
  private java.sql.Timestamp startedDate;


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


  public Integer getFromid() {
    return fromid;
  }

  public void setFromid(Integer fromid) {
    this.fromid = fromid;
  }


  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }


  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }


  public Integer getDataid() {
    return dataid;
  }

  public void setDataid(Integer dataid) {
    this.dataid = dataid;
  }


  public java.sql.Timestamp getStarteddate() {
    return startedDate;
  }

  public void setStarteddate(java.sql.Timestamp startedDate) {
    this.startedDate = startedDate;
  }

}
