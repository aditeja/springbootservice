package com.N26.springbootservice.controller;

import com.N26.springbootservice.common.SpringBootServiceUtil;
import com.N26.springbootservice.model.Transaction;
import com.N26.springbootservice.service.TransactionService;
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

/**
 * The type Transaction controller.
 */
@RestController
public class TransactionController {

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
    public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction, UriComponentsBuilder ucBuilder) {

        logger.info("In TransactionController method - createTransaction");

        HttpStatus status;
        Long currentTimestamp = System.currentTimeMillis();
        if (SpringBootServiceUtil.isTransactionTimestampRecent(transaction, currentTimestamp)) {
            status = HttpStatus.CREATED;
        } else {
            status = HttpStatus.NO_CONTENT;
        }

        Transaction savedTransaction = transactionService.createTransaction(transaction);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/transactions").buildAndExpand(savedTransaction.getId()).toUri());
        return new ResponseEntity<String>(headers, status);
    }
}
