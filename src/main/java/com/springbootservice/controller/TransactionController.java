package com.springbootservice.controller;

import com.springbootservice.util.SpringBootServiceUtil;
import com.springbootservice.exception.SpringBootServiceException;
import com.springbootservice.model.Transaction;
import com.springbootservice.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

/**
 * The type Transaction controller.
 */
@RestController
public class TransactionController {

    /**
     * The constant logger.
     */
    public static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    /**
     * Create transaction response entity.
     * @param transaction the transaction
     * @param ucBuilder   the uc builder
     * @return the response entity
     */
    @RequestMapping(path = "/transaction", method = RequestMethod.POST)
    public ResponseEntity<?> addTransaction(@RequestBody Transaction transaction, UriComponentsBuilder ucBuilder) {

        logger.info("In TransactionController method - createTransaction");
        if(isTransactionValid(transaction)) {
            HttpStatus status;
            Long currentTimestamp = System.currentTimeMillis();
            if (SpringBootServiceUtil.isTransactionTimestampRecent(transaction, currentTimestamp)) {
                status = HttpStatus.CREATED;
                transaction.setId(UUID.randomUUID().getMostSignificantBits());
                transactionService.addTransaction(transaction);
            } else {
                status = HttpStatus.NO_CONTENT;
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/transactions").buildAndExpand(transaction.getId()).toUri());
            return new ResponseEntity<String>(headers, status);
        }else{
            throw new SpringBootServiceException("Invalid Transaction");
        }
    }


    /**
     * private method to check if transaction is valid.
     * @param transaction the transaction
     * @return the boolean
     */
    private Boolean isTransactionValid(Transaction transaction){
        return (transaction!=null && transaction.getAmount() != null && transaction.getTimestamp() != null);
    }
}
