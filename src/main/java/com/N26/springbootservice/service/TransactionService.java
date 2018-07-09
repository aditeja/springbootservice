package com.N26.springbootservice.service;

import com.N26.springbootservice.model.Transaction;

/**
 * The interface Transaction service.
 */
public interface TransactionService {

    /**
     * Create transaction transaction.
     * @param transaction the transaction
     * @return the transaction
     */
    Transaction createTransaction(Transaction transaction);
}
