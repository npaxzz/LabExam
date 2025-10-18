package com.exam.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.exam.DTO.OrderRequestDTO;
import com.exam.entity.Order;

public interface OrderService {

	Order create(OrderRequestDTO dto);

	Order update(Long id, OrderRequestDTO dto);

	Order getById(Long id);

	void delete(Long id);

	Page<Order> search(Long customerId, Long productId, LocalDateTime from, LocalDateTime to, Pageable pageable);
}
