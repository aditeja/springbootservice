package com.springbootservice.model;

/**
 * The type Transaction.
 */
public class Transaction {
    private Long id;
    private Double amount;
    private Long timestamp;

    /**
     * Gets amount.
     * @return Value of amount.
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets new amount.
     * @param amount New value of amount.
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Gets timestamp.
     * @return Value of timestamp.
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets new timestamp.
     * @param timestamp New value of timestamp.
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets id.
     * @return Value of id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets new id.
     * @param id New value of id.
     */
    public void setId(Long id) {
        this.id = id;
    }
}


