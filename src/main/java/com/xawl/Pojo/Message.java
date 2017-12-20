package com.xawl.Pojo;


public class Message {

  private int id;
  private int uid;
  private int fromid;
  private int type;
  private String data;
  private int dataid;
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


  public int getFromid() {
    return fromid;
  }

  public void setFromid(int fromid) {
    this.fromid = fromid;
  }


  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }


  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }


  public int getDataid() {
    return dataid;
  }

  public void setDataid(int dataid) {
    this.dataid = dataid;
  }


  public java.sql.Timestamp getStarteddate() {
    return starteddate;
  }

  public void setStarteddate(java.sql.Timestamp starteddate) {
    this.starteddate = starteddate;
  }

}
