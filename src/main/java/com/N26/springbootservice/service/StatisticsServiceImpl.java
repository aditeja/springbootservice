package com.N26.springbootservice.service;

import com.N26.springbootservice.common.Constant;
import com.N26.springbootservice.exception.NoDataFoundException;
import com.N26.springbootservice.exception.SpringBootServiceException;
import com.N26.springbootservice.model.Statistics;
import com.N26.springbootservice.model.Transaction;
import com.N26.springbootservice.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * The type Statistics service.
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsServiceImpl.class);

    @Autowired
    private TransactionRepository transactionRepository;

    public Statistics getStatistics() {
        logger.info("In StatisticsService method - getStatistics by currentTimeStamp");
        try {
            Long currentTimeStamp = System.currentTimeMillis();
            List<Transaction> transactions = transactionRepository.findByTimestampGreaterThan(currentTimeStamp - Constant.TIME_LIMIT);
            if (CollectionUtils.isEmpty(transactions)) {
                throw new NoDataFoundException("No transactions are available in database");
            }
            return new Statistics(transactions);
        } catch (NoDataFoundException ex) {
            logger.error("No transactions are available in database");
            throw new NoDataFoundException(ex);
        } catch (Exception ex) {
            logger.error("An exception was thrown while finding transactions ", ex);
            throw new SpringBootServiceException("Error while getting transactions");
        }
    }
}
