package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import com.example.demo.model.FormData;
import com.example.demo.model.Transaction;
import com.example.demo.service.AccountService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.impl.AccountServiceImpl;
import com.example.demo.service.impl.CustomerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private CustomerService service;

    @Autowired
    private AccountService accountService;

    public MainController() {
        super();
        this.service = new CustomerServiceImpl();
        this.accountService = new AccountServiceImpl();
    }

    @GetMapping("/register")
    public String showForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "register_form";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        List<Customer> customers = service.getAllCustomers();
        model.addAttribute("customers", customers);
        return "admin";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        FormData formData = new FormData();
        model.addAttribute("formdata", formData);
        return "login_form";
    }
    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public String submitForm(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Total count of errors are = " + bindingResult.getErrorCount());
            return "register_form";
        }
        System.out.println(customer);
        service.addCustomer(customer);

        return "register_success";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public String loginForm(@Valid @ModelAttribute("formdata") FormData formData, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Total count of errors are = " + bindingResult.getErrorCount());
            return "login_form";
        }
//        System.out.println(customer);
        Boolean isAuth  = service.authenticateCustomer(formData.getCustomerId(),formData.getPassword());

        if(isAuth)
        {
            return "redirect:/customer/account/" +formData.getCustomerId();
        }
        else
        {
            return "login_form";
        }
    }

    @GetMapping("/customer/account/{id}" )
    public String showAccount(Model model, @PathVariable("id") long customerID )
    {
        Account acc = accountService.getAccountByCustomerId(customerID);
        Customer customer = acc.getCustomer();
        List<Transaction> transactions = accountService.getTransactionsByAccountId(acc.getAccountNumber());
        FormData formData = new FormData();
        formData.setAccountId(acc.getAccountNumber());
        model.addAttribute("formdata", formData);
        model.addAttribute("account", acc);
        model.addAttribute("customer", customer);
        model.addAttribute("transactions", transactions);
        return "account";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/account/deposit")
    public String deposit(@Valid @ModelAttribute("formdata") FormData formData, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Total count of errors are = " + bindingResult.getErrorCount());
            return "register_form";
        }
        System.out.println(formData);
        Account acc = accountService.depositAmount(formData.getAccountId(), formData.getAmount());
        return "redirect:/customer/account/" + acc.getId();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/account/withdraw")
    public String withdraw(@Valid @ModelAttribute("formdata") FormData formData, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Total count of errors are = " + bindingResult.getErrorCount());
            return "register_form";
        }
        System.out.println(formData);
        Account acc = accountService.withdrawAmount(formData.getAccountId(), formData.getAmount());
        return "redirect:/customer/account/" + acc.getId();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/account/transfer")
    public String transfer(@Valid @ModelAttribute("formdata") FormData formData, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Total count of errors are = " + bindingResult.getErrorCount());
            return "register_form";
        }
        System.out.println(formData);
        Account acc = accountService.transferAmount(formData.getAccountId(), formData.getToAccountId(), formData.getAmount());
        return "redirect:/customer/account/" + acc.getId();
    }
}
