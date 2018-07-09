package com.N26.springbootservice.service;

import com.N26.springbootservice.exception.NoDataFoundException;
import com.N26.springbootservice.exception.SpringBootServiceException;
import com.N26.springbootservice.model.Statistics;
import com.N26.springbootservice.model.Transaction;
import com.N26.springbootservice.repository.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * The type Statistics service test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatisticsServiceTest {

    private static final Double amount = 12.3;
    private static final Double sum = 12.3;
    private static final Double avg = 12.3;
    private static final Double max = 12.3;
    private static final Double min = 12.3;
    private static final Long count = Long.valueOf(1);
    private Long timestamp = System.currentTimeMillis();
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private StatisticsServiceImpl statisticsService;

    private Transaction getNewTransaction(Double amount, Long timestamp) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTimestamp(timestamp);
        return transaction;
    }

    ;

    private Statistics getNewStatistics(Double sum, Double avg, Double max, Double min, Long count) {
        Statistics statistics = new Statistics();
        statistics.setSum(sum);
        statistics.setAvg(avg);
        statistics.setMax(max);
        statistics.setMin(min);
        statistics.setCount(count);
        return statistics;
    }

    ;

    private List<Transaction> getTempTransactions(Transaction transaction) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        return transactions;
    }

    /**
     * Gets statistics test case 1.
     * @throws Exception the exception
     */
    @SuppressWarnings("deprecation")
    @Test
    public void getStatisticsTestCase1() throws Exception {
        when(transactionRepository.findByTimestampGreaterThan(any(Long.class))).thenReturn(getTempTransactions
                (getNewTransaction(amount, timestamp)));
        Statistics statistics = statisticsService.getStatistics();
        assertEquals(avg, statistics.getAvg());
        assertEquals(sum, statistics.getSum());
        assertEquals(max, statistics.getMax());
        assertEquals(min, statistics.getMin());
        assertEquals(count, statistics.getCount());
    }

    /**
     * Gets statistics test case 2.
     * @throws Exception the exception
     */
    @SuppressWarnings("deprecation")
    @Test(expected = NoDataFoundException.class)
    public void getStatisticsTestCase2() throws Exception {
        when(transactionRepository.findByTimestampGreaterThan(any(Long.class))).thenReturn(new ArrayList<>());
        Statistics statistics = statisticsService.getStatistics();
    }

    /**
     * Gets statistics test case 3.
     * @throws Exception the exception
     */
    @SuppressWarnings("deprecation")
    @Test(expected = SpringBootServiceException.class)
    public void getStatisticsTestCase3() throws Exception {
        when(transactionRepository.findByTimestampGreaterThan(any(Long.class))).thenThrow(Exception.class);
        Statistics statistics = statisticsService.getStatistics();
    }
}
