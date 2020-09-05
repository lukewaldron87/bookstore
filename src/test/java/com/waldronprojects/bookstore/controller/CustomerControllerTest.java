package com.waldronprojects.bookstore.controller;

import com.waldronprojects.bookstore.config.TestContext;
import com.waldronprojects.bookstore.config.WebAppContext;
import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.dto.factory.UserDtoFactory;
import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.entity.factory.RoleType;
import com.waldronprojects.bookstore.entity.factory.UserEntityFactory;
import com.waldronprojects.bookstore.service.CustomerService;
import com.waldronprojects.bookstore.service.UserService;
import com.waldronprojects.bookstore.util.UnitTestUserDtoFactory;
import com.waldronprojects.bookstore.util.UnitTestUserEntityFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * testing a controller
 * 1 - Send a request to the tested controller method.
 * 2 - Verify that we received the expected response.
 * 
 */

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class CustomerControllerTest {
	
	private MockMvc mockMvc;
	private UserDtoFactory userDtoFactory;

	@InjectMocks
	private CustomerController controller;

	@Mock
	private CustomerService customerService;
	
	@Mock
	private UserService userService;
	  
	@Before
	public void setUp() {
		userDtoFactory = new UnitTestUserDtoFactory();
		MockitoAnnotations.initMocks(this);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");

		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setViewResolvers(viewResolver)
				.build();
	}
	
	@Test
	public void testListCustomers() throws Exception {
		UserEntityFactory userEntityFactory = new UnitTestUserEntityFactory();
		User customer1 = userEntityFactory.createUser(RoleType.ROLE_CUSTOMER);
		User customer2 = userEntityFactory.createUser(RoleType.ROLE_CUSTOMER);
		when(customerService.getCustomers())
			.thenReturn(Arrays.asList((Customer)customer1, (Customer)customer2));

		mockMvc.perform(get("/employee/customer/list"))
				.andExpect(status().isOk())
				.andExpect(view().name("employee/list-customers"))
				.andExpect(forwardedUrl("/WEB-INF/view/employee/list-customers.jsp"))
				.andExpect(model().attribute("customers", hasSize(2)))
				.andExpect(model().attribute("customers", hasItem(customer1)))
				.andExpect(model().attribute("customers", hasItem(customer2)));

		Mockito.verify(customerService, Mockito.times(1)).getCustomers();
		Mockito.verifyNoMoreInteractions(customerService);
	}

	@Test
	public void testShowFormForUpdate() throws Exception{
		UserDto userDto = userDtoFactory.createUserDto(RoleType.ROLE_CUSTOMER);
		when(userService.getUser(userDto.getId())).thenReturn(userDto);

		mockMvc.perform(get("/employee/customer/showFormForUpdate")
						.param("userId",Long.toString(userDto.getId())))
				.andExpect(status().isOk())
				.andExpect(view().name("employee/customer-form"))
				.andExpect(forwardedUrl("/WEB-INF/view/employee/customer-form.jsp"))
				.andExpect(model().attribute("customer", userDto));

		Mockito.verify(userService, Mockito.times(1)).getUser(userDto.getId());
		Mockito.verifyNoMoreInteractions(userService);
	}

	@Test
	public void testSaveCustomer() throws Exception{
		UserDto userDto = userDtoFactory.createUserDto(RoleType.ROLE_CUSTOMER);
		mockMvc.perform(get("/employee/customer/saveCustomer")
						.flashAttr("customer",userDto))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:list"))
				.andExpect(redirectedUrl("list"));
		Mockito.verify(userService, Mockito.times(1)).saveUser(userDto);
		Mockito.verifyNoMoreInteractions(userService);
	}

	@Test
	public void testDelete() throws Exception{
		Long id = 1L;
		mockMvc.perform(get("/employee/customer/delete")
						.param("userId", Long.toString(id)))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:list"))
				.andExpect(redirectedUrl("list?userId="+id))
				.andExpect(model().attribute("userId", id));
		Mockito.verify(userService, Mockito.times(1)).deleteUser(id);
		Mockito.verifyNoMoreInteractions(userService);
	}
}
