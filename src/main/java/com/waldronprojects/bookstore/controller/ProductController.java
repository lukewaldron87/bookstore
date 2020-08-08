package com.waldronprojects.bookstore.controller;

import com.waldronprojects.bookstore.entity.Product;
import com.waldronprojects.bookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employee/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/list")
	public String listProducts(Model productModel) {
		List<Product> productList = productService.getProducts();
		productModel.addAttribute("products", productList);
		return "employee/list-products";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "employee/product-form";
	}
	
	
	@PostMapping("/saveProduct")
	public String saveProduct(@ModelAttribute("product")Product product) {
		productService.saveProduct(product);
		return "redirect:list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@ModelAttribute("productId")int id,
									Model model) {
		Product product = productService.getProduct(id);
		model.addAttribute("product", product);
		return "employee/product-form";
	}
	
	@GetMapping("/delete")
	public String delete(@ModelAttribute("productId")int id) {
		productService.deleteProduct(id);
		return "redirect:list";
	}
}
