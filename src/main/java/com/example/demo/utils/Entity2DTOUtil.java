package com.example.demo.utils;

import com.example.demo.dto.BookDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderDetailDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.pojo.BookEntity;
import com.example.demo.pojo.BookOrderEntity;
import com.example.demo.pojo.OrderDetailEntity;
import com.example.demo.pojo.UserEntity;

public class Entity2DTOUtil {
   /**
    *@description
    *@param bookEntity
    *@return bookDTO
    */
    public static BookDTO entity2DTO(BookEntity bookEntity){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(bookEntity.getId());
        bookDTO.setName(bookEntity.getName());
        bookDTO.setIsbn(bookEntity.getIsbn());
        bookDTO.setAuthor(bookEntity.getAuthor());
        bookDTO.setPrice(bookEntity.getPrice());
        bookDTO.setStock(bookEntity.getStock());
        bookDTO.setCover(bookEntity.getCover());
        return bookDTO;
    }
    /**
     *@description 省略setPassword
     *@param userEntity
     *@return userDTO
     */
    public static UserDTO entity2DTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setName(userEntity.getName());
        userDTO.setType(userEntity.getType());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setDisabled(userEntity.getDisabled());
        return userDTO;
    }

    /**
     *@description  orderDetail未设置
     *@param  bookOrderEntity
     *@return orderDTO
     */
    public static OrderDTO entity2DTO(BookOrderEntity bookOrderEntity){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(bookOrderEntity.getId());
        orderDTO.setUserId(bookOrderEntity.getUserId());
        orderDTO.setTime(bookOrderEntity.getTime());
        orderDTO.setTotalPrice(bookOrderEntity.getTotalPrice());
        return orderDTO;
    }

    /**
     *@description  bookName未设置
     *@param    orderDetailEntity
     *@return  orderDetailDTO
     */
    public static OrderDetailDTO entity2DTO(OrderDetailEntity orderDetailEntity){
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setBookId(orderDetailDTO.getBookId());
        orderDetailDTO.setAmount(orderDetailEntity.getAmount());
        orderDetailDTO.setBookPrice(orderDetailEntity.getBookPrice());
        return orderDetailDTO;
    }
}
