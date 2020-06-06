package com.waldronprojects.bookstore.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.waldronprojects.bookstore.config.MvcConfig;
import com.waldronprojects.bookstore.dao.CustomerDao;
import com.waldronprojects.bookstore.dao.UserDao;
import com.waldronprojects.bookstore.entity.Customer;

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
	}
	
	private List<Customer> createCustomerList(){
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer());
		customerList.add(new Customer());
		return customerList;
	}
	
}
