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
@RequestMapping(value = "/products")
public class ProductsController {

	@Autowired
	ProductService ProductService;

	@RequestMapping(method = RequestMethod.GET, value = "/getproducts")
	public List<Product> fetchProducts() {
		List<Product> productList = ProductService.getAllProducts();
		if (productList != null && productList.size() > 0) {
			return productList;
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {

		Product productDtl = null;
		productDtl = ProductService.getProductById(product.getId());
		if (productDtl != null) {
			return new ResponseEntity(
					new CustomErrorType("Unable to create. A Product with name " + productDtl.getId() + " already exist."),
					HttpStatus.BAD_REQUEST);
		}

		ProductService.addProduct(product);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(product.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public ResponseEntity<?> getProductById(@PathVariable("productId") long productId) {

		Product productDtl = ProductService.getProductById(productId);

		if (productDtl != null) {
			return new ResponseEntity<Product>(productDtl, HttpStatus.OK);
		} else {

			return new ResponseEntity(new CustomErrorType("Product with id " + productId + " not found"),
					HttpStatus.NOT_FOUND);
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable long id) {
		Product productDtl = ProductService.updateProduct(product, id);
		if (productDtl != null) {
			return ResponseEntity.ok(productDtl);
		} else {

			return new ResponseEntity(new CustomErrorType("Product with id " + id + " not found"),
					HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProductByCategoryAvailability(@RequestParam(required = false) String category,
			@RequestParam(required = false) String availability) {

		if (availability != null && availability != "" && category!=null && category!="") {
			int cattogeryVal = 0;
			cattogeryVal = Integer.parseInt(availability);
			return ResponseEntity.ok(ProductService.getProductByCategoryAvailability(category, cattogeryVal));

		} else if(category != null && category != "") {
			return ResponseEntity.ok(ProductService.getProductByCategory(category));
		}else {
			
			List<Product> productList = ProductService.getAllProducts();
			if (productList != null && productList.size() > 0) {
				return ResponseEntity.ok(productList);
			}
		}
		return null;
	}

	
}
