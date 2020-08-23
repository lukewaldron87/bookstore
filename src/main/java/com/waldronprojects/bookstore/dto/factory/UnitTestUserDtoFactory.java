package com.waldronprojects.bookstore.dto.factory;

import com.waldronprojects.bookstore.dto.CustomerDto;
import com.waldronprojects.bookstore.dto.EmployeeDto;
import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.entity.factory.UserType;

import java.util.*;

public class UnitTestUserDtoFactory extends UserDtoFactory {

	private final Map<UserType, UserDto> FACTORY_MAP;

	public UnitTestUserDtoFactory(){
		final HashMap<UserType, UserDto> factoryMap = new HashMap<>();
		factoryMap.put(UserType.CUSTOMER, buildCustomer());
		factoryMap.put(UserType.EMPLOYEE, createRegularEmployeeObject());
		factoryMap.put(UserType.ADMIN, createAdminEmployeeObject());
		FACTORY_MAP = Collections.unmodifiableMap(factoryMap);
	}

	@Override
	public UserDto createUserDto(UserType userType) {
		return FACTORY_MAP.get(userType);
	}

	private UserDto buildCustomer() {
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
		Collection<Role> roleCollection = createCustomerRoleCollection();
		customerDto.setRoles(roleCollection);
		return customerDto;
	}

	private Collection<Role> createCustomerRoleCollection() {
		Collection<Role> roleCollection = new ArrayList<Role>();
		roleCollection.add(new Role("ROLE_CUSTOMER"));
		return roleCollection;
	}

	private UserDto createRegularEmployeeObject() {
		EmployeeDto employeeDto = createEmployeeObject();
		Collection<Role> roleCollection = createEmployeeRoleCollection(false);
		employeeDto.setRoles(roleCollection);
		employeeDto.setIsAdmin(false);
		return employeeDto;
	}

	private UserDto createAdminEmployeeObject() {
		EmployeeDto employeeDto = createEmployeeObject();
		Collection<Role> roleCollection = createEmployeeRoleCollection(true);
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
	
	private Collection<Role> createEmployeeRoleCollection(boolean isAdmin) {
		Collection<Role> roleCollection = new ArrayList<Role>();
		roleCollection.add(new Role("ROLE_EMPLOYEE"));
		if(isAdmin) {
			roleCollection.add(new Role("ROLE_ADMIN"));
		}
		return roleCollection;
	}

}
