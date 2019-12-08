package com.example.demo.vo;

import org.json.JSONObject;

public class ResultVO<T>  {
    public String msg;
    public Integer status;
    public Integer id;
    public T data;

    public ResultVO() {
    }

    public ResultVO(String msg, Integer status) {
        this.msg = msg;
        this.status = status;
    }

    public ResultVO(String msg, Integer status, Integer id) {
        this.msg = msg;
        this.status = status;
        this.id = id;
    }

    public ResultVO(String msg, Integer status, T data) {
        this.msg = msg;
        this.status = status;
        this.data = data;
    }

    public ResultVO(String msg, Integer status, Integer id, T data){
        this.msg = msg;
        this.status = status;
        this.id = id;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JSONObject toJsonObject() {
        JSONObject object = new JSONObject();
        object.put("msg",msg);
        object.put("status",status);
        object.put("id",id);
        object.put("data",data);
        return object;
    }
}
