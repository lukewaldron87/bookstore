package com.waldronprojects.bookstore.controller;

import com.waldronprojects.bookstore.dto.EmployeeDto;
import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.entity.factory.UserType;
import com.waldronprojects.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("showEmployeeMenu")
	public String showEmployeeMenu(Model employeeModel) {
		return "employee/employee-menu";
	}
	
	@RequestMapping("/list")
	public String listEmployee(Model employeeModel) {
		List<User> employeeList = userService.getUsersOfType(UserType.EMPLOYEE);
		employeeModel.addAttribute("employees", employeeList);
		return "employee/list-employees";
	}
	
	@RequestMapping("showFormForAdd")
	public String showFormForAdd(Model employeeModel){
		employeeModel.addAttribute("employee", new EmployeeDto());
		return "employee/employee-form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@ModelAttribute("userId") Long id,
									Model employeeModel) {
		UserDto userDto = userService.getUser(id);
		employeeModel.addAttribute("employee", userDto);
		return "employee/employee-update-form";
	}
	
	@RequestMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee")EmployeeDto employee) {
		userService.saveUser(employee);
		return "redirect:list";
	}
	
	@RequestMapping("/deleteEmployee")
	public String deleteEmployee(@ModelAttribute("userId")Long id) {
		userService.deleteUser(id);
		return "redirect:list";
	}
		
}
