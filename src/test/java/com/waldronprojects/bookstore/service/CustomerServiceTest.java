package com.waldronprojects.bookstore.service;

import com.waldronprojects.bookstore.dao.CustomerDao;
import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.entity.factory.RoleType;
import com.waldronprojects.bookstore.entity.factory.UserEntityFactory;
import com.waldronprojects.bookstore.util.UnitTestUserEntityFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

//@RunWith(SpringRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(classes = MvcConfig.class)
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
	
	@Mock
	CustomerDao customerDao;
	
	//@Autowired
	@InjectMocks
	//private CustomerService customerService;
	private CustomerServiceImpl customerServiceImpl;
	
	@Test
	public void testGetCustomers() {
		List<Customer> customerList = createCustomerList();
		Mockito.when(customerDao.getCustomers()).thenReturn(customerList);
		List<Customer> returnedList = customerServiceImpl.getCustomers();
		assertEquals(customerList.size(), returnedList.size());
		Mockito.verify(customerDao, Mockito.times(1)).getCustomers();
	}
	
	private List<Customer> createCustomerList(){
		UserEntityFactory userEntityFactory = new UnitTestUserEntityFactory();
		List<Customer> customerList = new ArrayList<>();
		User customer = userEntityFactory.createUser(RoleType.ROLE_CUSTOMER);
		customerList.add((Customer)customer);
		customerList.add((Customer)customer);
		return customerList;
	}
	
}
