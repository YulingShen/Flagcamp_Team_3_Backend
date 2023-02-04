package com.laiofferflagcamp.community.util;

/**
 * @version 1.0
 * @project Flagcamp_Team_3_Backend-maintenance
 */
public class Result {
    private int code; // 结果标志，200:正常
    private Object data; // 返回的数据
    private String msg; // 返回信息

    public Result() {
    }

    public Result(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
