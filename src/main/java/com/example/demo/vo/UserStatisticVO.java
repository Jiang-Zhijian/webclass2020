package com.example.demo.vo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class UserStatisticVO {
    public String msg;
    public Integer status;
    public Integer userId;
    public BigDecimal totalPrice;
    public Set<Map.Entry<Integer,Integer>> bookList;

    public UserStatisticVO() {
    }

    public UserStatisticVO(String msg, Integer status, Integer userId, BigDecimal totalPrice, Set<Map.Entry<Integer, Integer>> bookList) {
        this.msg = msg;
        this.status = status;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.bookList = bookList;
    }

    public UserStatisticVO(String msg, Integer status) {
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<Map.Entry<Integer, Integer>> getBookList() {
        return bookList;
    }

    public void setBookList(Set<Map.Entry<Integer, Integer>> bookList) {
        this.bookList = bookList;
    }
}
