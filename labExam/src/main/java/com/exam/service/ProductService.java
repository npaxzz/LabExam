package com.exam.service;

import java.util.List;

import com.exam.entity.Product;

public interface ProductService {

	Product create(Product product);

	Product getById(Long id);

	Product update(Long id, Product product);

	void delete(Long id);

	List<Product> getAll();
}
