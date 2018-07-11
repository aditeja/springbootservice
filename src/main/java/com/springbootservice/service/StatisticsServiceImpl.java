package com.springbootservice.service;

import com.springbootservice.exception.SpringBootServiceException;
import com.springbootservice.model.Statistics;
import com.springbootservice.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * The type Statistics service.
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsServiceImpl.class);
    private Statistics statistics = new Statistics();
    @Autowired
    private TransactionServiceImpl transactionService;

    public Statistics getStatistics() {
        logger.info("In StatisticsService method - getStatistics");
        try {
            return statistics;
        } catch (Exception ex) {
            logger.error("An exception was thrown while getting statistics", ex);
            throw new SpringBootServiceException("Error while getting statistics");
        }
    }

    /**
     * Scheduler to Generate statistics for every instant set in
     * SCHEDULER_POLLING_RATE_MILLS 1 millisecond by default.
     */
    @Scheduled(fixedRate = Constant.SCHEDULER_POLLING_RATE_MILLS)
    private void generateStatistics() {
        statistics = new Statistics(transactionService.map);
    }
}
