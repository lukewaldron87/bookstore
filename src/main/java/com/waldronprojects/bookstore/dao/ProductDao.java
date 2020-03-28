package com.waldronprojects.bookstore.dao;

import java.util.List;

import com.waldronprojects.bookstore.entity.Product;

public interface ProductDao {

	public Product getProduct(int id);
	
	public List<Product> getProducts();

	public void saveProduct(Product product);
	
	public void deteteProduct(int id);

}
