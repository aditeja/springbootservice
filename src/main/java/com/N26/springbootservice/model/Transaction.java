package com.N26.springbootservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The type Transaction.
 */
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "timestamp")
    private Long timestamp;


    /**
     * Instantiates a Transaction.
     */
    public Transaction() {
        // Default Constructor
    }

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


