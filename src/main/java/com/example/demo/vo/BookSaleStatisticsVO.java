package com.example.demo.vo;

import java.util.Map;
import java.util.Set;

public class BookSaleStatisticsVO {
    public String msg;
    public Integer status;
    public Set<Map.Entry<Integer, Integer>> bookSaleList;

    public BookSaleStatisticsVO() {
    }

    public BookSaleStatisticsVO(String msg, Integer status, Set<Map.Entry<Integer, Integer>> bookSaleList) {
        this.msg = msg;
        this.status = status;
        this.bookSaleList = bookSaleList;
    }

    public BookSaleStatisticsVO(String msg, Integer status) {
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

    public Set<Map.Entry<Integer, Integer>> getBookSaleList() {
        return bookSaleList;
    }

    public void setBookSaleList(Set<Map.Entry<Integer, Integer>> bookSaleList) {
        this.bookSaleList = bookSaleList;
    }
}
