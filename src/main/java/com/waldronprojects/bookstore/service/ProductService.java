package com.waldronprojects.bookstore.service;

import java.util.List;

import com.waldronprojects.bookstore.entity.Product;

public interface ProductService {

	Product getProduct(Long id);
	
	List<Product> getProducts();

	void saveProduct(Product product);
	
	void deleteProduct(Long id);
	

}
