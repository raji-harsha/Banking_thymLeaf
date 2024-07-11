package com.example.demo.dao;

import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import com.example.demo.model.Transaction;

import java.util.List;

public interface AccountDao {
    Account save(Account account);
    Account update(Long id, Account account);
    Account getById(Long id);
    boolean delete(Long id);
    List<Account> getAll();
    Customer getCustomerByAccountId(Long accountId);

    Account getByAccountNumber(String accountNumber);

    Transaction addTransaction(Transaction transaction);

    Account getAccountByCustomerId(Long customerId);

    Account getAccountByCustomerEmail(String email);
}