package com.N26.springbootservice.repository;

import com.N26.springbootservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Transaction repository.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    /**
     * Find by timestamp greater than list.
     * @param l the l
     * @return the list
     */
    List<Transaction> findByTimestampGreaterThan(long l);
}
