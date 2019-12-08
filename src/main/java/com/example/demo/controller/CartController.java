package com.example.demo.controller;

import com.example.demo.dto.CartDTO;
import com.example.demo.service.CartService;
import com.example.demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping(method = RequestMethod.POST, value = "/user/addCart")
    public ResultVO addCart(@RequestBody CartDTO cartDTO ){
        return cartService.addCart(cartDTO);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/decreaseCart")
    public ResultVO decreaseCart(@RequestBody CartDTO cartDTO ){
        return cartService.decreaseCart(cartDTO);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/getCart")
    public ResultVO getCart(){
        return cartService.getCart();
    }

}
