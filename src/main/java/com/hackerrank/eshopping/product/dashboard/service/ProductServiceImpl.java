package com.hackerrank.eshopping.product.dashboard.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.eshopping.product.dashboard.controller.ProductsController;
import com.hackerrank.eshopping.product.dashboard.model.Product;
import com.hackerrank.eshopping.product.dashboard.repositpry.DiscountedPriceSorter;
import com.hackerrank.eshopping.product.dashboard.repositpry.ProductAviabilitySorter;
import com.hackerrank.eshopping.product.dashboard.repositpry.ProductRepository;
import com.hackerrank.eshopping.product.dashboard.repositpry.ProductRepositoryCustom;
import com.hackerrank.eshopping.product.dashboard.repositpry.ProductSortById;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductRepositoryCustom productRepositoryCustom;

	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);

	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> listOfProducts = productRepositoryCustom.fetchAllProduct();
		//Collections.sort(listOfProducts, new ProductSortById());
		return listOfProducts;

	}

	@Override
	public Product getProductById(long productId) {
		// TODO Auto-generated method stub
		return productRepository.findById(productId);
	}

	@Override
	public Product updateProduct(Product product, long id) {
		// TODO Auto-generated method stub

		Product productDetails = productRepository.findById(id);
		if (productDetails != null) {
			if(product.getName() != null ) {
				productDetails.setName(product.getName());
			}
			if(product.getCategory() != null ) {
				productDetails.setCategory(product.getCategory());
			}
			if(product.getDiscountedPrice() != null ) {
				productDetails.setDiscountedPrice(product.getDiscountedPrice());
			}
			if(product.getRetailPrice() != null ) {
				productDetails.setRetailPrice(product.getRetailPrice());
			}
			if(product.getAvailability() != null ) {
				productDetails.setAvailability(product.getAvailability());
			}
		
			
			
			productRepository.save(productDetails);
			return productDetails;
		} else {

			return null;
		}

	}

	@Override
	public List<Product> getProductByCategory(String category) {
		// TODO Auto-generated method stub
		List<Product> listOfCatogery = new ArrayList<Product>();
		listOfCatogery = productRepositoryCustom.findByCategoryName(category);
		
	
		Collections.sort(listOfCatogery, new ProductAviabilitySorter()
                .thenComparing(new DiscountedPriceSorter())
                .thenComparing(new ProductSortById()));
		
		return listOfCatogery;

	}

	@Override
	public List<Product> getProductByCategoryAvailability(String category, int availability) {
		// TODO Auto-generated method stub
		Boolean catVal = false;
		if (availability == 0) {
			catVal = false;
		} else {
			catVal = true;
		}
		List<Product> listOfCatogery = new ArrayList<Product>();
		listOfCatogery = productRepositoryCustom.findByCategoryAvailability(category, catVal);
		return listOfCatogery;
	}

}
