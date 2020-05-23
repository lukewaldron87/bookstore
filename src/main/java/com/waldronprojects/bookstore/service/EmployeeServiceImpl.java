package com.waldronprojects.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waldronprojects.bookstore.dao.EmployeeDao;
import com.waldronprojects.bookstore.dao.UserDao;
import com.waldronprojects.bookstore.entity.Employee;
import com.waldronprojects.bookstore.entity.User;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	@Transactional
	public List<Employee> getEmployees() {
		return employeeDao.getEmployees();
	}

	@Override
	@Transactional
	public Employee getEmployee(Long id) {
		
		User user = userDao.findUserById(id);
		if(user instanceof Employee) {
			return (Employee) user;
		}else {
			return new Employee();
		}
	}

	@Override
	@Transactional
	public void saveEmployee(Employee employee) {
		userDao.save(employee);
	}

	@Override
	@Transactional
	public void deleteEmployee(Employee employee) {
		userDao.save(employee);
	}

}
