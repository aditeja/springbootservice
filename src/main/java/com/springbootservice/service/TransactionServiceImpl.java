package com.springbootservice.service;

import com.springbootservice.exception.SpringBootServiceException;
import com.springbootservice.model.Transaction;
import com.springbootservice.util.Constant;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * The type Transaction service.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    /**
     * The Expiring Map that sets transactions to expire in 60 secs from its timestamp.
     */
    protected final ExpiringMap<Long, Double> map = ExpiringMap.builder().variableExpiration().build();
    public void addTransaction(Transaction transaction) {
        logger.info("In TransactionService method - createTransaction");
        try {
            Long duration = transaction.getTimestamp()+ Constant.TIME_LIMIT - System.currentTimeMillis();
            map.put(transaction.getId(),transaction.getAmount(),ExpirationPolicy.CREATED,duration,TimeUnit
                    .MILLISECONDS);
        } catch (Exception ex) {
            logger.error("An exception was thrown during save ", ex);
            throw new SpringBootServiceException("Error while saving transaction");
        }
    }
}
