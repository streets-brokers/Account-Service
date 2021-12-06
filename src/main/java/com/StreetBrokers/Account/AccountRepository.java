package com.StreetBrokers.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    /**
    SELECT * FROM account WHERE id = ?
    @Query("SELECT s FROM ACCOUNT s WHERE a.accountId =?1")
    */

    /**
     * Find an account by ClientId
     * @param clientId
     */
    Optional<Account> findAccountByClientId(Long clientId);

    /**
     *Find an account by accountId
     * @param accountId
     */
    Optional<Account> findAccountByAccountId(Long accountId);

    /**
     *
     * @param accountId
     */
    Optional<List<Transaction>> findListOfTransactionsByAccountId(Long accountId);

    /**
     *
     * @param clientId
     */
//    List<Optional<Transaction>> findListOfTransactionsByClientId(Long clientId);
}
