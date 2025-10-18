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

import com.exam.entity.Product;
import com.exam.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	// ค้นหา Products ทั้งหมด
	// http://localhost:8080/api/products
	@GetMapping
	public ResponseEntity<List<Product>> list() {
		return ResponseEntity.ok(productService.getAll());
	}

	// ค้นหา Product ตาม ID
	// http://localhost:8080/api/products/2
	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable Long id) {
		return ResponseEntity.ok(productService.getById(id));
	}

	// เพิ่ม Product
	// http://localhost:8080/api/products
	// json { "name": "Laptop", "price": 35000 }
	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product) {
		return ResponseEntity.ok(productService.create(product));
	}

	// แก้ไข Product
	// http://localhost:8080/api/products/2
	// json { "name": "Laptop Pro", "price": 38000 }
	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
		return ResponseEntity.ok(productService.update(id, product));
	}

	// ลบ Product
	// http://localhost:8080/api/products/2
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
