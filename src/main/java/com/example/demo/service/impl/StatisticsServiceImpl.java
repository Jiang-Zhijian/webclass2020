package com.example.demo.service.impl;

import com.example.demo.constants.ResultMsgConstants;
import com.example.demo.dao.OrderDetailRepository;
import com.example.demo.dao.OrderRepository;
import com.example.demo.exception.TimeRangeWrongException;
import com.example.demo.pojo.BookOrderEntity;
import com.example.demo.pojo.OrderDetailEntity;
import com.example.demo.service.StatisticsService;
import com.example.demo.utils.SessionUtil;
import com.example.demo.vo.BookSaleStatisticsVO;
import com.example.demo.vo.UserConsumeStatisticsVO;
import com.example.demo.vo.UserStatisticVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    /**
     *@description 获取用户统计信息， 普通用户
     *@param
     *@return
     */
    @Override
    public UserStatisticVO userStatistics(Timestamp start, Timestamp end) {
        HttpSession session = SessionUtil.getSession();
        if(session == null){
            return new UserStatisticVO(ResultMsgConstants.SESSION_PROBLEM,0);
        }
        if(session.getAttribute("userId") == null){
            return new UserStatisticVO(ResultMsgConstants.NOT_LOGGED, 0);
        }
        Integer userId = (Integer) session.getAttribute("userId");
        UserStatisticVO userStatisticVO = new UserStatisticVO();
        try {
            userStatisticVO = userStatistics(userId, start, end);
        }catch (TimeRangeWrongException e){
            userStatisticVO.setMsg(e.getMessage());
        };
        return userStatisticVO;
    }

    /**
     *@description 重载的内部方法
     *@param
     *@return
     */
    private UserStatisticVO userStatistics(Integer userId,Timestamp start, Timestamp end )throws TimeRangeWrongException{
        if(end.before(start)){
            throw new TimeRangeWrongException(ResultMsgConstants.TIME_RANGE_WRONG);
        }
        List<BookOrderEntity> bookOrderEntityList = orderRepository.findByUserIdIsAndTimeBetween(userId, start, end);
        BigDecimal totalPrice = new BigDecimal("0.0");
        Map<Integer, Integer> bookMap = new HashMap<>();
        for (BookOrderEntity bookOrderEntity : bookOrderEntityList
             ) {
            totalPrice = totalPrice.add(bookOrderEntity.getTotalPrice());
            Integer orderID = bookOrderEntity.getId();
            List<OrderDetailEntity> orderDetailEntityList = orderDetailRepository.findByOrderId(orderID);
            for (OrderDetailEntity orderDetailEntity : orderDetailEntityList
                 ) {
                Integer bookId = orderDetailEntity.getBookId();
                bookMap.put(bookId, orderDetailEntity.getAmount() + bookMap.getOrDefault(bookId, 0));
            }
        }
        return new UserStatisticVO(ResultMsgConstants.GET_USER_STATISTICS_SUCCESS, 1, userId, totalPrice, bookMap.entrySet());
    }

    /**
     *@description 用户消费数据统计，管理员才有权限
     *@param
     *@return
     */
    @Override
    public UserConsumeStatisticsVO userConsumeStatistics(Timestamp start, Timestamp end){
        if(end.before(start)){
            return new UserConsumeStatisticsVO(ResultMsgConstants.TIME_RANGE_WRONG, 0);
        }
        List<BookOrderEntity> bookOrderEntityList = orderRepository.findByTimeBetween(start, end);
        Map<Integer, BigDecimal> userMap = new HashMap<>();
        for (BookOrderEntity bookOrderEntity : bookOrderEntityList
             ) {
            Integer userId = bookOrderEntity.getUserId();
            userMap.put(userId, bookOrderEntity.getTotalPrice().add(userMap.getOrDefault(userId, new BigDecimal("0.0"))));
        }
        return new UserConsumeStatisticsVO(ResultMsgConstants.GET_USER_CONSUME_STATISTICS_SUCCESS, 1, userMap.entrySet());
    }

    /**
     *@description 书籍销量统计，管理员才有权限
     *@param
     *@return
     */
    @Override
    public BookSaleStatisticsVO bookSaleStatistics(Timestamp start, Timestamp end){
        if(end.before(start)){
            return new BookSaleStatisticsVO(ResultMsgConstants.TIME_RANGE_WRONG, 0);
        }
        List<BookOrderEntity> bookOrderEntityList = orderRepository.findByTimeBetween(start, end);
        Map<Integer, Integer> bookMap = new HashMap<>();
        for (BookOrderEntity bookOrderEntity : bookOrderEntityList
        ) {
            Integer orderId = bookOrderEntity.getId();
            List<OrderDetailEntity> orderDetailEntityList = orderDetailRepository.findByOrderId(orderId);
            for (OrderDetailEntity orderDetailEntity: orderDetailEntityList
                 ) {
                Integer bookId = orderDetailEntity.getBookId();
                bookMap.put(bookId, orderDetailEntity.getAmount() + bookMap.getOrDefault(bookId,0));
            }
        }
        return new BookSaleStatisticsVO(ResultMsgConstants.GET_BOOK_SALE_STATISTICS_SUCCESS, 1, bookMap.entrySet());
    }
}
