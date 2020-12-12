package com.waldronprojects.bookstore.util;

import com.waldronprojects.bookstore.entity.*;
import com.waldronprojects.bookstore.entity.factory.ProductType;
import com.waldronprojects.bookstore.entity.factory.RoleEntityCollectionFactory;
import com.waldronprojects.bookstore.entity.factory.RoleType;
import com.waldronprojects.bookstore.entity.factory.UserEntityFactory;

import java.util.*;

public class UnitTestUserEntityFactory extends UserEntityFactory {

	private RoleEntityCollectionFactory roleEntityCollectionFactory;
	private final Map<RoleType, User> CREATE_USER_FACTORY_MAP;
	private final Map<RoleType, User> CREATE_PARTIAL_USER_FACTORY_MAP;

	public UnitTestUserEntityFactory() {
		roleEntityCollectionFactory = new UnitTestRoleEntityCollectionFactory();

		final Map<RoleType, User> userRoleFactoryMap = new EnumMap<>(RoleType.class);
		userRoleFactoryMap.put(RoleType.ROLE_CUSTOMER, createCustomerUser());
		userRoleFactoryMap.put(RoleType.ROLE_EMPLOYEE, createRegularEmployeeUser());
		userRoleFactoryMap.put(RoleType.ROLE_ADMIN, createAdminEmployeeUser());
		CREATE_USER_FACTORY_MAP = Collections.unmodifiableMap(userRoleFactoryMap);

		final Map<RoleType, User> partialUserRoleFactoryMap = new EnumMap<>(RoleType.class);
		partialUserRoleFactoryMap.put(RoleType.ROLE_CUSTOMER, createPartialCustomerUser());
		partialUserRoleFactoryMap.put(RoleType.ROLE_EMPLOYEE, createPartialRegularEmployeeUser());
		partialUserRoleFactoryMap.put(RoleType.ROLE_ADMIN, createPartialAdminEmployeeUser());
		CREATE_PARTIAL_USER_FACTORY_MAP = Collections.unmodifiableMap(partialUserRoleFactoryMap);
	}

	@Override
	public User createUser(RoleType roleType) {
		return CREATE_USER_FACTORY_MAP.get(roleType);
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
		customer.setPhoneNumber("12340");
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

	@Override
	public User createPartialUser(RoleType roleType) {
		return CREATE_PARTIAL_USER_FACTORY_MAP.get(roleType);
	}

	private User createPartialCustomerUser() {
		Customer partialCustomer = createCustomerUser();
		partialCustomer = (Customer) setSomeUserFieldsToNull(partialCustomer);
		partialCustomer = setSomeCustomerFieldsToNull(partialCustomer);
		return partialCustomer;
	}

	private Customer setSomeCustomerFieldsToNull(Customer partialCustomer) {
		partialCustomer.setAddressLine1(null);
		partialCustomer.setPhoneNumber(null);
		return partialCustomer;
	}
	private User createPartialRegularEmployeeUser() {
		Employee partialEmployee = createRegularEmployeeUser();
		partialEmployee = (Employee) setSomeUserFieldsToNull(partialEmployee);
		partialEmployee = setSomeEmployeeFieldsToNull(partialEmployee);
		return partialEmployee;
	}

	private User createPartialAdminEmployeeUser() {
		Employee partialAdmin = createAdminEmployeeUser();
		partialAdmin = (Employee) setSomeUserFieldsToNull(partialAdmin);
		partialAdmin = setSomeEmployeeFieldsToNull(partialAdmin);
		return partialAdmin;
	}

	private Employee setSomeEmployeeFieldsToNull(Employee partialEmployee) {
		partialEmployee.setDepartment(null);
		return partialEmployee;
	}

	private User setSomeUserFieldsToNull(User partialUser){
		partialUser.setUsername(null);
		partialUser.setPassword(null);
		partialUser.setEmail(null);
		return partialUser;
	}

}
