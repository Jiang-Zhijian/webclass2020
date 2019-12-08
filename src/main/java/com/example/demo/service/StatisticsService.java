package com.example.demo.service;

import com.example.demo.vo.BookSaleStatisticsVO;
import com.example.demo.vo.UserConsumeStatisticsVO;
import com.example.demo.vo.UserStatisticVO;

import java.sql.Timestamp;

public interface StatisticsService {
    UserStatisticVO userStatistics(Timestamp start, Timestamp end);

    UserConsumeStatisticsVO userConsumeStatistics(Timestamp start, Timestamp end);

    BookSaleStatisticsVO bookSaleStatistics(Timestamp start, Timestamp end);
}
