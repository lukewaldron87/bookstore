package com.waldronprojects.bookstore.entity.factory;

import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.Employee;
import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.entity.User;

import java.util.ArrayList;
import java.util.Collection;

public class UnitTestUserEntityFactory extends UserEntityFactory {

	@Override
	public User createUser(UserType userType) {

		User user = null;
		
		switch (userType) {
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
					throw new IllegalArgumentException("Argument " + userType + " strange supported");
		}

		return user;
	}
	
	private Customer buildCustomer(long id) {
		Customer customer = new Customer();
		//customer.setId(id);
		customer.setUsername("username"+id);
		customer.setPassword("password"+id);
		customer.setFirstName("firstName"+id);
		customer.setLastName("lastName"+id);
		customer.setEmail(id+"@email.com");
		customer.setAddressLine1("addressLine1"+id);
		customer.setAddressLine2("addressLine2"+id);
		customer.setCity("city"+id);
		customer.setCountry("country"+id);
		customer.setPostCode("postCode"+id);
		customer.setPhoneNumber(1234+(int)id);
		Collection<Role> roleCollection = createCustomerRoleCollection();
		customer.setRoles(roleCollection);
		return customer;
	}
	
	private Collection<Role> createCustomerRoleCollection() {
		Collection<Role> roleCollection = new ArrayList<Role>();
		//roleCollection.add(roleDao.findRoleByName("ROLE_CUSTOMER"));
		roleCollection.add(new Role("ROLE_CUSTOMER"));
		return roleCollection;
	}
	
	private Employee createRegularEmployeeObject(long id) {
		Employee employee = createEmployeeObject(id);
		Collection<Role> roleCollection = createEmployeeRoleCollection(false);
		employee.setRoles(roleCollection);
		return employee;
	}
	
	private Employee createAdminEmployeeObject(long id) {
		Employee employee = createEmployeeObject(id);
		Collection<Role> roleCollection = createEmployeeRoleCollection(true);
		employee.setRoles(roleCollection);
		return employee;
	}
	
	private Employee createEmployeeObject(long id) {
		Employee employee = new Employee();
		//employee.setId(id);
		employee.setUsername("username"+id);
		employee.setPassword("password"+id);
		employee.setFirstName("firstName"+id);
		employee.setLastName("lastName"+id);
		employee.setEmail(id+"@email.com");
		employee.setDepartment("department"+id);
		employee.setTitle("title"+id);
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
