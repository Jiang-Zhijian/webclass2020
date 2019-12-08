package com.example.demo.service;

import com.example.demo.dto.CartDTO;
import com.example.demo.vo.ResultVO;


public interface CartService {
    ResultVO addCart(CartDTO cartDTO);

    ResultVO decreaseCart(CartDTO cartDTO);

    ResultVO getCart();
}
