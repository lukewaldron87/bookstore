package com.waldronprojects.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.service.CustomerService;

@Controller
@RequestMapping("/employee/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/list")
	public String listCustomers(Model customerModel){
		
		List<Customer> customerList = customerService.getCustomers();
		customerModel.addAttribute("customers", customerList);
		return "/employee/list-customers";
	}
	
	@RequestMapping("showFormForUpdate")
	public String showFormForUpdate(@ModelAttribute("userId")Long id,
									Model customerModel) {
		
		Customer customer = customerService.getCustomer(id);
		customerModel.addAttribute("customer", customer);
		return "/employee/customer-form";
	}
	
	@RequestMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer")Customer customer) {
		
		customerService.saveCustomer(customer);
		return "redirect:/employee/customer/list";
	}
	
	@RequestMapping("/delete")
	public String delete(@ModelAttribute("userId")Long id){
		customerService.deleteCustomer(id);
		return "redirect:/employee/customer/list";
	}
}
