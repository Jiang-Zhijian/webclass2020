package com.example.demo.service;

import com.example.demo.vo.ResultVO;

public interface OrderService {
    ResultVO purchase();

    ResultVO getOrder();

    ResultVO getOrder(Integer userId);

    ResultVO getAllOrder();
}
