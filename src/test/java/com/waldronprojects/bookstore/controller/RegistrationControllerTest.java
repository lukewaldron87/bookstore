package com.waldronprojects.bookstore.controller;

import com.waldronprojects.bookstore.config.TestContext;
import com.waldronprojects.bookstore.config.WebAppContext;
import com.waldronprojects.bookstore.dto.CustomerDto;
import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.util.UnitTestUserDtoFactory;
import com.waldronprojects.bookstore.dto.factory.UserDtoFactory;
import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.util.UnitTestUserEntityFactory;
import com.waldronprojects.bookstore.entity.factory.UserEntityFactory;
import com.waldronprojects.bookstore.entity.factory.UserType;
import com.waldronprojects.bookstore.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes =  {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class RegistrationControllerTest {

    private MockMvc mockMvc;
    private UserDtoFactory userDtoFactory;

    @InjectMocks
    private RegistrationController registrationController;

    @Mock
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(registrationController)
                .setViewResolvers(viewResolver)
                .build();

        userDtoFactory = new UnitTestUserDtoFactory();
    }

    @Test
    public void testShowRegistrationForm() throws Exception {
        mockMvc.perform(get("/register/showRegistrationForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/registration-form"))
                .andExpect(forwardedUrl("/WEB-INF/view/customer/registration-form.jsp"))
                .andExpect(model().attribute("customer", instanceOf(UserDto.class)))
                .andExpect(model().attribute("customer", instanceOf(CustomerDto.class)));
    }

    @Test
    public void testProcessRegistrationFormForNewUser() throws Exception {
        CustomerDto customer = (CustomerDto) userDtoFactory
                .createUserDto(UserType.CUSTOMER);
        UserEntityFactory userEntityFactory = new UnitTestUserEntityFactory();
        User user = userEntityFactory.createUser(UserType.CUSTOMER);
        when(userService.findUsername(customer.getUsername()))
                .thenReturn(user);

        mockMvc.perform(post("/register/processRegistrationForm")
                        .flashAttr("customer", customer))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/registration-confirmation"))
                .andExpect(forwardedUrl("/WEB-INF/view/customer/registration-confirmation.jsp"));

        verify(userService, times(1)).findUsername(customer.getUsername());
        verify(userService, times(1)).saveUser(customer);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testProcessRegistrationFormBadBindingResult() throws Exception {
        CustomerDto customer = (CustomerDto) userDtoFactory
                .createUserDto(UserType.CUSTOMER);
        customer.setEmail("bad email");

        mockMvc.perform(post("/register/processRegistrationForm")
                .flashAttr("customer", customer))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/registration-form"))
                .andExpect(forwardedUrl("/WEB-INF/view/customer/registration-form.jsp"));
        verifyNoInteractions(userService);
    }

    @Test
    public void testProcessRegistrationFormForExistingUser() throws Exception {
        CustomerDto customer = (CustomerDto) userDtoFactory
                .createUserDto(UserType.CUSTOMER);

        when(userService.findUsername(customer.getUsername()))
                .thenReturn(null);

        mockMvc.perform(post("/register/processRegistrationForm")
                .flashAttr("customer", customer))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/registration-form"))
                .andExpect(forwardedUrl("/WEB-INF/view/customer/registration-form.jsp"))
                .andExpect(model().attribute("customer", instanceOf(CustomerDto.class)))
                .andExpect(model().attribute("registrationError", "User name already exists."));

        verify(userService, times(1)).findUsername(customer.getUsername());
        verifyNoMoreInteractions(userService);
    }
}