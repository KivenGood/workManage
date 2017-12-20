package com.xawl.Pojo;


public class Message {

  private long id;
  private long uid;
  private long fromid;
  private long type;
  private String data;
  private long dataid;
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


  public long getFromid() {
    return fromid;
  }

  public void setFromid(long fromid) {
    this.fromid = fromid;
  }


  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }


  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }


  public long getDataid() {
    return dataid;
  }

  public void setDataid(long dataid) {
    this.dataid = dataid;
  }


  public java.sql.Timestamp getStarteddate() {
    return starteddate;
  }

  public void setStarteddate(java.sql.Timestamp starteddate) {
    this.starteddate = starteddate;
  }

}
