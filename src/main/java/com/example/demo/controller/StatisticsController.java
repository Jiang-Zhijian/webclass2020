package com.example.demo.controller;

import com.example.demo.service.StatisticsService;
import com.example.demo.vo.BookSaleStatisticsVO;
import com.example.demo.vo.UserConsumeStatisticsVO;
import com.example.demo.vo.UserStatisticVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @RequestMapping(method = RequestMethod.GET, value = "/user/getStatistics")
    public UserStatisticVO getStatistics(@RequestParam(value = "start") String start, @RequestParam(value = "end") String end){
            UserStatisticVO userStatisticVO = new UserStatisticVO();
        try{
            userStatisticVO = statisticsService.userStatistics(Timestamp.valueOf(start), Timestamp.valueOf(end));
        }catch(Exception e){
            userStatisticVO.setMsg(e.getMessage());
            userStatisticVO.setStatus(0);
        }
        return userStatisticVO;
        }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/getUserConsumeStatistics")
    public UserConsumeStatisticsVO getUserConsumeStatistics(@RequestParam(value = "start") String start, @RequestParam(value = "end") String end){
        return statisticsService.userConsumeStatistics(Timestamp.valueOf(start), Timestamp.valueOf(end));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/getBookSaleStatistics")
    public BookSaleStatisticsVO getBookSaleStatistics(@RequestParam(value = "start") String start, @RequestParam(value = "end") String end){
        return statisticsService.bookSaleStatistics(Timestamp.valueOf(start), Timestamp.valueOf(end));
    }


}
