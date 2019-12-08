package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import com.example.demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/user/toRegister")
    public ResultVO register(@RequestBody UserDTO userDTO){
        return userService.register(userDTO);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admin/disableUser")
    public ResultVO disable(@RequestParam(value = "id")Integer id){
        return userService.updateUserDisabled(id,(byte)1);
    }

    @RequestMapping(method = RequestMethod.PUT, value ="/admin/enableUser")
    public ResultVO enable(@RequestParam(value = "id")Integer id){
        return userService.updateUserDisabled(id,(byte)0);
    }

    @RequestMapping(method = RequestMethod.POST, value ="/user/toLogin")
    public ResultVO login(@RequestBody UserDTO userDTO){
        return userService.login(userDTO);
    }

    @RequestMapping(method = RequestMethod.PUT, value ="/user/toLogout")
    public ResultVO logout(){
        return userService.logout();
    }
}

