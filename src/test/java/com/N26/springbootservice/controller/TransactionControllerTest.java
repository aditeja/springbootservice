package com.N26.springbootservice.controller;

import com.N26.springbootservice.common.Constant;
import com.N26.springbootservice.exception.ExceptionControllerAdvice;
import com.N26.springbootservice.model.Transaction;
import com.N26.springbootservice.service.TransactionService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Transaction controller test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    private static final Double amount = 12.3;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

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
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(transactionController)
                .setControllerAdvice(new ExceptionControllerAdvice()).build();
    }

    /**
     * Create transaction test case 1.
     * @throws Exception the exception
     */
    @SuppressWarnings("deprecation")
    @Test
    public void createTransactionTestCase1() throws Exception {
        Transaction transaction = getNewTransaction(amount, System.currentTimeMillis());
        Gson gson = new Gson();
        String json = gson.toJson(transaction);
        when(transactionService.createTransaction(any(Transaction.class))).thenReturn(transaction);
        mockMvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

    /**
     * Create transaction test case 2.
     * @throws Exception the exception
     */
    @SuppressWarnings("deprecation")
    @Test
    public void createTransactionTestCase2() throws Exception {
        Transaction transaction = getNewTransaction(amount, System.currentTimeMillis() - Constant.TIME_LIMIT);
        Gson gson = new Gson();
        String json = gson.toJson(transaction);
        when(transactionService.createTransaction(any(Transaction.class))).thenReturn(transaction);
        mockMvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isNoContent());
    }
}
