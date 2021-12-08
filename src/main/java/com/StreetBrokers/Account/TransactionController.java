package com.StreetBrokers.Account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/accoutservice/transactions")
@Slf4j
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{accountId}")
    public List<Transaction> ListOfTransactionsByAccountId(@PathVariable("accountId") Long accountId){
        List<Transaction> transactionList = transactionService.getListOfTransactionsByAccountId(accountId);
        log.info("Inside getListOfTransactionsByAccountId in AccountController");
        return transactionList;
    }
}
