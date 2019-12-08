package com.example.demo.dao;

import com.example.demo.pojo.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

        Optional<UserEntity> findOneByName(String name);

        @Modifying
        @Query("update UserEntity as u set u.disabled = ?2 where  u.id = ?1")
        int setUserDisabledById(int id, byte disabled);

        @Query("select u.name from UserEntity as u where u.id = ?1")
        String findNameById(Integer id);

        Optional<UserEntity> findByNameIsAndPasswordIs(String name, String password);
}
