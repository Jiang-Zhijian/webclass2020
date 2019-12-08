package com.example.demo.service.impl;

import com.example.demo.constants.ResultMsgConstants;
import com.example.demo.dao.BookRepository;
import com.example.demo.dao.OrderDetailRepository;
import com.example.demo.dao.OrderRepository;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderDetailDTO;
import com.example.demo.pojo.BookEntity;
import com.example.demo.pojo.BookOrderEntity;
import com.example.demo.pojo.OrderDetailEntity;
import com.example.demo.service.OrderService;
import com.example.demo.utils.Entity2DTOUtil;
import com.example.demo.utils.SessionUtil;
import com.example.demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private BookRepository bookRepository;

    /**
     *@description 提交订单并清空购物车
     *@param
     *@return
     */
    @Override
    @Transactional
    public ResultVO purchase(){
        HttpSession session = SessionUtil.getSession();
        if(session == null){
            return new ResultVO(ResultMsgConstants.SESSION_PROBLEM,0);
        }
        if(session.getAttribute("cart") == null){
            return new ResultVO(ResultMsgConstants.BOOK_NOT_IN_CART, 0);
        }
        Map<Integer, Integer> cartMap = (Map<Integer, Integer>) session.getAttribute("cart");
        BigDecimal totalPrice = new BigDecimal("0.0");

        BookOrderEntity bookorderEntity = new BookOrderEntity();
        bookorderEntity.setUserId((Integer) session.getAttribute("userId"));
        bookorderEntity.setTime(new Timestamp(System.currentTimeMillis()));
        bookorderEntity.setTotalPrice(totalPrice);

        BookOrderEntity tmpOrder = orderRepository.saveAndFlush(bookorderEntity);
        Integer orderId = tmpOrder.getId();

        for(Map.Entry<Integer,Integer> bookEntry : cartMap.entrySet()){
                Integer bookId = bookEntry.getKey(), bookAmount = bookEntry.getValue();
                if(!bookRepository.findById(bookId).isPresent()){
                    return new ResultVO(ResultMsgConstants.NO_BOOK_FOUND, 0);
                }
                BookEntity bookEntity = bookRepository.findById(bookId).get();
                BigDecimal price = bookEntity.getPrice();
                Integer stock = bookEntity.getStock();

                if(stock < bookAmount){
                    return new ResultVO(ResultMsgConstants.STOCK_NOT_ENOUGH,0);
                }
                OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
                orderDetailEntity.setBookId(bookId);
                orderDetailEntity.setAmount(bookAmount);
                orderDetailEntity.setBookPrice(bookEntity.getPrice());
                orderDetailEntity.setOrderId(orderId);
                orderDetailRepository.save(orderDetailEntity);

                bookEntity.setStock(stock - bookAmount);
                bookRepository.save(bookEntity);

                totalPrice = totalPrice.add(price.multiply(new BigDecimal(bookAmount)));
        }
        tmpOrder.setTotalPrice(totalPrice);
        orderRepository.save(tmpOrder);

        session.removeAttribute("cart");
        return new ResultVO(ResultMsgConstants.PURCHASE_SUCCESS,1);
    }

    /**
     *@description 用户获取自己的订单
     *@param
     *@return
     */
    @Override
    public ResultVO getOrder(){
        HttpSession session = SessionUtil.getSession();
        if(session == null){
            return new ResultVO(ResultMsgConstants.SESSION_PROBLEM,0);
        }
        if(session.getAttribute("userId") == null){
            return new ResultVO(ResultMsgConstants.NOT_LOGGED, 0);
        }
        Integer userId = (Integer) session.getAttribute("userId");
        return getOrder(userId);
    }

    /**
     *@description 根据userId获取订单，管理员功能
     *@param
     *@return
     */
    @Override
    public ResultVO getOrder(Integer userId){
        List<BookOrderEntity> orderEntitylist = orderRepository.findByUserId(userId);
        return getOrder(orderEntitylist);
    }


    /**
     *@description  获取所有订单，管理员功能
     *@param
     *@return
     */
    @Override
    public ResultVO getAllOrder(){
        List<BookOrderEntity> orderEntitylist = orderRepository.findAll();
        return getOrder(orderEntitylist);
    }

    /**
     *@description  用于代码复用
     *@param
     *@return
     */
    @Transactional
    public ResultVO getOrder(List<BookOrderEntity> orderEntitylist){
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for(BookOrderEntity bookOrderEntity : orderEntitylist){
            OrderDTO orderDTO = Entity2DTOUtil.entity2DTO(bookOrderEntity);
            Integer orderID = bookOrderEntity.getId();
            List<OrderDetailEntity> orderDetailEntityList = orderDetailRepository.findByOrderId(orderID);
            List<OrderDetailDTO> orderDetail = new ArrayList<>();
            for(OrderDetailEntity orderDetailEntity : orderDetailEntityList){
                OrderDetailDTO orderDetailDTO = Entity2DTOUtil.entity2DTO(orderDetailEntity);
                orderDetailDTO.setBookName(bookRepository.findNameById(orderDetailEntity.getBookId()));
                orderDetail.add(orderDetailDTO);

            }
            orderDTO.setOrderDetailDTOS(orderDetail);
            orderDTOList.add(orderDTO);
        }
        return new ResultVO(ResultMsgConstants.GET_ORDER_SUCCESS,1, orderDTOList);
    }
}
