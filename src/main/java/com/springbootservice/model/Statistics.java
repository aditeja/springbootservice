package com.springbootservice.model;

import net.jodah.expiringmap.ExpiringMap;

import java.util.Map;

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
     * Instantiates a new Statistics.
     */
    public Statistics() {
    }

    /**
     * Instantiates a new Statistics.
     * Returns statistics calculated from transaction amounts in Expiring map.
     */
    public Statistics(Map<Long,Double> map) {
        this.setCount((long) map.values().size());
        Double defaultValue = Double.valueOf(0);
        this.setSum(map.values().stream().mapToDouble(Double::doubleValue).sum());
        this.setAvg(map.values().stream().mapToDouble(Double::doubleValue).average().orElse(defaultValue));
        this.setMax(map.values().stream().max(Double::compareTo).orElse(defaultValue));
        this.setMin(map.values().stream().min(Double::compareTo).orElse(defaultValue));
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Statistics{");
        sb.append("sum=").append(sum);
        sb.append(", avg=").append(avg);
        sb.append(", max=").append(max);
        sb.append(", min=").append(min);
        sb.append(", count=").append(count);
        sb.append('}');
        return sb.toString();
    }
}


