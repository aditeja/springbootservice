package com.springbootservice.controller;

import com.google.gson.Gson;
import com.springbootservice.exception.ExceptionControllerAdvice;
import com.springbootservice.model.Transaction;
import com.springbootservice.service.TransactionService;
import com.springbootservice.util.Constant;
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

    /**
     * Private stub to create new transaction.
     * @param amount    the amount
     * @param timestamp the timestamp
     * @return the new transaction
     */
    private Transaction getNewTransaction(Double amount, Long timestamp) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTimestamp(timestamp);
        return transaction;
    }

    /**
     * Sets up.
     */
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(transactionController)
                .setControllerAdvice(new ExceptionControllerAdvice()).build();
    }

    /**
     * Create transaction test case 1.
     * when transaction is within 60 sec.
     * @throws Exception the exception
     */
    @SuppressWarnings("deprecation")
    @Test
    public void createTransactionTestCase1() throws Exception {
        Transaction transaction = getNewTransaction(amount, System.currentTimeMillis());
        Gson gson = new Gson();
        String json = gson.toJson(transaction);
        mockMvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

    /**
     * Create transaction test case 2.
     * when transaction is earlier than 60 sec.
     * @throws Exception the exception
     */
    @SuppressWarnings("deprecation")
    @Test
    public void createTransactionTestCase2() throws Exception {
        Transaction transaction = getNewTransaction(amount, System.currentTimeMillis() - Constant.TIME_LIMIT);
        Gson gson = new Gson();
        String json = gson.toJson(transaction);
        mockMvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isNoContent());
    }

    /**
     * Create transaction test case 3.
     * when transaction is empty
     * @throws Exception the exception
     */
    @SuppressWarnings("deprecation")
    @Test
    public void createTransactionTestCase3() throws Exception {
        Transaction transaction = new Transaction();
        Gson gson = new Gson();
        String json = gson.toJson(transaction);
        mockMvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isInternalServerError());
    }

    /**
     * Create transaction test case 4.
     * when timestamp is null
     * @throws Exception the exception
     */
    @SuppressWarnings("deprecation")
    @Test
    public void createTransactionTestCase4() throws Exception {
        Transaction transaction = getNewTransaction(amount,null);
        Gson gson = new Gson();
        String json = gson.toJson(transaction);
        mockMvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isInternalServerError());
    }

    /**
     * Create transaction test case 5.
     * when amount is null
     * @throws Exception the exception
     */
    @SuppressWarnings("deprecation")
    @Test
    public void createTransactionTestCase5() throws Exception {
        Transaction transaction = getNewTransaction(null,System.currentTimeMillis());
        Gson gson = new Gson();
        String json = gson.toJson(transaction);
        mockMvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isInternalServerError());
    }

}
