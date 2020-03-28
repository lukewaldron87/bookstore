package com.waldronprojects.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.waldronprojects.bookstore.entity.Product;
import com.waldronprojects.bookstore.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/list")
	public String listProducts(Model productModel) {
		
		List<Product> productList = productService.getProducts();
		productModel.addAttribute("products", productList);
		
		return "list-product";
	}
}
