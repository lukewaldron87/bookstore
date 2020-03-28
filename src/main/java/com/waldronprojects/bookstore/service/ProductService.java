package com.waldronprojects.bookstore.service;

import java.util.List;

import com.waldronprojects.bookstore.entity.Product;

public interface ProductService {

	public Product getProduct(int id);
	
	public List<Product> getProducts();

	public void saveProduct(Product product);
	
	public void deteteProduct(int id);
	

}
