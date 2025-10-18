package com.exam.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.DTO.OrderRequestDTO;
import com.exam.entity.Order;
import com.exam.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;

	// ค้นหา Order ทั้งหมด
	// localhost:8080/api/orders
	@GetMapping
	public ResponseEntity<List<Order>> list(@RequestParam(required = false) Long customerId,
			@RequestParam(required = false) Long productId,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
			Pageable pageable) {

		List<Order> orders = orderService.search(customerId, productId, from, to, pageable).getContent();
		return ResponseEntity.ok(orders);
	}

	// ค้นหา Order ตาม ID
	// localhost:8080/api/orders/2
	@GetMapping("/{id}")
	public ResponseEntity<Order> get(@PathVariable Long id) {
		return ResponseEntity.ok(orderService.getById(id));
	}

	// เพิ่ม Order พร้อม OrderLine
	// localhost:8080/api/orders
	// json { "customerId": 1, "orderDate": "2025-10-18T20:00:00", "lines": [ {
	// "productId": 1, "quantity": 2, "unitPrice": 35000 } ] }
	@PostMapping
	public ResponseEntity<Order> create(@RequestBody OrderRequestDTO dto) {
		return ResponseEntity.ok(orderService.create(dto));
	}

	// แก้ไข Order
	// localhost:8080/api/orders/2
	// json { "customerId": 1, "orderDate": "2025-10-18T21:00:00", "lines": [ {
	// "productId": 1, "quantity": 3, "unitPrice": 35000 } ] }
	@PutMapping("/{id}")
	public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody OrderRequestDTO dto) {
		return ResponseEntity.ok(orderService.update(id, dto));
	}

	// ลบ Order
	// localhost:8080/api/orders/2
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		orderService.delete(id);
		return ResponseEntity.noContent().build();
	}

}