package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import com.example.demo.model.Transaction;

import java.util.List;

public interface AccountService {
    Account addAccount(Account account);
    Account updateAccount(Long id, Account account);
    Account getAccountById(Long id);
    boolean deleteAccount(Long id);
    List<Account> getAllAccounts();
    Customer getCustomerByAccountId(Long accountId);
    List<Transaction> getTransactionsByAccountId(String accountId);
    Transaction addTransaction(Transaction transaction);

    //get account by customer id
    Account getAccountByCustomerId(Long customerId);

    //get account by customer email
    Account getAccountByCustomerEmail(String email);


    //Implement code for deposit amount and add transaction to transaction table
    Account depositAmount(String accNo, double amount);

    //Implement code for withdraw amount
    Account withdrawAmount(String accNo, double amount);

    //Implement code for transfer amount
    Account transferAmount(String fromAccountId, String toAccountId, double amount);
}