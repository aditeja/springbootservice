package com.N26.springbootservice.model;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * The type Statistics.
 */
public class Statistics {
    private Double sum;
    private Double avg;
    private Double max;
    private Double min;
    private Long count;


    /**
     * Instantiates a Statistics.
     */
    public Statistics() {
        // Default Constructor
    }

    /**
     * Instantiates a new Statistics.
     * Returns statistics calculated from given transactions.
     * @param transactions the transactions
     */
    public Statistics(List<Transaction> transactions) {
        final List<Double> amounts = transactions.stream().map(Transaction::getAmount).collect(toList());
        final Long count = amounts.stream().count();
        this.setCount(count);
        if (count > 0) {
            this.setSum(amounts.stream().mapToDouble(Double::doubleValue).sum());
            this.setAvg(amounts.stream().mapToDouble(Double::doubleValue).average().getAsDouble());
            this.setMax(amounts.stream().max(Double::compareTo).get());
            this.setMin(amounts.stream().min(Double::compareTo).get());
        }
    }

    /**
     * Gets max.
     * @return Value of max.
     */
    public Double getMax() {
        return max;
    }

    /**
     * Sets new max.
     * @param max New value of max.
     */
    public void setMax(Double max) {
        this.max = max;
    }

    /**
     * Gets count.
     * @return Value of count.
     */
    public Long getCount() {
        return count;
    }

    /**
     * Sets new count.
     * @param count New value of count.
     */
    public void setCount(Long count) {
        this.count = count;
    }

    /**
     * Gets avg.
     * @return Value of avg.
     */
    public Double getAvg() {
        return avg;
    }

    /**
     * Sets new avg.
     * @param avg New value of avg.
     */
    public void setAvg(Double avg) {
        this.avg = avg;
    }

    /**
     * Gets min.
     * @return Value of min.
     */
    public Double getMin() {
        return min;
    }

    /**
     * Sets new min.
     * @param min New value of min.
     */
    public void setMin(Double min) {
        this.min = min;
    }

    /**
     * Gets sum.
     * @return Value of sum.
     */
    public Double getSum() {
        return sum;
    }

    /**
     * Sets new sum.
     * @param sum New value of sum.
     */
    public void setSum(Double sum) {
        this.sum = sum;
    }
}


