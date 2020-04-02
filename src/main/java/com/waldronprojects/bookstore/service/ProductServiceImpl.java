package com.waldronprojects.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waldronprojects.bookstore.dao.ProductDao;
import com.waldronprojects.bookstore.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	// inject the DAO
	@Autowired
	private ProductDao productDao;
	
	@Override
	@Transactional
	public Product getProduct(int id) {
		return productDao.getProduct(id);
	}

	@Override
	@Transactional
	public List<Product> getProducts() {
		return productDao.getProducts();
	}

	@Override
	@Transactional
	public void saveProduct(Product product) {
		productDao.saveProduct(product);
	}

	@Override
	@Transactional
	public void deteteProduct(int id) {
		productDao.deteteProduct(id);
	}

}
