package com.springbootservice.controller;

import com.springbootservice.model.Statistics;
import com.springbootservice.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Statistics controller.
 */
@RestController
public class StatisticsController {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    @Autowired
    private StatisticsService statisticsService;

    /**
     * Endpoint to Get statistics in constant time and space.
     * @return the response entity
     */
    @RequestMapping(path = "/statistics", method = RequestMethod.GET)
    public ResponseEntity<Statistics> getStatistics() {
        logger.info("In Statistics Controller method - getStatistics");
        Statistics statistics = statisticsService.getStatistics();
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }
}
