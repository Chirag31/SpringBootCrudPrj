package com.hackerrank.eshopping.product.dashboard.repositpry;

import java.util.Comparator;

import org.springframework.util.comparator.BooleanComparator;

import com.hackerrank.eshopping.product.dashboard.model.Product;

public class ProductAviabilitySorter implements Comparator<Product> {

	public static final BooleanComparator TRUE_HIGH = new BooleanComparator(false);

	private boolean trueHigh;

	@Override
	public int compare(Product o1, Product o2) {
		// TODO Auto-generated method stub
		boolean v1 = o1.getAvailability();
		boolean v2 = o2.getAvailability();
		return (v1 ^ v2) ? ((v1 ^ this.trueHigh) ? 1 : -1) : 0;
	}

}
