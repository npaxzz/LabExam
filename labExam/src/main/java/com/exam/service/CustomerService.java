package com.exam.service;

import java.util.List;

import com.exam.entity.Customer;

public interface CustomerService {

	Customer create(Customer customer);

	Customer getById(Long id);

	Customer update(Long id, Customer customer);

	void delete(Long id);

	List<Customer> getAll();
}
