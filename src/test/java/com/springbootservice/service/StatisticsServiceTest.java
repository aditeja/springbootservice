package com.springbootservice.service;

import com.springbootservice.model.Statistics;
import com.springbootservice.model.Transaction;
import com.springbootservice.util.Constant;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * The type Statistics service test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsServiceTest {

    private static final Double amount = 12.3;
    private static final Double sum = 12.3;
    private static final Double avg = 12.3;
    private static final Double max = 12.3;
    private static final Double min = 12.3;
    private static final Long count = Long.valueOf(1);
    private static final Long id = UUID.randomUUID().getMostSignificantBits();
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private StatisticsServiceImpl statisticsService;

    /**
     * Private stub to Get new transaction.
     * @param id        the id
     * @param amount    the amount
     * @param timestamp the timestamp
     * @return the new transaction
     */
    private Transaction getNewTransaction(Long id, Double amount, Long timestamp) {
        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setAmount(amount);
        transaction.setTimestamp(timestamp);
        return transaction;
    }

    /**
     * Get statistics test case 1.
     * when no transactions are present within 60sec.
     */
    @SuppressWarnings("deprecation")
    @Test
    public void getStatisticsTestCase1(){
        Statistics statistics = statisticsService.getStatistics();
        assertEquals(Double.valueOf(0), statistics.getAvg());
        assertEquals(Double.valueOf(0), statistics.getSum());
        assertEquals(Double.valueOf(0), statistics.getMax());
        assertEquals(Double.valueOf(0), statistics.getMin());
        assertEquals(Long.valueOf(0), statistics.getCount());
    }

    /**
     * Get statistics test case 2.
     * when a transaction happens.
     * @throws Exception the exception
     */
    @SuppressWarnings("deprecation")
    @Test
    public void getStatisticsTestCase2() throws Exception {
        transactionService.addTransaction(getNewTransaction(id,amount,System.currentTimeMillis()));
        TimeUnit.MILLISECONDS.sleep(1000);
        Statistics statistics = statisticsService.getStatistics();
        assertEquals(avg, statistics.getAvg());
        assertEquals(sum, statistics.getSum());
        assertEquals(max, statistics.getMax());
        assertEquals(min, statistics.getMin());
        assertEquals(count, statistics.getCount());
    }
}
