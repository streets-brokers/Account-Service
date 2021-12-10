package com.StreetBrokers.Account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    /**
     * Save a new Account, if exists already, throw error
     * @param account
     * @return saved account
     */
    public Account saveAccount(Account account) {
        Optional<Account> accountByClientId = accountRepository.findAccountByClientId(account.getClientId());
        if(accountByClientId.isPresent()){
            throw new IllegalStateException("Client Already exists");
//            System.out.println("Client Already exists");
        }
        log.info("Inside saveAccount method in Account Service");
        account.setTotalBalance(0.0);
        return accountRepository.save(account);
    }

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
     * Get Account by clientId
     * @param clientId
     * @return Optional<Account>
     */
    public Optional<Account> getAccountByClientId(Long clientId) {
        Optional<Account> accountByClientId = accountRepository.findAccountByClientId(clientId);
        if(accountByClientId.isEmpty()){
            throw new IllegalStateException("Account is not found");
        }
        log.info("Inside getAccountByClientId method in Account Service");
        return accountByClientId;
    }

    public Account updateAccount(Account account){
        Optional<Account> accountById = accountRepository.findAccountByAccountId(account.getAccountId());
        if(accountById.isPresent()){
            Account existingAccount = accountById.get();
            existingAccount.setTotalBalance(account.getTotalBalance());
            Account updatedAccount = accountRepository.save(existingAccount);
            return  new Account(updatedAccount.getAccountId(),updatedAccount.getTotalBalance(), updatedAccount.getClientId(), updatedAccount.getTransaction());
        }else {
            return null;
        }
    }

    /**
     * To-Do add amount of transaction to account balance if transaction type is TOPUP
     * This method is to add funds to account
     * @param transaction
     * @return saved transaction
     */
    public Transaction addFunds(Transaction transaction){
               TransactionType operation = TransactionType.TOPUP;
               transaction.setTransactionType(operation);
        log.info("Inside addFunds method in Account Service");
        return transactionRepository.save(transaction);
    }

    /**
     * To-Do deduct amount of transaction to account balance
     * if transaction type is WITHDRAWAL
     * And if balance in Account is less than or equal to the transaction amount
     * This method is to add funds to account
     * @param transaction
     * @return saved transaction
     */
    public Transaction withdraw(Transaction transaction){
        TransactionType operation2 = TransactionType.WITHDRAWAL;
        transaction.setTransactionType(operation2);
        System.out.println(
                operation2
        );
        log.info("Inside withdraw method in Account Service");
        return transactionRepository.save(transaction);
    }

    /**
     * Get list of transactions using clientId
     * @param clientId
     * @return List<Optional<Transaction>>
     */
//    public List<Optional<Transaction>> getListOfTransactionsByClientId(Long clientId){
//        List<Optional<Transaction>> listOfTransactionsByClientId = accountRepository.findListOfTransactionsByClientId(clientId);
//        if(listOfTransactionsByClientId.isEmpty()){
//            throw new IllegalStateException("Transactions is not found");
//        }
//        log.info("Inside getListOfTransactionsByClientIds method in Account Service");
//        return listOfTransactionsByClientId;
//    }
}
/**
 * Tomorrow to-do
 * 1. Come and work on the controller and routes of the transactions(addFunds and Withdraw) method.
 * 2. Look at the exception handeling
 * 3. How to send the HTTP response with its Error and Success codes.
 */
