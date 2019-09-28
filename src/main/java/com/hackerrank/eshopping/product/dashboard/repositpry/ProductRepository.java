package com.hackerrank.eshopping.product.dashboard.repositpry;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hackerrank.eshopping.product.dashboard.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

	public Product findById(long productId);
	public List<Product> findByCategory(String findByCategory);
}
