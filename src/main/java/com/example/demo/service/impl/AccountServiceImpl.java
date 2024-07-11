package com.example.demo.service.impl;

import com.example.demo.dao.AccountDao;
import com.example.demo.dao.TransactionDao;
import com.example.demo.dao.impl.AccountDaoImpl;
import com.example.demo.dao.impl.TransactionDaoImpl;
import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionType;
import com.example.demo.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;
    private TransactionDao transactionDao;


    public AccountServiceImpl() {
        this.accountDao = new AccountDaoImpl();
        this.transactionDao = new TransactionDaoImpl();
    }

    @Override
    public Account addAccount(Account account) {
        return accountDao.save(account);
    }

    @Override
    public Account updateAccount(Long id, Account account) {
        return accountDao.update(id, account);
    }

    @Override
    public Account getAccountById(Long id) {
        return accountDao.getById(id);
    }

    @Override
    public boolean deleteAccount(Long id) {
        return accountDao.delete(id);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountDao.getAll();
    }

    @Override
    public Customer getCustomerByAccountId(Long accountId) {
        Account account = accountDao.getById(accountId);
        return account != null ? account.getCustomer() : null;
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(String accountId) {
        return transactionDao.getByAccountId(accountId);
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        return transactionDao.addTransaction(transaction);
    }

    //get account by customer id
    @Override
    public Account getAccountByCustomerId(Long customerId) {
        return accountDao.getAccountByCustomerId(customerId);
    }

    @Override
    public Account getAccountByCustomerEmail(String email) {
        return accountDao.getAccountByCustomerEmail(email);
    }

    //Implement code for deposit amount and add transaction to transaction table
    @Override
    public Account depositAmount(String accNo, double amount) {
        Account account = accountDao.getByAccountNumber(accNo);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            accountDao.update(account.getId(), account);

            Transaction transaction = new Transaction(amount, account.getAccountNumber(),account.getAccountNumber());
            transaction.setType(TransactionType.DEPOSIT.getType());
            accountDao.addTransaction(transaction);
        }
        return account;
    }

    //Implement code for withdraw amount
    @Override
    public Account withdrawAmount(String accNo, double amount) {
        Account account = accountDao.getByAccountNumber(accNo);
        if (account != null && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            accountDao.update(account.getId(), account);

            Transaction transaction = new Transaction(amount, account.getAccountNumber(),account.getAccountNumber());
            transaction.setType(TransactionType.WITHDRAWAL.getType());
            accountDao.addTransaction(transaction);
        }
        return account;
    }

    //Implement code for transfer amount
    @Override
    public Account transferAmount(String fromAccountId, String toAccountId, double amount) {
        Account fromAccount = accountDao.getByAccountNumber(fromAccountId);
        Account toAccount = accountDao.getByAccountNumber(toAccountId);
        if (fromAccount != null && toAccount != null && fromAccount.getBalance() >= amount) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
            accountDao.update(fromAccount.getId(), fromAccount);
            accountDao.update(toAccount.getId(), toAccount);

            Transaction transaction = new Transaction(amount, fromAccount.getAccountNumber(),toAccount.getAccountNumber());
            transaction.setType(TransactionType.TRANSFER.getType());
            accountDao.addTransaction(transaction);

        }
        return fromAccount;
    }
}