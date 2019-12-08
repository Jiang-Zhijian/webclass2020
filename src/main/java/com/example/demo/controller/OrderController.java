package com.example.demo.controller;

import com.example.demo.service.OrderService;
import com.example.demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.POST, value = "/user/purchase")
    public ResultVO purchase(){
        return orderService.purchase();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/getOrder")
    public ResultVO getOrder(){return orderService.getOrder();}

    @RequestMapping(method = RequestMethod.GET, value = "/admin/getOrder")
    public ResultVO getAllOrder(){return orderService.getAllOrder();}

    @RequestMapping(method = RequestMethod.GET, value = "/admin/getOrderByUserId")
    public ResultVO getOrderByUserId(@RequestParam(value = "id")Integer id){
        return orderService.getOrder(id);
    }
}
