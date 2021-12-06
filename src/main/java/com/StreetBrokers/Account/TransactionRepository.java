package com.StreetBrokers.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    /**
     *
     * @param accountId
     */
    @Query(value = "SELECT * FROM transaction t WHERE t.account_id = ?1", nativeQuery = true)
    Optional<List<Transaction>> findListOfTransactionsByAccountId(Long accountId);
}
