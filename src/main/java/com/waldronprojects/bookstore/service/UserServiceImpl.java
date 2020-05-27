package com.waldronprojects.bookstore.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
	public void deleteUser(Long id) {
		userDao.deleteUser(id);
	}
	
	@Override
	@Transactional
	public void saveUser(UserDto userDto) {

		/****** CREATE UTILITY TO CONVERT DTO TO ENTITY ******/
		User user = createUserObject(userDto);
		userDao.save(user);
	}
	
	
	/**************** CREATE UTILITY TO CONVERT DTO TO ENTITY ****************/
	private User createUserObject(UserDto userDto) {
		User user = new User();
		if(userDto instanceof CustomerDto) {
			user = createCustomerObjectFromDto((CustomerDto)userDto);
		}else if(userDto instanceof EmployeeDto) {
			user = createEmployeeObjectFromDto((EmployeeDto)userDto);
		}
		return user;
	}
	
	private Customer createCustomerObjectFromDto(CustomerDto  customerDto) {
			Customer customer = new Customer();
			customer.setId(customerDto.getId());
			customer.setUsername(customerDto.getUsername());
			customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
			customer.setFirstName(customerDto.getFirstName());
			customer.setLastName(customerDto.getLastName());
			customer.setEmail(customerDto.getEmail());
			customer.setAddressLine1(customerDto.getAddressLine1());
			customer.setAddressLine2(customerDto.getAddressLine2());
			customer.setCity(customerDto.getCity());
			customer.setCountry(customerDto.getCountry());
			customer.setPostCode(customerDto.getPostCode());
			customer.setPhoneNumber(customerDto.getPhoneNumber());
			customer.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_CUSTOMER")));
			return customer;
	}
	
	private Employee createEmployeeObjectFromDto(EmployeeDto employeeDto) {
			Employee employee = new Employee();
			employee.setId(employeeDto.getId());
			employee.setUsername(employeeDto.getUsername());
			employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
			employee.setFirstName(employeeDto.getFirstName());
			employee.setLastName(employeeDto.getLastName());
			employee.setEmail(employeeDto.getEmail());
			employee.setDepartment(employeeDto.getDepartment());
			employee.setTitle(employeeDto.getTitle());
			Collection<Role> roleCollection = createEmployeeRoleCollection(employeeDto);
			employee.setRoles(roleCollection);
			return employee;
	}
	
	private Collection<Role> createEmployeeRoleCollection(EmployeeDto employeeDto) {
		Collection<Role> roleCollection = new ArrayList<Role>();
		roleCollection.add(roleDao.findRoleByName("ROLE_EMPLOYEE"));
		if(employeeDto.getIsAdmin()) {
			roleCollection.add(roleDao.findRoleByName("ROLE_ADMIN"));
		}
		return roleCollection;
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
	
	@Override
	@Transactional
	public  UserDto getUser(Long id) {
		User user = userDao.findUserById(id);
		return createUserDtoObject(user);
	}
	
	/**************** CREATE UTILITY TO CONVERT ENTITY TO DTO ****************/
	private UserDto createUserDtoObject(User user) {
		UserDto userDto = new UserDto();
		if(user instanceof Customer) {
			userDto = createCustomerDtoObject((Customer)user);
		}else if(userDto.getClass().isInstance(new EmployeeDto())) {
			userDto = createEmployeeDtoObject((Employee)user);
		}
		return userDto;
	}

	private UserDto createCustomerDtoObject(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(customer.getId());
		customerDto.setUsername(customer.getUsername());
		customerDto.setPassword(customer.getPassword());
		customerDto.setMatchingPassword(customer.getPassword());
		customerDto.setFirstName(customer.getFirstName());
		customerDto.setLastName(customer.getLastName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setRoles(customer.getRoles());
		customerDto.setAddressLine1(customer.getAddressLine1());
		customerDto.setAddressLine2(customer.getAddressLine2());
		customerDto.setCity(customer.getCity());
		customerDto.setCountry(customer.getCountry());
		customerDto.setPostCode(customer.getPostCode());
		customerDto.setPhoneNumber(customer.getPhoneNumber());
		return customerDto;
	}

	private UserDto createEmployeeDtoObject(Employee employee) {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(employee.getId());
		employeeDto.setUsername(employee.getUsername());
		employeeDto.setPassword(employee.getPassword());
		employeeDto.setMatchingPassword(employee.getPassword());
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setLastName(employee.getLastName());
		employeeDto.setEmail(employee.getEmail());
		employeeDto.setRoles(employee.getRoles());
		employeeDto.setDepartment(employee.getDepartment());
		employeeDto.setTitle(employee.getTitle());
		employeeDto.setIsAdmin(checkEmployeeIsAdmin(employee));
		return employeeDto;
	}
	
	private boolean checkEmployeeIsAdmin(Employee employee) {
		for(Role role: employee.getRoles()) {
			if(role.getName().equals("ROLE_ADMIN")){
				return true;
			}
		}
		return false;
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
