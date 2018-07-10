package com.springbootservice.service;

import com.springbootservice.model.Transaction;

/**
 * The interface Transaction service.
 */
public interface TransactionService {

    /**
     * Create transaction transaction.
     * @param transaction the transaction
     * @return the transaction
     */
    void addTransaction(Transaction transaction);
}
