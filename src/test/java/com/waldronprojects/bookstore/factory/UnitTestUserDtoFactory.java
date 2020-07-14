package com.waldronprojects.bookstore.factory;

import java.util.ArrayList;
import java.util.Collection;

import com.waldronprojects.bookstore.dto.CustomerDto;
import com.waldronprojects.bookstore.dto.EmployeeDto;
import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.entity.Role;

public class UnitTestUserDtoFactory extends UserDtoFactory {
	
	public static final String CUSTOMER = "customer";
	public static final String EMPLOYEE = "employee";
	public static final String ADMIN = "admin";

	@Override
	public UserDto createUserDto(String type) {

		UserDto user = null;

		switch (type) {
			case CUSTOMER: 
				user = buildCustomer(0);
				break;
			case EMPLOYEE:
				user = createRegularEmployeeObject(0);
				break;
			case ADMIN:
				user =  createAdminEmployeeObject(0);
				break;
			default:
				throw new IllegalArgumentException("Argument " + type + " strange supported");
		}

		return user;	
	}

	private UserDto buildCustomer(long id) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(id);
		customerDto.setUsername("username"+id);
		customerDto.setPassword("password"+id);
		customerDto.setMatchingPassword("password"+id);
		customerDto.setFirstName("firstName"+id);
		customerDto.setLastName("lastName"+id);
		customerDto.setEmail(id+"@email.com");
		customerDto.setAddressLine1("addressLine1"+id);
		customerDto.setAddressLine2("addressLine2"+id);
		customerDto.setCity("city"+id);
		customerDto.setCountry("country"+id);
		customerDto.setPostCode("postCode"+id);
		customerDto.setPhoneNumber(1234+(int)id);
		Collection<Role> roleCollection = createCustomerRoleCollection();
		customerDto.setRoles(roleCollection);
		return customerDto;
	}

	private Collection<Role> createCustomerRoleCollection() {
		Collection<Role> roleCollection = new ArrayList<Role>();
		//roleCollection.add(roleDao.findRoleByName("ROLE_CUSTOMER"));
		roleCollection.add(new Role("ROLE_CUSTOMER"));
		return roleCollection;
	}

	private UserDto createRegularEmployeeObject(int id) {
		EmployeeDto employeeDto = createEmployeeObject(id);
		Collection<Role> roleCollection = createEmployeeRoleCollection(false);
		employeeDto.setRoles(roleCollection);
		employeeDto.setIsAdmin(false);
		return employeeDto;
	}

	private UserDto createAdminEmployeeObject(int id) {
		EmployeeDto employeeDto = createEmployeeObject(id);
		Collection<Role> roleCollection = createEmployeeRoleCollection(true);
		employeeDto.setRoles(roleCollection);
		employeeDto.setIsAdmin(true);
		return employeeDto;
	}
	
	private EmployeeDto createEmployeeObject(long id) {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(id);
		employeeDto.setUsername("username"+id);
		employeeDto.setPassword("password"+id);
		employeeDto.setMatchingPassword("password"+id);
		employeeDto.setUsername("username"+id);
		employeeDto.setPassword("password"+id);
		employeeDto.setFirstName("firstName"+id);
		employeeDto.setLastName("lastName"+id);
		employeeDto.setEmail(id+"@email.com");
		employeeDto.setDepartment("department"+id);
		employeeDto.setTitle("title"+id);
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
