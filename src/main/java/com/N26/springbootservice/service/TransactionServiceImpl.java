package com.N26.springbootservice.service;

import com.N26.springbootservice.exception.SpringBootServiceException;
import com.N26.springbootservice.model.Transaction;
import com.N26.springbootservice.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Transaction service.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        logger.info("In TransactionService method - createTransaction");
        try {
            return transactionRepository.save(transaction);
        } catch (Exception ex) {
            logger.error("An exception was thrown during save ", ex);
            throw new SpringBootServiceException("Error while saving transaction");
        }
    }
}
