package com.exam.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.DTO.OrderLineDTO;
import com.exam.DTO.OrderRequestDTO;
import com.exam.entity.Customer;
import com.exam.entity.Order;
import com.exam.entity.OrderLine;
import com.exam.entity.Product;
import com.exam.repository.CustomerRepository;
import com.exam.repository.OrderRepository;
import com.exam.repository.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private ProductRepository productRepo;

	@Override
	@Transactional
	public Order create(OrderRequestDTO dto) {
		Customer c = customerRepo.findById(dto.customerId)
				.orElseThrow(() -> new IllegalArgumentException("Customer not found"));
		Order order = new Order();
		order.setCustomer(c);
		order.setOrderDate(LocalDateTime.now());

		if (dto.lines != null) {
			for (OrderLineDTO l : dto.lines) {
				Product p = productRepo.findById(l.productId)
						.orElseThrow(() -> new IllegalArgumentException("Product not found"));
				OrderLine ol = new OrderLine(p, l.quantity, l.unitPrice != null ? l.unitPrice : p.getPrice());
				order.addLine(ol);
			}
		}
		return orderRepo.save(order);
	}

	@Override
	@Transactional
	public Order update(Long id, OrderRequestDTO dto) {
		Order order = orderRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
		if (dto.customerId != null) {
			Customer c = customerRepo.findById(dto.customerId)
					.orElseThrow(() -> new IllegalArgumentException("Customer not found"));
			order.setCustomer(c);
		}
		order.setLines(new java.util.ArrayList<>());
		if (dto.lines != null) {
			for (OrderLineDTO l : dto.lines) {
				Product p = productRepo.findById(l.productId)
						.orElseThrow(() -> new IllegalArgumentException("Product not found"));
				OrderLine ol = new OrderLine(p, l.quantity, l.unitPrice != null ? l.unitPrice : p.getPrice());
				order.addLine(ol);
			}
		}
		return orderRepo.save(order);
	}

	@Override
	public Order getById(Long id) {
		return orderRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
	}

	@Override
	public void delete(Long id) {
		orderRepo.deleteById(id);
	}

	@Override
	public Page<Order> search(Long customerId, Long productId, LocalDateTime from, LocalDateTime to,
			Pageable pageable) {
		if (customerId != null)
			return orderRepo.findByCustomerId(customerId, pageable);
		if (productId != null)
			return orderRepo.findByProductId(productId, pageable);
		if (from != null && to != null)
			return orderRepo.findByOrderDateBetween(from, to, pageable);
		return orderRepo.findAll(pageable);
	}
}