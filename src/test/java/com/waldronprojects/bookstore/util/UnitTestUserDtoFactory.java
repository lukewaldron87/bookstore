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
	private final Map<RoleType, UserDto> CREATE_USER_FACTORY_MAP;
	private final Map<RoleType, UserDto> CREATE_PARTIAL_USER_FACTORY_MAP;

	public UnitTestUserDtoFactory(){
		roleEntityCollectionFactory = new UnitTestRoleEntityCollectionFactory();

		final HashMap<RoleType, UserDto> factoryMap = new HashMap<>();
		factoryMap.put(RoleType.ROLE_CUSTOMER, createCustomerUser());
		factoryMap.put(RoleType.ROLE_EMPLOYEE, createRegularEmployeeUser());
		factoryMap.put(RoleType.ROLE_ADMIN, createAdminEmployeeUser());
		CREATE_USER_FACTORY_MAP = Collections.unmodifiableMap(factoryMap);

		final HashMap<RoleType, UserDto> partialUserRoleFactoryMap = new HashMap<>();
		partialUserRoleFactoryMap.put(RoleType.ROLE_CUSTOMER, createPartialCustomerUser());
		partialUserRoleFactoryMap.put(RoleType.ROLE_EMPLOYEE, createPartialRegularEmployeeUser());
		partialUserRoleFactoryMap.put(RoleType.ROLE_ADMIN, createPartialAdminEmployeeUser());
		CREATE_PARTIAL_USER_FACTORY_MAP = Collections.unmodifiableMap(partialUserRoleFactoryMap);
	}

	@Override
	public UserDto createUserDto(RoleType roleType) {
		return CREATE_USER_FACTORY_MAP.get(roleType);
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

	@Override
	public UserDto createPartialUser(RoleType roleType) {
		return CREATE_PARTIAL_USER_FACTORY_MAP.get(roleType);
	}

	private UserDto createPartialCustomerUser() {
		CustomerDto partialCustomer = (CustomerDto) createCustomerUser();
		partialCustomer = (CustomerDto) setSomeUserFieldsToNull(partialCustomer);
		partialCustomer = setSomeCustomerFieldsToNull(partialCustomer);
		return partialCustomer;
	}

	private CustomerDto setSomeCustomerFieldsToNull(CustomerDto partialCustomer) {
		partialCustomer.setAddressLine1(null);
		partialCustomer.setPhoneNumber(0);
		return partialCustomer;
	}
	private UserDto createPartialRegularEmployeeUser() {
		EmployeeDto partialEmployee = (EmployeeDto) createRegularEmployeeUser();
		partialEmployee = (EmployeeDto) setSomeUserFieldsToNull(partialEmployee);
		partialEmployee = setSomeEmployeeFieldsToNull(partialEmployee);
		return partialEmployee;
	}

	private UserDto createPartialAdminEmployeeUser() {
		EmployeeDto partialAdmin = (EmployeeDto) createAdminEmployeeUser();
		partialAdmin = (EmployeeDto) setSomeUserFieldsToNull(partialAdmin);
		partialAdmin = setSomeEmployeeFieldsToNull(partialAdmin);
		return partialAdmin;
	}

	private EmployeeDto setSomeEmployeeFieldsToNull(EmployeeDto partialEmployee) {
		partialEmployee.setDepartment(null);
		return partialEmployee;
	}

	private UserDto setSomeUserFieldsToNull(UserDto partialUser){
		partialUser.setUsername(null);
		partialUser.setPassword(null);
		partialUser.setMatchingPassword(null);
		partialUser.setEmail(null);
		return partialUser;
	}

}
