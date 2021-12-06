package com.StreetBrokers.Account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Get Account by AccountId
     * @param accountId
     * @return Optional<Account>
     */
    public Optional<Account> getAccountByAccountId(Long accountId) {
        Optional<Account> accountById = accountRepository.findAccountByAccountId(accountId);
        if(accountById.isEmpty()){
            throw new IllegalStateException("Account is not found");
        }
        log.info("Inside getAccountById method in Account Service");
        return accountById;
    }

    /**
     * Get List of Transactions using accountId
     * @param accountId
     * @return List<Optional<Transaction>>
     */
    public List<Transaction>  getListOfTransactionsByAccountId(Long accountId){
        Optional<Account> foundAccount = accountRepository.findAccountByAccountId(accountId);
        Account account = foundAccount.orElseThrow(()-> new IllegalStateException("Account not found"));
        Optional<List<Transaction>>  listOfTransactionsByAccountId = transactionRepository.findListOfTransactionsByAccountId(account.getAccountId());
        if(listOfTransactionsByAccountId .isEmpty()){
            throw new IllegalStateException("Transactions is not found");
        }
        log.info("Inside getListOfTransactionsByAccountId method in Account Service");
        return listOfTransactionsByAccountId.get() ;
    }
}
