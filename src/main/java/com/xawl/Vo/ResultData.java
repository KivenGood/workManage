package com.xawl.Vo;

/**
 * Created by doter on 2017/7/23.
 */
public class ResultData {
    public static Integer I_NoLogin = 21;
    public static Integer I_NoAllow = 22;
    public static Integer I_PramError = 23;
    public static Integer I_SerError = 24;
    public static Integer I_SqlError = 25;
    public static Integer I_PramFail = 26;

    Integer status;
    Object data;
    String data1;

    public ResultData(Integer status) {
        this.status = status;
    }

    public ResultData(Integer status, Object datas) {
        this.status = status;
        this.data = datas;
    }
    public ResultData(Integer status, Object datas,String datas1) {
        this.status = status;
        this.data = datas;
        this.data1 = datas1;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
