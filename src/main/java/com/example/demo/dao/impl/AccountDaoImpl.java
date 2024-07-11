package com.example.demo.dao.impl;

import com.example.demo.dao.AccountDao;
import com.example.demo.database.DBUtil;
import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import com.example.demo.model.Transaction;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AccountDaoImpl implements AccountDao {

    private static EntityManager manager;

    public AccountDaoImpl() {
        manager = DBUtil.getManager();
    }

    @Override
    public Account save(Account account) {
        manager.getTransaction().begin();
        manager.persist(account);
        manager.getTransaction().commit();
        return account;
    }

    @Override
    public Account update(Long id, Account account) {
        Account existingAccount = manager.find(Account.class, id);
        if (existingAccount != null) {
            manager.getTransaction().begin();
            existingAccount.setAccountNumber(account.getAccountNumber());
            existingAccount.setBalance(account.getBalance());
            manager.getTransaction().commit();
        }
        return existingAccount;
    }

    @Override
    public Account getById(Long id) {
        return manager.find(Account.class, id);
    }

    @Override
    public Account getByAccountNumber(String accountNumber) {
        return manager.createQuery("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber", Account.class)
                .setParameter("accountNumber", accountNumber)
                .getSingleResult();
    }

    @Override
    public boolean delete(Long id) {
        Account account = manager.find(Account.class, id);
        if (account != null) {
            manager.getTransaction().begin();
            manager.remove(account);
            manager.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public List<Account> getAll() {
        return manager.createQuery("SELECT a FROM Account a", Account.class).getResultList();
    }

    @Override
    public Customer getCustomerByAccountId(Long accountId) {
        Account account = manager.find(Account.class, accountId);
        return account != null ? account.getCustomer() : null;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        manager.getTransaction().begin();
        manager.persist(transaction);
        manager.getTransaction().commit();
        return transaction;
    }

    @Override
    public Account getAccountByCustomerId(Long customerId) {
        return manager.createQuery("SELECT a FROM Account a WHERE a.customer.id = :customerId", Account.class)
                .setParameter("customerId", customerId)
                .getSingleResult();
    }

    @Override
    public Account getAccountByCustomerEmail(String email) {
        return manager.createQuery("SELECT a FROM Account a WHERE a.customer.email = :email", Account.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}