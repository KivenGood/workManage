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

    public ResultData(Integer status) {
        this.status = status;
    }

    public ResultData(Integer status, Object datas) {
        this.status = status;
        this.data = datas;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
