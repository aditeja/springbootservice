package com.springbootservice.controller;

import com.springbootservice.exception.ExceptionControllerAdvice;
import com.springbootservice.model.Statistics;
import com.springbootservice.service.StatisticsService;
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

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Statistics controller test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatisticsControllerTest {

    private static final Double sum = 12.3;
    private static final Double avg = 12.3;
    private static final Double max = 12.3;
    private static final Double min = 12.3;
    private static final Integer count = 1;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StatisticsService statisticsService;

    @InjectMocks
    private StatisticsController statisticsController;

    /**
     * Private stub to Gets new statistics.
     * @param sum   the sum
     * @param avg   the avg
     * @param max   the max
     * @param min   the min
     * @param count the count
     * @return the new statistics
     */
    private Statistics getNewStatistics(Double sum, Double avg, Double max, Double min, Long count) {
        Statistics statistics = new Statistics();
        statistics.setSum(sum);
        statistics.setAvg(avg);
        statistics.setMax(max);
        statistics.setMin(min);
        statistics.setCount(count);
        return statistics;
    }

    /**
     * Sets up.
     */
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(statisticsController)
                .setControllerAdvice(new ExceptionControllerAdvice()).build();
    }

    /**
     * Gets statistics test case 1.
     * Testing Get statistics API
     * @throws Exception the exception
     */
    @SuppressWarnings("deprecation")
    @Test
    public void getStatisticsTestCase1() throws Exception {
        Statistics statistics = getNewStatistics(sum, avg, max, min, Long.valueOf(count));
        when(statisticsService.getStatistics()).thenReturn(statistics);
        mockMvc.perform(get("/statistics")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("sum", equalTo(sum)))
                .andExpect(jsonPath("avg", equalTo(avg)))
                .andExpect(jsonPath("max", equalTo(max)))
                .andExpect(jsonPath("min", equalTo(min)))
                .andExpect(jsonPath("count", equalTo(count)));
    }
}
