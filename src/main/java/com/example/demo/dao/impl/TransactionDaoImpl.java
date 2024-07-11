package com.example.demo.dao.impl;

import com.example.demo.dao.TransactionDao;
import com.example.demo.database.DBUtil;
import com.example.demo.model.Transaction;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TransactionDaoImpl implements TransactionDao {

    private static EntityManager manager;

    public TransactionDaoImpl() {
        manager = DBUtil.getManager();
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        manager.getTransaction().begin();
        manager.persist(transaction);
        manager.getTransaction().commit();
        return transaction;
    }

    @Override
    public List<Transaction> getAll() {
        return manager.createQuery("SELECT t FROM Transaction t", Transaction.class).getResultList();
    }

    @Override
    public List<Transaction> getByAccountId(String accountId) {
        return manager.createQuery("SELECT c from Transaction c where c.fromAccountId = :accountId or c.toAccountId = :accountId order by c.timestamp desc", Transaction.class)
                .setParameter("accountId", accountId)
                .getResultList();
    }
}