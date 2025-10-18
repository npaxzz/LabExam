package com.exam.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exam.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	Page<Order> findByCustomerId(Long customerId, Pageable pageable);

	@Query("SELECT DISTINCT o FROM Order o JOIN o.lines ol WHERE ol.product.id = :productId")
	Page<Order> findByProductId(@Param("productId") Long productId, Pageable pageable);

	Page<Order> findByOrderDateBetween(LocalDateTime from, LocalDateTime to, Pageable pageable);

}