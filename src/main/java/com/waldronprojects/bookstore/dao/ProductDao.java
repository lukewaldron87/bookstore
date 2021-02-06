package com.waldronprojects.bookstore.dao;

import java.util.List;

import com.waldronprojects.bookstore.entity.Product;

public interface ProductDao {

	Product getProduct(Long id);
	
	List<Product> getProducts();

	void saveProduct(Product product);
	
	void deleteProduct(Long id);

}
