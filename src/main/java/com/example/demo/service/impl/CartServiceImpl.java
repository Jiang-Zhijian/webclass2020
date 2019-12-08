package com.example.demo.service.impl;

import com.example.demo.constants.ResultMsgConstants;
import com.example.demo.dto.CartDTO;
import com.example.demo.service.CartService;
import com.example.demo.utils.SessionUtil;
import com.example.demo.vo.ResultVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    /**
     *@description  将一定数量的书籍加入购物车，存在session中
     *@param
     *@return
     */
    @Override
    public ResultVO addCart(CartDTO cartDTO) {
        HttpSession session = SessionUtil.getSession();
        if(session == null){
            return new ResultVO(ResultMsgConstants.SESSION_PROBLEM,0);
        }
        if(session.getAttribute("cart") == null){
            Map<Integer,Integer> cartMap = new HashMap<>();
            cartMap.put(cartDTO.getBookId(),cartDTO.getAmount());
            session.setAttribute("cart",cartMap);
            return new ResultVO(ResultMsgConstants.ADD_CART_SUCCESS, 1,cartMap.entrySet());
        }
        Map<Integer, Integer> cartMap = (Map<Integer, Integer>) session.getAttribute("cart");
        if(cartMap.containsKey(cartDTO.getBookId())){
            Integer bookAmount = cartMap.get(cartDTO.getBookId());
            if(bookAmount < 0){
                return new ResultVO(ResultMsgConstants.BOOK_AMOUNT_NEG, 0);
            }
            cartMap.put(cartDTO.getBookId(), bookAmount + cartDTO.getAmount());
        }else{
            cartMap.put(cartDTO.getBookId(), cartDTO.getAmount());
        }
        return new ResultVO(ResultMsgConstants.ADD_CART_SUCCESS, 1, cartMap.entrySet());
    }

    /**
     *@description 将一定数量的书籍从购物车移除，存在session中
     *@param
     *@return
     */
    @Override
    public ResultVO decreaseCart(CartDTO cartDTO){
        HttpSession session = SessionUtil.getSession();
        if(session == null){
            return new ResultVO(ResultMsgConstants.SESSION_PROBLEM,0);
        }
        if(session.getAttribute("cart") == null){
            return new ResultVO(ResultMsgConstants.BOOK_NOT_IN_CART, 0);
        }
        Map<Integer, Integer> cartMap = (Map<Integer, Integer>) session.getAttribute("cart");
        if(cartMap.containsKey(cartDTO.getBookId())){
            Integer bookAmount = cartMap.get(cartDTO.getBookId());
            if(bookAmount - cartDTO.getAmount() > 0){
                cartMap.put(cartDTO.getBookId(), bookAmount - cartDTO.getAmount());
            }else{
                cartMap.remove(cartDTO.getBookId());
            }
            return new ResultVO(ResultMsgConstants.DECREASE_CART_SUCCESS,1);
        }else{
            return new ResultVO(ResultMsgConstants.BOOK_NOT_IN_CART, 0);
        }
    }

    /**
     *@description  获取购物车
     *@param
     *@return
     */
    @Override
    public ResultVO getCart(){
        HttpSession session = SessionUtil.getSession();
        if(session == null){
            return new ResultVO(ResultMsgConstants.SESSION_PROBLEM,0);
        }
        if(session.getAttribute("cart") == null){
            return new ResultVO(ResultMsgConstants.CART_EMPTY, 0);
        }
        Map<Integer, Integer> cartMap = (Map<Integer, Integer>) session.getAttribute("cart");
        return new ResultVO(ResultMsgConstants.GET_CART_SUCCESS, 1, cartMap.entrySet());
    }
}
