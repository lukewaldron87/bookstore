package com.waldronprojects.bookstore.dao;

import com.waldronprojects.bookstore.config.MvcConfig;
import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.Employee;
import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.entity.factory.RoleType;
import com.waldronprojects.bookstore.entity.factory.UserEntityFactory;
import com.waldronprojects.bookstore.entity.factory.UserType;
import com.waldronprojects.bookstore.util.UnitTestUserEntityFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests uses the bookstore_test database
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = MvcConfig.class)
public class UserDaoImplTest {

	@Autowired
	UserDao userDao;

	UserEntityFactory userEntityFactory;

	@Before
	public void setUpTests(){
		userEntityFactory = new UnitTestUserEntityFactory();
	}

	@Test
	@Transactional
	@Rollback
	public void testFindByUsername(){
		String customerName = "customer1";
		User user = userDao.findByUsername(customerName);
		assertEquals(customerName, user.getUsername());
	}

	@Test
	@Transactional
	@Rollback
	public void testFindUserByIdFail(){
		String customerName = "nonExistentCustomer";
		User user = userDao.findByUsername(customerName);
		assertNull(user.getId());
		assertNull(user.getUsername());
	}

	@Test
	@Transactional
	@Rollback
	public void testFindUserById(){
		Long id = 1L;
		String expectedUsername = "customer1";
		User user = userDao.findUserById(id);
		boolean containsRole = testAssignedRoles(user, RoleType.ROLE_CUSTOMER);
		assertEquals(id, user.getId());
		assertEquals(expectedUsername, user.getUsername());
		assertTrue(containsRole);
	}

	@Test
	@Transactional
	@Rollback
	public void testAddCustomerUser(){
		User createdCustomerUser =  userEntityFactory.createUser(RoleType.ROLE_CUSTOMER);
		User returnedCustomerUser = addAndFindUser(createdCustomerUser);
		boolean containsRole = testAssignedRoles(returnedCustomerUser, RoleType.ROLE_CUSTOMER);
		assertEquals(createdCustomerUser, returnedCustomerUser);
		assertTrue(returnedCustomerUser instanceof Customer);
		assertTrue(containsRole);
	}

	@Test
	@Transactional
	@Rollback
	public void testAddEmployeeUser() {
		User createdEmployeeUser =  userEntityFactory.createUser(RoleType.ROLE_EMPLOYEE);
		User returnedEmployeeUser = addAndFindUser(createdEmployeeUser);
		boolean containsRole = testAssignedRoles(returnedEmployeeUser, RoleType.ROLE_EMPLOYEE);
		assertEquals(createdEmployeeUser, returnedEmployeeUser);
		assertTrue(returnedEmployeeUser instanceof Employee);
		assertTrue(containsRole);
	}

	@Test
	@Transactional
	@Rollback
	public void testAddAdminUser(){
		User createdAdminUser =  userEntityFactory.createUser(RoleType.ROLE_ADMIN);
		User returnedAdminUser = addAndFindUser(createdAdminUser);
		boolean containsRole = testAssignedRoles(returnedAdminUser, RoleType.ROLE_ADMIN);
		assertEquals(createdAdminUser, returnedAdminUser);
		assertTrue(returnedAdminUser instanceof Employee);
		assertTrue(containsRole);
	}

	private User addAndFindUser(User user){
		userDao.addUser(user);
		return userDao.findByUsername(user.getUsername());
	}

	@Test
	@Transactional
	@Rollback
	public void testDeleteUser(){
		Long id = 1L;
		userDao.deleteUser(id);
		User user = userDao.findUserById(id);
		assertNull(user);
	}

	@Test
	@Transactional
	@Rollback
	public void testGetUsersOfType_getCustomers(){
		List<User> customerList = userDao.getUsersOfType(UserType.CUSTOMER);
		for(User customer: customerList) {
			assertTrue(customer instanceof Customer);
		}
	}

	@Test
	@Transactional
	@Rollback
	public void testGetUsersOfType_getEmployees(){
		List<User> employeeList = userDao.getUsersOfType(UserType.EMPLOYEE);
		for(User employee: employeeList) {
			assertTrue(employee instanceof Employee);
		}
	}

	@Test
	@Transactional
	@Rollback
	public void testGetUsersOfType_getsCorrectUserDetails(){
		String expectedUsername = "customer1";
		String expectedAddressLine1 = "customer1 address line 1";
		List<User> customerList = userDao.getUsersOfType(UserType.CUSTOMER);
		Customer customer = (Customer) customerList.get(0);
		assertEquals(expectedUsername, customer.getUsername());
		assertEquals(expectedAddressLine1, customer.getAddressLine1());
		testAssignedRoles(customer, RoleType.ROLE_CUSTOMER);
	}

	private boolean testAssignedRoles(User user, RoleType roleType){
		Collection<Role> roles = user.getRoles();
		boolean containsRole = false;
		for(Role role: roles){
			if(role.getName().equals(roleType.toString())){
				containsRole = true;
				break;
			}
		}
		return containsRole;
	}

	@Test
	@Transactional
	@Rollback
	public void testGetUsersOfType_usersOrderedByLastName(){
		List<User> userList = userDao.getUsersOfType(UserType.EMPLOYEE);
		boolean isOrderedByLastName = true;
		String previousLastName = "";
		for(User user: userList){
			if(!previousLastName.isEmpty()){
				if(user.getLastName().compareTo(previousLastName) < 0){
					isOrderedByLastName = false;
					break;
				}

			}
			previousLastName = user.getLastName();
		}
		assertTrue(isOrderedByLastName);
	}
}
