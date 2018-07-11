package com.springbootservice.service;

import com.springbootservice.model.Transaction;

/**
 * The interface Transaction service.
 */
public interface TransactionService {

    /**
     * Add transaction to the Expiring Map.
     * @param transaction the transaction
     */
    void addTransaction(Transaction transaction);
}
