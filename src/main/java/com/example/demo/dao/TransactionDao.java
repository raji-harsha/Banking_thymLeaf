package com.example.demo.dao;

import com.example.demo.model.Transaction;

import java.util.List;

public interface TransactionDao {
    // Add method to save transaction
    Transaction addTransaction(Transaction transaction);

    // Add method to get all transactions
    List<Transaction> getAll();

    // Add method to get transaction by account Id either in fromAccount or toAccount
    List<Transaction> getByAccountId(String accountId);
}