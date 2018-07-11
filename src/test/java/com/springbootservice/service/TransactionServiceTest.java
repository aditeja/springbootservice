package com.springbootservice.service;

import com.springbootservice.model.Transaction;
import net.jodah.expiringmap.ExpiringMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The type Transaction service test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {

    private static final Double amount = 12.3;
    private Long timestamp = System.currentTimeMillis();
    private static final Long id = UUID.randomUUID().getMostSignificantBits();

    @Autowired
    private TransactionServiceImpl transactionService;

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
     * Create transaction test case 1.
     * when a new transaction is added
     */
    @SuppressWarnings("deprecation")
    @Test
    public void createTransactionTestCase1(){
        Transaction transaction = getNewTransaction(id, amount, timestamp);
        transactionService.addTransaction(transaction);
        ExpiringMap<Long,Double> map = transactionService.map;
        assertTrue(map.containsKey(transaction.getId()));
        assertEquals(amount, map.get(transaction.getId()));
    }
}
