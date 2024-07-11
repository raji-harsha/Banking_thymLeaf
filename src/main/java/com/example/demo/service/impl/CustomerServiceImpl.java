package com.example.demo.service.impl;

import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.impl.CustomerDaoImpl;
import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;

    public CustomerServiceImpl()
    {
        this.customerDao = new CustomerDaoImpl();
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        return customerDao.update(id, customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerDao.getById(id);
    }

    @Override
    public boolean deleteCustomer(Long id) {
        return customerDao.delete(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAll();
    }

    @Override
    public Account getAccountByCustomerId(Long customerId) {
        Customer customer = customerDao.getById(customerId);
        return customer != null ? customer.getAccount() : null;
    }

    @Override
    public Boolean authenticateCustomer(Long custId,String pass) {
        Boolean isAuthenticated = false;
        Customer customer = customerDao.getById(custId);
        if (customer != null && customer.getPassword().equals(pass))
            isAuthenticated = true;
        return isAuthenticated;
    }
}