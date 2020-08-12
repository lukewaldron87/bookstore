package com.waldronprojects.bookstore.controller;

import com.waldronprojects.bookstore.dto.CustomerDto;
import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;

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
		model.addAttribute("customer", new CustomerDto());
		return "customer/registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
					@Valid @ModelAttribute("customer") CustomerDto customer,
					BindingResult bindingResult,
					Model model) {
		
		String username = customer.getUsername();
		logger.info("Processing registration form for new user: "+username);
		
		if(bindingResult.hasErrors()) {
			return "customer/registration-form";
		}
		
		// check if user already exists
		User user = userService.findUsername(username);
		if(user == null) {
			model.addAttribute("customer", new CustomerDto());
			model.addAttribute("registrationError", "User name already exists.");
			logger.warning("User name already exists.");
        	return "customer/registration-form";
		}
		
		//userService.save(user);
		userService.saveUser(customer);
		logger.info("Successfully created user: " + username);

        return "customer/registration-confirmation";	
	}
}
