package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer);
    Customer getCustomerById(Long id);
    boolean deleteCustomer(Long id);
    List<Customer> getAllCustomers();
    Account getAccountByCustomerId(Long customerId);

    Boolean authenticateCustomer(Long custId,String pass);

}