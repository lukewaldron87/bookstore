package com.waldronprojects.bookstore.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.waldronprojects.bookstore.factory.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.waldronprojects.bookstore.config.TestContext;
import com.waldronprojects.bookstore.config.WebAppContext;
import com.waldronprojects.bookstore.dao.RoleDao;
import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.factory.UnitTestUserEntityFactory;
import com.waldronprojects.bookstore.factory.UserEntityFactory;
import com.waldronprojects.bookstore.service.CustomerService;
import com.waldronprojects.bookstore.service.UserService;


/**
 * testing a controller
 * 1 - Send a request to the tested controller method.
 * 2 - Verify that we received the expected response.
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class CustomerControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	//@InjectMocks
	RoleDao roleDao;
	
	 @Autowired
	//@Resource
	private WebApplicationContext webApplicationContext;
	  
	@Before
	public void setUp() {
		Mockito.reset(customerService);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
								.build();
	}
	
	@Test
	//@Transactional
	public void listCustomersTest() throws Exception {
		UserEntityFactory userEntityFactory = new UnitTestUserEntityFactory();
		
		User customer1 = userEntityFactory.createUser(UserType.CUSTOMER);
		User customer2 = userEntityFactory.createUser(UserType.CUSTOMER);
		
		when(customerService.getCustomers())
			.thenReturn(Arrays.asList((Customer)customer1, (Customer)customer2));
		
		//mockMvc.perform(get("/"))
		mockMvc.perform(get("/employee/customer/list"))
				.andExpect(status().isOk())
				//.andExpect(view().name("/employee/customer/list"))
				.andExpect(view().name("/employee/list-customers"))
				//.andExpect(forwardedUrl("/WEB-INF/view/employee/list-customers.jsp"))
				.andExpect(forwardedUrl("/WEB-INF/view//employee/list-customers.jsp"))
				.andExpect(model().attribute("customers", hasSize(2)))
				.andExpect(model().attribute("customers", hasItem(customer1)))
				.andExpect(model().attribute("customers", hasItem(customer2)));
		
		//.andExpect(forwardedUrl("/WEB-INF/jsp/todo/list.jsp"))
		
		//verify(customerService, times(1)).findAll();
		//verifyNoMoreInteractions(customerService);
	}

}
