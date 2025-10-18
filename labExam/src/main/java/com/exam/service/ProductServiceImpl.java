package com.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entity.Product;
import com.exam.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product create(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product getById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id " + id));
	}

	@Override
	public Product update(Long id, Product product) {
		Product existing = getById(id);
		existing.setName(product.getName());
		existing.setPrice(product.getPrice());
		return productRepository.save(existing);
	}

	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}
}
