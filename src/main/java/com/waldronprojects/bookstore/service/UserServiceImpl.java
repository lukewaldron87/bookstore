package com.waldronprojects.bookstore.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waldronprojects.bookstore.dao.RoleDao;
import com.waldronprojects.bookstore.dao.UserDao;
import com.waldronprojects.bookstore.dto.CustomerDto;
import com.waldronprojects.bookstore.dto.EmployeeDto;
import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.Employee;
import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public User findUsername(String username) {
		return userDao.findByUsername(username); 
	}
	
	@Override
	@Transactional
	public void save(UserDto userDto) {

		/****** CREATE UTILITY TO CONVERT DTO TO ENTITY ******/
		User user = new User();
		
		if(userDto.getClass().isInstance(new CustomerDto())) {
			user = setCustomerFields((CustomerDto)userDto);
			user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_CUSTOMER")));
		}else if(userDto.getClass().isInstance(new EmployeeDto())) {
			user = setEmployeeFields((EmployeeDto)userDto);
			user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));
		}
		
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		userDao.save(user);
	}
	
	private Customer setCustomerFields(CustomerDto  customerDto) {
		Customer customer = new Customer();
		customer.setAddressLine1(customerDto.getAddressLine1());
		customer.setAddressLine2(customerDto.getAddressLine2());
		customer.setCity(customerDto.getCity());
		customer.setCountry(customerDto.getCountry());
		customer.setPostCode(customerDto.getPostCode());
		customer.setPhoneNumber(customerDto.getPhoneNumber());
		return customer;
	}
	
	private Employee setEmployeeFields(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		employee.setDepartment(employeeDto.getDepartment());
		employee.setTitle(employeeDto.getTitle());
		return employee;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}


	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
