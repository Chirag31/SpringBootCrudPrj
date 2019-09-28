package com.hackerrank.eshopping.product.dashboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.hackerrank.eshopping.product.dashboard.model.Product;
import com.hackerrank.eshopping.product.dashboard.repositpry.CustomErrorType;

import com.hackerrank.eshopping.product.dashboard.service.ProductService;

@RestController
@RequestMapping(value = "/getproducts")
public class ProductsController1 {

	@Autowired
	ProductService ProductService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Product> fetchProducts() {
		List<Product> productList = ProductService.getAllProducts();
		if (productList != null && productList.size() > 0) {
			return productList;
		}
		return null;
	}



	
}
