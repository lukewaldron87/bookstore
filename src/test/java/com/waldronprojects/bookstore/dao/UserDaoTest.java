package com.waldronprojects.bookstore.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.waldronprojects.bookstore.config.MvcConfig;
import com.waldronprojects.bookstore.entity.Employee;
import com.waldronprojects.bookstore.entity.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = MvcConfig.class)
public class UserDaoTest {

	@Autowired
	UserDao userDao;
	@Autowired
	RoleDao roleDao;
	
	@Test
	@Transactional
	@Rollback(true)
	public void testAddEmployeeUser() {
		Employee employee = createEmployeeObject();
		userDao.addUser(employee);
		Employee employee2 = (Employee) userDao.findByUsername(employee.getUsername());
		assertEquals(employee, employee2);
	}
	
	
	
	private Employee createEmployeeObject() {
		Employee employee = new Employee();
		//employee.setId(employeeDto.getId());
		employee.setUsername("testEmployee1");
		employee.setPassword("testEmployee1");
		employee.setFirstName("testEmployee1");
		employee.setLastName("testEmployee1");
		employee.setEmail("testEmployee1@testEmployee1.com");
		employee.setDepartment("testEmployee1");
		employee.setTitle("testEmployee1");
		Collection<Role> roleCollection = createEmployeeRoleCollection(false);
		employee.setRoles(roleCollection);
		return employee;
	}
	
	private Collection<Role> createEmployeeRoleCollection(boolean isAdmin) {
		Collection<Role> roleCollection = new ArrayList<Role>();
		roleCollection.add(roleDao.findRoleByName("ROLE_EMPLOYEE"));
		if(isAdmin) {
			roleCollection.add(roleDao.findRoleByName("ROLE_ADMIN"));
		}
		return roleCollection;
	}
	
}
