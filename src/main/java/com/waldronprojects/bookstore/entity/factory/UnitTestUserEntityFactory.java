package com.waldronprojects.bookstore.entity.factory;

import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.Employee;
import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.entity.User;

import java.util.*;

public class UnitTestUserEntityFactory extends UserEntityFactory {

	private final Map<UserType, User> FACTORY_MAP;

	public UnitTestUserEntityFactory() {
		final HashMap<UserType, User> factoryMap = new HashMap<>();
		factoryMap.put(UserType.CUSTOMER, buildCustomer());
		factoryMap.put(UserType.EMPLOYEE, createRegularEmployeeObject());
		factoryMap.put(UserType.ADMIN, createAdminEmployeeObject());
		FACTORY_MAP = Collections.unmodifiableMap(factoryMap);
	}

	@Override
	public User createUser(UserType userType) {
		return FACTORY_MAP.get(userType);
	}
	
	private Customer buildCustomer() {
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
		Collection<Role> roleCollection = createCustomerRoleCollection();
		customer.setRoles(roleCollection);
		return customer;
	}
	
	private Collection<Role> createCustomerRoleCollection() {
		Collection<Role> roleCollection = new ArrayList<Role>();
		roleCollection.add(new Role("ROLE_CUSTOMER"));
		return roleCollection;
	}
	
	private Employee createRegularEmployeeObject() {
		Employee employee = createEmployeeObject();
		Collection<Role> roleCollection = createEmployeeRoleCollection(false);
		employee.setRoles(roleCollection);
		return employee;
	}
	
	private Employee createAdminEmployeeObject() {
		Employee employee = createEmployeeObject();
		Collection<Role> roleCollection = createEmployeeRoleCollection(true);
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
	
	private Collection<Role> createEmployeeRoleCollection(boolean isAdmin) {
		Collection<Role> roleCollection = new ArrayList<Role>();
		roleCollection.add(new Role("ROLE_EMPLOYEE"));
		if(isAdmin) {
			roleCollection.add(new Role("ROLE_ADMIN"));
		}
		return roleCollection;
	}

}
