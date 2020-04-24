package com.waldronprojects.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@RequestMapping("showEmployeeMenu")
	public String showEmployeeMenu(Model employeeModel) {
		return "/employee/employee-menu";
	}
	
}
