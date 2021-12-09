package com.StreetBrokers.Account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/v1/accountservice/account")
@Slf4j
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account){
        log.info("createAccount method in Account controller");
        return accountService.saveAccount(account);
    }

    @GetMapping("accountId/{accountId}")
    public Optional<Account> getAccountByAccountId(@PathVariable("accountId") Long accountId){
        log.info("Inside getAccountById in Account controller");
        return accountService.getAccountByAccountId(accountId);
    }

    @GetMapping("clientId/{clientId}")
    public Optional<Account> getAccountByClientId(@PathVariable("clientId") Long clientId){
        log.info("Inside getAccountByClientId in Account controller");
        return accountService.getAccountByClientId(clientId);
    }

//    @RequestMapping(value = "{accountId}/addFunds", method = {RequestMethod.POST,RequestMethod.PUT})
    @PostMapping("{accountId}/addFunds")
    public Transaction addTransaction(@PathVariable(value ="accountId" ) Long accountId, @RequestBody Transaction transaction){
               Optional<Account> foundAccount = accountRepository.findAccountByAccountId(accountId);
               Account updatedAccount = foundAccount.map(account -> {
                   account.setTotalBalance(account.getTotalBalance() + transaction.getAmount());
                   return accountService.updateAccount(account);
               }).orElseThrow(()-> new IllegalStateException("Account not found"));
//        accountRepository.findAccountByAccountId(accountId).map(account -> {
//            account.setTotalBalance(account.getTotalBalance() + transaction.getAmount());
//            accountService.updateAccount(account);
//            Transaction newTransaction = accountService.addFunds(transaction);
//            return newTransaction;
//        }).orElseThrow(()-> new IllegalStateException("Account not found"));


//       Optional<Account> foundAccount = accountRepository.findAccountByAccountId(accountId);
//        System.out.println(foundAccount);
//       if(foundAccount.isPresent()){
//           System.out.println("yaaay "+ foundAccount);
//           return;
//       }
//       throw new IllegalStateException("Account not found");



//        accountRepository.findAccountByAccountId(accountId).map(account -> {
////            System.out.println(account.getTotalBalance());
////            System.out.println(transaction);
////                            transaction.setAccount(account);
////            System.out.println(transaction);
////                            double newBalance = account.getTotalBalance() + transaction.getAmount();
////                            account.setTotalBalance(newBalance);
////                            accountService.updateAccount(account);
//
//                            account.setTotalBalance(account.getTotalBalance() + transaction.getAmount());
////                            Set<Transaction> setOfTransaction = new HashSet<>();
////                            setOfTransaction.add(transaction);
//                            accountService.updateAccount(account);
//                            Transaction newTransaction = accountService.addFunds(transaction);
//                            return newTransaction;
//                        }).orElseThrow(()-> new IllegalStateException("Account not found"));
        log.info("Inside addTransaction method in Account controller");
                transaction.setAccount(updatedAccount);
               Transaction newTransaction = accountService.addFunds(transaction);
                return newTransaction;
    }

    @PostMapping("{accountId}/withdraw")
    public Transaction withdrawFunds(@PathVariable(value = "accountId") Long accountId, @RequestBody Transaction transaction){
        Optional<Account> foundAccount = accountRepository.findAccountByAccountId(accountId);
        Account updatedAccount = foundAccount.map(account -> {
            account.setTotalBalance(account.getTotalBalance() - transaction.getAmount());
            return accountService.updateAccount(account);
        }).orElseThrow(()-> new IllegalStateException("Account not found"));
        log.info("Inside withdrawFunds  method in Account controller");
        transaction.setAccount(updatedAccount);
        Transaction newTransaction = accountService.addFunds(transaction);
        return newTransaction;
    }
}
