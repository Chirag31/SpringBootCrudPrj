package com.hackerrank.eshopping.product.dashboard.repositpry;

import java.util.Comparator;

import com.hackerrank.eshopping.product.dashboard.model.Product;

public class DiscountedPriceSorter implements Comparator<Product>{

	@Override
	public int compare(Product o1, Product o2) {
		// TODO Auto-generated method stub
		return o1.getDiscountedPrice().compareTo(o2.getDiscountedPrice());
	}
	
	

}
