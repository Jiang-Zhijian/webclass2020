package com.example.demo.dto;

import com.example.demo.pojo.OrderDetailEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class OrderDTO {
    private int orderId;
    private int userId;
    private Timestamp time;
    private BigDecimal totalPrice;
    private List<OrderDetailDTO> orderDetail;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderDetailDTO> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetailDTOS(List<OrderDetailDTO> orderDetail) {
        this.orderDetail = orderDetail;
    }
}
