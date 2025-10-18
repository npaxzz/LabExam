package com.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entity.Customer;
import com.exam.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	// ค้นหา Customers ทั้งหมด
	// http://localhost:8080/api/customers
	@GetMapping
	public ResponseEntity<List<Customer>> list() {
		return ResponseEntity.ok(customerService.getAll());
	}

	// ค้นหา Customer ตาม ID
	// http://localhost:8080/api/customers/2
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getById(@PathVariable Long id) {
		return ResponseEntity.ok(customerService.getById(id));
	}

	// เพิ่ม Customer
	// http://localhost:8080/api/customers
	// json { "name": "ทับทิม", "email": "tubtim@example.com", "phone": "0812345678"
	// }
	@PostMapping
	public ResponseEntity<Customer> create(@RequestBody Customer customer) {
		return ResponseEntity.ok(customerService.create(customer));
	}

	// แก้ไข Customer
	// http://localhost:8080/api/customers/2
	// json { "name": "สมชาย ใจดี", "email": "somchai2@example.com", "phone":
	// "0899999999" }
	@PutMapping("/{id}")
	public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer customer) {
		return ResponseEntity.ok(customerService.update(id, customer));
	}

	// ลบ Customer
	// http://localhost:8080/api/customers/2
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		customerService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
