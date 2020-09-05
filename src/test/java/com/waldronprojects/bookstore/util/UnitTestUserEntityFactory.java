package com.waldronprojects.bookstore.util;

import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.Employee;
import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.entity.factory.RoleEntityCollectionFactory;
import com.waldronprojects.bookstore.entity.factory.RoleType;
import com.waldronprojects.bookstore.entity.factory.UserEntityFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UnitTestUserEntityFactory extends UserEntityFactory {

	private RoleEntityCollectionFactory roleEntityCollectionFactory;
	private final Map<RoleType, User> FACTORY_MAP;

	public UnitTestUserEntityFactory() {
		roleEntityCollectionFactory = new UnitTestRoleEntityCollectionFactory();
		final HashMap<RoleType, User> factoryMap = new HashMap<>();
		factoryMap.put(RoleType.ROLE_CUSTOMER, createCustomerUser());
		factoryMap.put(RoleType.ROLE_EMPLOYEE, createRegularEmployeeUser());
		factoryMap.put(RoleType.ROLE_ADMIN, createAdminEmployeeUser());
		FACTORY_MAP = Collections.unmodifiableMap(factoryMap);
	}

	@Override
	public User createUser(RoleType RoleType) {
		return FACTORY_MAP.get(RoleType);
	}
	
	private Customer createCustomerUser() {
		Customer customer = new Customer();
		customer.setUsername("username0");
		customer.setPassword("password0");
		customer.setFirstName("firstName0");
		customer.setLastName("lastName0");
		customer.setEmail("0@email.com");
		customer.setAddressLine1("addressLine10");
		customer.setAddressLine2("addressLine20");
		customer.setCity("city0");
		customer.setCountry("country0");
		customer.setPostCode("postCode0");
		customer.setPhoneNumber(12340);
		Collection<Role> roleCollection = roleEntityCollectionFactory
				.createRole(RoleType.ROLE_CUSTOMER);
		customer.setRoles(roleCollection);
		return customer;
	}
	
	private Employee createRegularEmployeeUser() {
		Employee employee = createEmployeeObject();
		Collection<Role> roleCollection = roleEntityCollectionFactory
				.createRole(RoleType.ROLE_EMPLOYEE);
		employee.setRoles(roleCollection);
		return employee;
	}
	
	private Employee createAdminEmployeeUser() {
		Employee employee = createEmployeeObject();
		Collection<Role> roleCollection = roleEntityCollectionFactory
				.createRole(RoleType.ROLE_ADMIN);
		employee.setRoles(roleCollection);
		return employee;
	}
	
	private Employee createEmployeeObject() {
		Employee employee = new Employee();
		employee.setUsername("username0");
		employee.setPassword("password0");
		employee.setFirstName("firstName0");
		employee.setLastName("lastName0");
		employee.setEmail("0@email.com");
		employee.setDepartment("department0");
		employee.setTitle("title0");
		return employee;
	}

}
