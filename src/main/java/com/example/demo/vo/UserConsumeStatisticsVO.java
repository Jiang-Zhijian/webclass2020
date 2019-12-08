package com.example.demo.vo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class UserConsumeStatisticsVO {
    public String msg;
    public Integer status;
    public Set<Map.Entry<Integer,BigDecimal>> userConsumeList;

    public UserConsumeStatisticsVO() {
    }

    public UserConsumeStatisticsVO(String msg, Integer status, Set<Map.Entry<Integer, BigDecimal>> userConsumeList) {
        this.msg = msg;
        this.status = status;
        this.userConsumeList = userConsumeList;
    }

    public UserConsumeStatisticsVO(String msg, Integer status) {
        this.msg = msg;
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Map.Entry<Integer, BigDecimal>> getUserConsumeList() {
        return userConsumeList;
    }

    public void setUserConsumeList(Set<Map.Entry<Integer, BigDecimal>> userConsumeList) {
        this.userConsumeList = userConsumeList;
    }
}
