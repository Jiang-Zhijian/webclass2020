package com.example.demo.dao;

import com.example.demo.pojo.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer> {

    List<OrderDetailEntity> findByOrderId(Integer orderid);
}
