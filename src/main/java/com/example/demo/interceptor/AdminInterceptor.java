package com.example.demo.interceptor;

import com.example.demo.constants.ResultMsgConstants;
import com.example.demo.dao.UserRepository;
import com.example.demo.pojo.UserEntity;

import com.example.demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Optional;


public class AdminInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("userId") != null) {
            Integer userId = (Integer) session.getAttribute("userId");
            Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
            if(userEntityOptional.isPresent() && userEntityOptional.get().getType() == 1) {
                return true;
            }
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(new ResultVO(ResultMsgConstants.PERMISSION_DENIED,0).toJsonObject());
        return false;
    }
}
