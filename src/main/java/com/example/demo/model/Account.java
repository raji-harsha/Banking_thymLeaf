package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String accountNumber;
	private Double balance = 500.0;

    @OneToOne
	@JoinColumn(name = "id", insertable = false, updatable = false)
    private Customer customer;


	public Account() {
		this.accountNumber = generateAccountNumber();
	}

	private String generateAccountNumber() {
		return new Random().ints(10, 0, 10)
				.mapToObj(Integer::toString)
				.limit(12)
				.collect(Collectors.joining());
	}

	public Long getId() {
		return id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}