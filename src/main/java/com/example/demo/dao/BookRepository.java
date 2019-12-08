package com.example.demo.dao;

import com.example.demo.pojo.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository  extends JpaRepository<BookEntity, Integer> {

    BookEntity findByName(String name);

    List<BookEntity> findByNameContaining(String name);

    @Query("select u.name from BookEntity as u where u.id = ?1")
    String findNameById(Integer id);
}
