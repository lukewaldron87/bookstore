package com.waldronprojects.bookstore.service;

import java.util.List;

import com.waldronprojects.bookstore.entity.Employee;

public interface EmployeeService {

	public List<Employee> getEmployees();

	public Employee getEmployee(Long id);

	public void saveEmployee(Employee employee);

	public void deleteEmployee(Employee employee);

}
