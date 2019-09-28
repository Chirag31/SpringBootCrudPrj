package com.hackerrank.eshopping.product.dashboard.service;

import java.util.List;

import com.hackerrank.eshopping.product.dashboard.model.Product;

public interface ProductService {
	public Product addProduct(Product product);
	public List<Product> getAllProducts();
	public Product getProductById(long productId);
	public Product updateProduct(Product product, long id);
	public List<Product> getProductByCategory(String category);
	public List<Product> getProductByCategoryAvailability(String category, int availability);
}
