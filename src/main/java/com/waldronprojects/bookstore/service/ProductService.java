package com.waldronprojects.bookstore.service;

import java.util.List;

import com.waldronprojects.bookstore.entity.Product;

public interface ProductService {

	public Product getProduct(Long id);
	
	public List<Product> getProducts();

	public void saveProduct(Product product);
	
	public void deleteProduct(Long id);
	

}
