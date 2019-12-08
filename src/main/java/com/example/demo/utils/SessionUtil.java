package com.example.demo.utils;

import org.springframework.http.HttpRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
    /**
     *@description 获取session
     *@param
     *@return
     */
    public static HttpSession getSession(){
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(servletRequestAttributes == null){
            return null;
        }
        HttpServletRequest httpRequest = servletRequestAttributes.getRequest();
        HttpSession httpSession =  httpRequest.getSession();
        return httpSession;
    }


}
