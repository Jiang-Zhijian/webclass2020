package com.example.demo.service.impl;

import com.example.demo.constants.ResultMsgConstants;
import com.example.demo.dao.UserRepository;
import com.example.demo.dto.UserDTO;
import com.example.demo.pojo.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.utils.DTO2EntityUtil;
import com.example.demo.utils.SessionUtil;
import com.example.demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     *@description 注册
     *@param userDTO
     *@return
     */
    @Override
    @Transactional
    public ResultVO register(UserDTO userDTO){
        if(userDTO.getName() == null || userDTO.getPassword() == null || userDTO.getEmail() == null){
            return new ResultVO(ResultMsgConstants.REGISTER_INFO_WRONG, 0);
        }
        if(userRepository.findOneByName(userDTO.getName()).isPresent()) {
            return new ResultVO(ResultMsgConstants.USERNAME_DUPLICATED, 0);
        }
        UserEntity userEntity = DTO2EntityUtil.dto2Entity(userDTO);
        userRepository.save(userEntity);
        return new ResultVO(ResultMsgConstants.REGISTER_SUCCESS, 1);
    }

    /**
     *@description 禁用/解禁用户，只有管理员才有权限
     *@param userId & (1 禁用 || 0 解禁）
     *@return
     */
    @Override
    @Transactional
    public ResultVO updateUserDisabled(Integer userId, byte number){
        if(number != 0 && number != 1){
            return new ResultVO(ResultMsgConstants.DISABLED_INFO_WRONG, 0);
        }
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
        if(!optionalUserEntity.isPresent()){
            return new ResultVO(ResultMsgConstants.NO_USER_FOUND, 0);
        }
        UserEntity userEntity = optionalUserEntity.get();
        userEntity.setDisabled(number);
        userRepository.save(userEntity);
        return new ResultVO(ResultMsgConstants.UPDATE_DISABLED_SUCCESS,1);
    }

    /**
     *@description 用户登录
     *@param userDTO
     *@return
     */
    @Override
    @Transactional
    public ResultVO login(UserDTO userDTO){
        Optional<UserEntity> optionalUserEntity = userRepository.findByNameIsAndPasswordIs(userDTO.getName(),userDTO.getPassword());
        if(!optionalUserEntity.isPresent()){
            return new ResultVO(ResultMsgConstants.LOGIN_INFO_WRONG, 0);
        }
        if(optionalUserEntity.get().getDisabled() == 1){
            return new ResultVO(ResultMsgConstants.USER_DISABLED, -1);
        }
        HttpSession session = SessionUtil.getSession();
        if(session == null){
            return new ResultVO(ResultMsgConstants.SESSION_PROBLEM,0);
        }
        session.setAttribute("userId",optionalUserEntity.get().getId());
        class UserType{
            public byte userType;
            UserType(byte userType){
                this.userType = userType;
            }
        }
        return new ResultVO(ResultMsgConstants.LOGIN_SUCCESS, 1, new UserType(optionalUserEntity.get().getType()));
    }

    /**
     *@description 退出登录
     *@param
     *@return
     */
    @Override
    public ResultVO logout(){
        HttpSession session = SessionUtil.getSession();
        if(session == null){
            return new ResultVO(ResultMsgConstants.SESSION_PROBLEM,0);
        }
        session.removeAttribute("userId");
        return new ResultVO(ResultMsgConstants.LOGOUT_SUCCESS, 1);
    }

}
