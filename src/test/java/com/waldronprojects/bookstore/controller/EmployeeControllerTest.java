package com.waldronprojects.bookstore.controller;

import com.waldronprojects.bookstore.config.TestContext;
import com.waldronprojects.bookstore.config.WebAppContext;
import com.waldronprojects.bookstore.dto.EmployeeDto;
import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.dto.factory.UnitTestUserDtoFactory;
import com.waldronprojects.bookstore.dto.factory.UserDtoFactory;
import com.waldronprojects.bookstore.entity.Employee;
import com.waldronprojects.bookstore.entity.factory.UnitTestUserEntityFactory;
import com.waldronprojects.bookstore.entity.factory.UserEntityFactory;
import com.waldronprojects.bookstore.entity.factory.UserType;
import com.waldronprojects.bookstore.service.EmployeeService;
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

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class EmployeeControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private EmployeeController controller;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testShowEmployeeMenu() throws Exception{
        mockMvc.perform(get("/employee/showEmployeeMenu"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee/employee-menu"))
                .andExpect(forwardedUrl("/WEB-INF/view/employee/employee-menu.jsp"));
    }

    @Test
    public void testListEmployee() throws Exception{
        List<Employee> employeeList = createEmployeeList();
        when(employeeService.getEmployees()).thenReturn(employeeList);
        mockMvc.perform(get("/employee/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee/list-employees"))
                .andExpect(forwardedUrl("/WEB-INF/view/employee/list-employees.jsp"))
                .andExpect(model().attribute("employees", hasSize(2)))
                .andExpect(model().attribute("employees", hasItem(employeeList.get(0))))
                .andExpect(model().attribute("employees", hasItem(employeeList.get(1))));
        verify(employeeService, times(1)).getEmployees();
        verifyNoMoreInteractions(employeeService);
    }

    private List<Employee> createEmployeeList(){
        UserEntityFactory userEntityFactory = new UnitTestUserEntityFactory();
        Employee employee1 = (Employee) userEntityFactory.createUser(UserType.EMPLOYEE);
        Employee employee2 = (Employee) userEntityFactory.createUser(UserType.EMPLOYEE);
        return Arrays.asList(employee1, employee2);
    }

    @Test
    public void testShowFormForAdd() throws Exception{
        mockMvc.perform(get("/employee/showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee/employee-form"))
                .andExpect(forwardedUrl("/WEB-INF/view/employee/employee-form.jsp"))
                .andExpect(model().attribute("employee", instanceOf(UserDto.class)))
                .andExpect(model().attribute("employee", instanceOf(EmployeeDto.class)));
    }

    @Test
    public void testShowFormForUpdate() throws Exception{
        UserDto userDto = createEmployeeUserDto();
        when(userService.getUser(userDto.getId())).thenReturn(userDto);
        mockMvc.perform(get("/employee/showFormForUpdate")
                        .param("userId",String.valueOf(userDto.getId())))
                .andExpect(status().isOk())
                .andExpect(view().name("employee/employee-update-form"))
                .andExpect(forwardedUrl("/WEB-INF/view/employee/employee-update-form.jsp"))
                .andExpect(model().attribute("employee", userDto));
        verify(userService, times(1)).getUser(userDto.getId());
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testSaveEmployee() throws Exception{
        UserDto userDto = createEmployeeUserDto();
        mockMvc.perform(get("/employee/saveEmployee")
                        .flashAttr("employee", userDto))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:list"))
                .andExpect(redirectedUrl("list"));
        verify(userService, times(1)).saveUser(userDto);
        verifyNoMoreInteractions(userService);
    }

    private UserDto createEmployeeUserDto(){
        UserDtoFactory userDtoFactory = new UnitTestUserDtoFactory();
        return userDtoFactory.createUserDto(UserType.EMPLOYEE);
    }

    @Test
    public void testDeleteEmployee() throws Exception{
        Long userId = 1L;
        mockMvc.perform(get("/employee/deleteEmployee")
                        .param("userId", String.valueOf(userId)))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:list"))
                .andExpect(redirectedUrl("list?userId=" + userId))
                .andExpect(model().attribute("userId", userId));;
        verify(userService, times(1)).deleteUser(userId);
        verifyNoMoreInteractions(userService);
    }
}