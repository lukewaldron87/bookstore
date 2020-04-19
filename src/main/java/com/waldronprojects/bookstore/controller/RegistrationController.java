package com.waldronprojects.bookstore.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.service.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private Logger logger = Logger.getLogger(getClass().getName());

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder .registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/showRegistrationForm")
	public String showRegistrationForm(Model model) {
		
		// should be a customer class????
		//model.addAttribute("crmUser", new CrmUser());
		model.addAttribute("customer", new Customer());
		return "registration-form";
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
					@Valid @ModelAttribute("customer") Customer customer,
					BindingResult bindingResult,
					Model model) {
		
		String userName = customer.getUserName();
		logger.info("Processing registration form for new user: "+userName);
		
		if(bindingResult.hasErrors()) {
			return "registration-form";
		}
		
		// check if user already exists
		User user = userService.findUserName(userName);
		if(user == null) {
			model.addAttribute("customer", new Customer());
			model.addAttribute("registrationError", "User name already exists.");
			logger.warning("User name already exists.");
        	return "registration-form";
		}
		
		//userService.save(user);
		userService.save(customer);
		logger.info("Successfully created user: " + userName);

        return "registration-confirmation";	
	}
}
