package com.example.demo.utils;

import com.example.demo.dto.BookDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.pojo.BookEntity;
import com.example.demo.pojo.UserEntity;

public class DTO2EntityUtil {
  /**
   *@description 省略setID
   *@param bookDTO
   *@return bookEntity
   */
    public static BookEntity dto2Entity(BookDTO bookDTO){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(bookDTO.getName());
        bookEntity.setIsbn(bookDTO.getIsbn());
        bookEntity.setAuthor(bookDTO.getAuthor());
        bookEntity.setPrice(bookDTO.getPrice());
        bookEntity.setStock(bookDTO.getStock());
        bookEntity.setCover(bookDTO.getCover());
        return bookEntity;
    }

    /**
     *@description 省略setID
     *@param userDTO
     *@return userEntity
     */
    public static UserEntity dto2Entity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDTO.getName());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setEmail(userDTO.getEmail());
        return userEntity;
    }
}
