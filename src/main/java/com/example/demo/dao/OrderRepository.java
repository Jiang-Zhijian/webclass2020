package com.example.demo.dao;

import com.example.demo.pojo.BookOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<BookOrderEntity, Integer> {

        List<BookOrderEntity> findByUserId(Integer userId);

        List<BookOrderEntity> findByUserIdIsAndTimeBetween(Integer userId,Timestamp start, Timestamp end);

        List<BookOrderEntity> findByTimeBetween(Timestamp start, Timestamp end);
}
