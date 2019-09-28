package com.hackerrank.eshopping.product.dashboard.repositpry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hackerrank.eshopping.product.dashboard.model.Product;

@Repository
public class ProductRepositoryCustom {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	    public List<Product> findByCategoryName(String category) {
	        return jdbcTemplate.query(
	                "select * from product where category=?",
	                new Object[]{category},
	                (rs, rowNum) ->
	                        new Product(
	                                rs.getLong("id"),
	                                rs.getString("name"),
	                                rs.getString("category"),
	                                rs.getDouble("retail_price"),
	                                rs.getDouble("discounted_price"),                           
	                                rs.getBoolean("availability")
	                        )
	        );
	    }
	    
	    public List<Product> fetchAllProduct() {
	        return jdbcTemplate.query(
	                "select * from product order by id",
	                new Object[]{},
	                (rs, rowNum) ->
	                        new Product(
	                                rs.getLong("id"),
	                                rs.getString("name"),
	                                rs.getString("category"),
	                                rs.getDouble("retail_price"),
	                                rs.getDouble("discounted_price"),                           
	                                rs.getBoolean("availability")
	                        )
	        );
	    }
	    
	    
	    public List<Product> findByCategoryAvailability(String category,Boolean availability) {
	        return jdbcTemplate.query(
	                "select * from product where category=? and availability=?",
	                new Object[]{category,availability},
	                (rs, rowNum) ->
	                        new Product(
	                                rs.getLong("id"),
	                                rs.getString("name"),
	                                rs.getString("category"),
	                                rs.getDouble("retail_price"),
	                                rs.getDouble("discounted_price"),                           
	                                rs.getBoolean("availability")
	                        )
	        );
	    }
}
	


