package com.springbootservice.service;

import com.springbootservice.exception.SpringBootServiceException;
import com.springbootservice.model.Transaction;
import com.springbootservice.repository.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * The type Transaction service test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionServiceTest {

    private static final Double amount = 12.3;
    private Long timestamp = System.currentTimeMillis();

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private Transaction getNewTransaction(Double amount, Long timestamp) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTimestamp(timestamp);
        return transaction;
    }

    ;

    /**
     * Create transaction test case 1.
     * @throws Exception the exception
     */
    @SuppressWarnings("deprecation")
    @Test
    public void createTransactionTestCase1() throws Exception {
        Transaction transaction = getNewTransaction(amount, timestamp);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        Transaction savedTransaction = transactionService.createTransaction(transaction);
        assertEquals(amount, savedTransaction.getAmount());
        assertEquals(timestamp, savedTransaction.getTimestamp());
    }

    /**
     * Create Transaction test case 2.
     * @throws Exception the exception
     */
    @SuppressWarnings("deprecation")
    @Test(expected = SpringBootServiceException.class)
    public void createTransactionTestCase2() throws Exception {
        Transaction transaction = getNewTransaction(amount, timestamp);
        when(transactionRepository.save(transaction)).thenThrow(Exception.class);
        Transaction savedTransaction = transactionService.createTransaction(transaction);
    }
}
