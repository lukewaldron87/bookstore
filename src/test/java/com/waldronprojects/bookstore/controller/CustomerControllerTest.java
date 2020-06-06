package com.waldronprojects.bookstore.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.waldronprojects.bookstore.config.MvcConfig;
import com.waldronprojects.bookstore.config.TestContext;
import com.waldronprojects.bookstore.config.WebAppContext;
import com.waldronprojects.bookstore.dao.RoleDao;
import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.Role;
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
		
		Customer customer1 = createCustomer(1);
		Customer customer2 = createCustomer(2);
		
		when(customerService.getCustomers())
			.thenReturn(Arrays.asList(customer1, customer2));
		
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
	
	private Customer createCustomer(int customerNumber) {
		Customer customer = buildCustomer(customerNumber);
		Collection<Role> roleCollection = createCustomerRoleCollection();
		customer.setRoles(roleCollection);
		return customer;
	}
	
	private Customer buildCustomer(int customerNumber) {
		Customer customer = new Customer();
		customer.setUsername("username"+customerNumber);
		customer.setPassword("password"+customerNumber);
		customer.setFirstName("firstName"+customerNumber);
		customer.setLastName("lastName"+customerNumber);
		customer.setEmail(customerNumber+"@email.com");
		customer.setAddressLine1("addressLine1"+customerNumber);
		customer.setAddressLine2("addressLine2"+customerNumber);
		customer.setCity("city"+customerNumber);
		customer.setCountry("country"+customerNumber);
		customer.setPostCode("postCode"+customerNumber);
		customer.setPhoneNumber(1234+customerNumber);
		return customer;
	}
	
	private Collection<Role> createCustomerRoleCollection() {
		Collection<Role> roleCollection = new ArrayList<Role>();
		roleCollection.add(roleDao.findRoleByName("ROLE_CUSTOMER"));
		return roleCollection;
	}

}
