package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.vo.ResultVO;

public interface UserService {
    ResultVO register(UserDTO userDTO);

    ResultVO updateUserDisabled(Integer id, byte number);

    ResultVO login(UserDTO userDTO);

    ResultVO logout();
}
