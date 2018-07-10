package com.springbootservice.repository;

import com.springbootservice.util.Constant;
import com.springbootservice.model.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * The type Transaction repository test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionRepositoryTest {

    private static final Double amount = 12.3;

    /**
     * The Transaction repository.
     */
    @Autowired
    TransactionRepository transactionRepository;

    private Transaction getNewTransaction(Double amount, Long timestamp) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTimestamp(timestamp);
        return transaction;
    }

    ;

    /**
     * Sets up.
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        transactionRepository.save(getNewTransaction(amount, System.currentTimeMillis()));
    }

    /**
     * Find by timestamp greater than value test case 1.
     */
    @Test
    public void findByTimestampGreaterThanValueTestCase1() {

        List<Transaction> transactionList = transactionRepository.findByTimestampGreaterThan(System.currentTimeMillis
                () - Constant.TIME_LIMIT);
        assertEquals(1, transactionList.size());
    }

    /**
     * Find by timestamp greater than value test case 2.
     */
    @Test
    public void findByTimestampGreaterThanValueTestCase2() {

        List<Transaction> transactionList = transactionRepository.findByTimestampGreaterThan(System.currentTimeMillis
                ());
        assertEquals(0, transactionList.size());
    }
}
