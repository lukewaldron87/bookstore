package com.waldronprojects.bookstore.controller;

import com.waldronprojects.bookstore.dto.CustomerDto;
import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.service.CustomerService;
import com.waldronprojects.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employee/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private UserService userService;
	
	@RequestMapping("/list")
	public String listCustomers(Model customerModel){
		List<Customer> customerList = customerService.getCustomers();
		customerModel.addAttribute("customers", customerList);
		return "employee/list-customers";
	}
	
	@RequestMapping("showFormForUpdate")
	public String showFormForUpdate(@ModelAttribute("userId")Long id,
									Model customerModel) {
		UserDto userDto = userService.getUser(id);
		customerModel.addAttribute("customer", userDto);
		return "employee/customer-form";
	}

	@RequestMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer")CustomerDto customer) {
		userService.saveUser(customer);
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(@ModelAttribute("userId")Long id){
		userService.deleteUser(id);
		return "redirect:list";
	}
}
