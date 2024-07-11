package com.example.demo.dao;

import com.example.demo.model.Customer;

import java.util.List;

public interface CustomerDao {
    Customer save(Customer customer);
    Customer update(Long id, Customer customer);
    Customer getById(Long id);
    boolean delete(Long id);
    List<Customer> getAll();

    Customer getByEmail(String email);
}