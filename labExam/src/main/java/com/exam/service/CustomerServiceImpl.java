package com.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entity.Customer;
import com.exam.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer create(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer getById(Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
	}

	@Override
	public Customer update(Long id, Customer customer) {
		Customer existing = getById(id);
		existing.setName(customer.getName());
		existing.setEmail(customer.getEmail());
		existing.setPhone(customer.getPhone());
		return customerRepository.save(existing);
	}

	@Override
	public void delete(Long id) {
		customerRepository.deleteById(id);
	}

	@Override
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}
}
