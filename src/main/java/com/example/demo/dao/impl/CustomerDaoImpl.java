package com.example.demo.dao.impl;

import com.example.demo.dao.CustomerDao;
import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import com.example.demo.database.DBUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    private static EntityManager manager;

    public CustomerDaoImpl() {
        manager = DBUtil.getManager();
    }

    @Override
    public Customer save(Customer customer) {
        manager.getTransaction().begin();
        manager.persist(customer);

        // Create a new Account when a new Customer is saved
        Account account = new Account();
        account.setCustomer(customer);
        manager.persist(account);

        manager.getTransaction().commit();
        return customer;
    }

    @Override
    public Customer update(Long id, Customer customer) {
        Customer existingCustomer = manager.find(Customer.class, id);
        if (existingCustomer != null) {
            manager.getTransaction().begin();
            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            manager.getTransaction().commit();
        }
        return existingCustomer;
    }

    @Override
    public Customer getById(Long id) {
        return manager.find(Customer.class, id);
    }

    @Override
    public boolean delete(Long id) {
        Customer customer = manager.find(Customer.class, id);
        if (customer != null) {
            manager.getTransaction().begin();
            manager.remove(customer);
            manager.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public List<Customer> getAll() {
        return manager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    @Override
    public Customer getByEmail(String email) {
        List<Customer> customers = manager.createQuery("SELECT c FROM Customer c WHERE c.email = :email", Customer.class)
                .setParameter("email", email)
                .getResultList();
        return customers.isEmpty() ? null : customers.get(0);
    }
}