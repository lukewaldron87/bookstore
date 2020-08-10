package com.waldronprojects.bookstore.controller;

import com.waldronprojects.bookstore.config.TestContext;
import com.waldronprojects.bookstore.config.WebAppContext;
import com.waldronprojects.bookstore.dto.CustomerDto;
import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.factory.*;
import com.waldronprojects.bookstore.service.UserService;
import com.waldronprojects.bookstore.validation.EmailValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Import;
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
@Import(EmailValidator.class)
public class RegistrationControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private RegistrationController registrationController;

    @Mock
    private UserService userService;

    /*@Mock
    private BindingResult bindingResult;*/

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
        UserDtoFactory userDtoFactory = new UnitTestUserDtoFactory();
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

    /*@Test
    public void testProcessRegistrationFormBadBindingResult() throws Exception {
        UserDtoFactory userDtoFactory = new UnitTestUserDtoFactory();
        CustomerDto customer = (CustomerDto) userDtoFactory
                .createUserDto(UserType.CUSTOMER);

        customer.setEmail("bad email");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        mockMvc.perform(post("/register/processRegistrationForm")
                .flashAttr("customer", customer))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/registration-form"))
                .andExpect(forwardedUrl("/WEB-INF/view/customer/registration-form.jsp"));
        verifyNoInteractions(userService);
    }
    @Test
    public void testProcessRegistrationInvalidEmail() throws Exception {

        mockMvc.perform(post("/register/processRegistrationForm")
                        .accept(MediaType.TEXT_HTML)
                        .param("username", "username")
                .param("username", "username")
                .param("password", "password")
                .param("matchingPassword", "password")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("email", "Bad Email")
                .param("addressLine1", "addressLine1")
                .param("city", "city")
                .param("country", "country")
                .param("postCode", "postCode")
                .param("phoneNumber", "123456798"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/registration-form"))
                .andExpect(forwardedUrl("/WEB-INF/view/customer/registration-form.jsp"));
        verifyNoInteractions(userService);
    }*/

    @Test
    public void testProcessRegistrationFormForExistingUser() throws Exception {
        UserDtoFactory userDtoFactory = new UnitTestUserDtoFactory();
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