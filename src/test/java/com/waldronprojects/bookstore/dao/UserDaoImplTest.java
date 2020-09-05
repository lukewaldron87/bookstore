package com.waldronprojects.bookstore.dao;

import com.waldronprojects.bookstore.config.MvcConfig;
import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.Employee;
import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.entity.factory.UserEntityFactory;
import com.waldronprojects.bookstore.entity.factory.UserType;
import com.waldronprojects.bookstore.util.UnitTestUserEntityFactory;
import org.junit.Assert;
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
	@Rollback(true)
	public void testFindByUsername(){
		String customerName = "customer1";
		User user = userDao.findByUsername(customerName);
		assertEquals(customerName, user.getUsername());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testFindUserByIdFail(){
		String customerName = "nonExistentCustomer";
		User user = userDao.findByUsername(customerName);
		assertNull(user.getId());
		assertNull(user.getUsername());
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testFindUserById(){
		Long id = 1L;
		String expectedUsername = "customer1";
		User user = userDao.findUserById(id);
		boolean containsRole = testAssignedRoles(user, "ROLE_CUSTOMER");
		assertEquals(id, user.getId());
		assertEquals(expectedUsername, user.getUsername());
		assertTrue(containsRole);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testAddCustomerUser(){
		User createdCustomerUser =  userEntityFactory.createUser(UserType.CUSTOMER);
		User returnedCustomerUser = addAndFindUser(createdCustomerUser);
		boolean containsRole = testAssignedRoles(returnedCustomerUser, "ROLE_CUSTOMER");
		assertEquals(createdCustomerUser, returnedCustomerUser);
		assertTrue(returnedCustomerUser instanceof Customer);
		assertTrue(containsRole);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testAddEmployeeUser() {
		User createdEmployeeUser =  userEntityFactory.createUser(UserType.EMPLOYEE);
		User returnedEmployeeUser = addAndFindUser(createdEmployeeUser);
		boolean containsRole = testAssignedRoles(returnedEmployeeUser, "ROLE_EMPLOYEE");
		assertEquals(createdEmployeeUser, returnedEmployeeUser);
		assertTrue(returnedEmployeeUser instanceof Employee);
		assertTrue(containsRole);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testAddAdminUser(){
		User createdAdminUser =  userEntityFactory.createUser(UserType.ADMIN);
		User returnedAdminUser = addAndFindUser(createdAdminUser);
		boolean containsRole = testAssignedRoles(returnedAdminUser, "ROLE_ADMIN");
		assertEquals(createdAdminUser, returnedAdminUser);
		assertTrue(returnedAdminUser instanceof Employee);
		assertTrue(containsRole);
	}

	private User addAndFindUser(User user){
		userDao.addUser(user);
		return userDao.findByUsername(user.getUsername());
	}

	private boolean testAssignedRoles(User user, String expectedRoleName){
		Collection<Role> roles = user.getRoles();
		boolean containsRole = false;
		for(Role role: roles){
			if(role.getName().equals(expectedRoleName)){
				containsRole = true;
			}
		}
		return containsRole;
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteUser(){
		Long id = 1L;
		userDao.deleteUser(id);
		User user = userDao.findUserById(id);
		assertNull(user);
	}

	@Test
	@Transactional
	@Rollback
	public void testGetUsersOfType_getCustomer(){
		List<User> customerList = userDao.getUsersOfType(UserType.CUSTOMER);
		for(User customer: customerList) {
			Assert.assertTrue(customer instanceof Customer);
		}
	}

	@Test
	@Transactional
	@Rollback
	public void testGetUsersOfType_getEmployee(){
		List<User> employeeList = userDao.getUsersOfType(UserType.EMPLOYEE);
		for(User employee: employeeList) {
			Assert.assertTrue(employee instanceof Employee);
		}
	}
	
}
