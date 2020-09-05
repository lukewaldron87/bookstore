package com.waldronprojects.bookstore.util;

import com.waldronprojects.bookstore.dto.CustomerDto;
import com.waldronprojects.bookstore.dto.EmployeeDto;
import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.dto.factory.UserDtoFactory;
import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.entity.factory.RoleEntityCollectionFactory;
import com.waldronprojects.bookstore.entity.factory.RoleType;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UnitTestUserDtoFactory extends UserDtoFactory {

	private	RoleEntityCollectionFactory roleEntityCollectionFactory;
	private final Map<RoleType, UserDto> FACTORY_MAP;

	public UnitTestUserDtoFactory(){
		roleEntityCollectionFactory = new UnitTestRoleEntityCollectionFactory();
		final HashMap<RoleType, UserDto> factoryMap = new HashMap<>();
		factoryMap.put(RoleType.ROLE_CUSTOMER, createCustomerUser());
		factoryMap.put(RoleType.ROLE_EMPLOYEE, createRegularEmployeeUser());
		factoryMap.put(RoleType.ROLE_ADMIN, createAdminEmployeeUser());
		FACTORY_MAP = Collections.unmodifiableMap(factoryMap);
	}

	@Override
	public UserDto createUserDto(RoleType roleType) {
		return FACTORY_MAP.get(roleType);
	}

	private UserDto createCustomerUser() {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(0L);
		customerDto.setUsername("username0");
		customerDto.setPassword("password0");
		customerDto.setMatchingPassword("password0");
		customerDto.setFirstName("firstName0");
		customerDto.setLastName("lastName0");
		customerDto.setEmail("0@email.com");
		customerDto.setAddressLine1("addressLine10");
		customerDto.setAddressLine2("addressLine20");
		customerDto.setCity("city0");
		customerDto.setCountry("country0");
		customerDto.setPostCode("postCode0");
		customerDto.setPhoneNumber(12340);
		Collection<Role> roleCollection = roleEntityCollectionFactory
				.createRole(RoleType.ROLE_CUSTOMER);
		customerDto.setRoles(roleCollection);
		return customerDto;
	}

	private UserDto createRegularEmployeeUser() {
		EmployeeDto employeeDto = createEmployeeObject();
		Collection<Role> roleCollection = roleEntityCollectionFactory
				.createRole(RoleType.ROLE_EMPLOYEE);
		employeeDto.setRoles(roleCollection);
		employeeDto.setIsAdmin(false);
		return employeeDto;
	}

	private UserDto createAdminEmployeeUser() {
		EmployeeDto employeeDto = createEmployeeObject();
		Collection<Role> roleCollection = roleEntityCollectionFactory
				.createRole(RoleType.ROLE_ADMIN);
		employeeDto.setRoles(roleCollection);
		employeeDto.setIsAdmin(true);
		return employeeDto;
	}
	
	private EmployeeDto createEmployeeObject() {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(0L);
		employeeDto.setUsername("username0");
		employeeDto.setPassword("password0");
		employeeDto.setMatchingPassword("password0");
		employeeDto.setFirstName("firstName0");
		employeeDto.setLastName("lastName0");
		employeeDto.setEmail("0@email.com");
		employeeDto.setDepartment("department0");
		employeeDto.setTitle("title0");
		return employeeDto;
	}

}
