package com.waldronprojects.bookstore.dto.factory;

import com.waldronprojects.bookstore.dto.CustomerDto;
import com.waldronprojects.bookstore.dto.EmployeeDto;
import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.entity.factory.RoleType;
import com.waldronprojects.bookstore.util.UnitTestUserDtoFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnitTestUserDtoFactoryTest {

    private UserDtoFactory userDtoFactory;

    @Before
    public void setUp() {
        userDtoFactory = new UnitTestUserDtoFactory();
    }

    @Test
    public void testCreateUserDtoReturnsCorrectCustomerType() {
        UserDto user = userDtoFactory.createUserDto(RoleType.ROLE_CUSTOMER);
        assertTrue(user instanceof CustomerDto);
    }

    @Test
    public void testCreateUserDtoReturnsCorrectEmployeeType() {
        UserDto user = userDtoFactory.createUserDto(RoleType.ROLE_EMPLOYEE);
        assertTrue(user instanceof EmployeeDto);
    }

    @Test
    public void testCreateUserDtoReturnsCorrectAdminType() {
        UserDto user = userDtoFactory.createUserDto(RoleType.ROLE_ADMIN);
        assertTrue(user instanceof EmployeeDto);
    }

    @Test
    public void testCreateUserStaticValuesForCustomer(){
        CustomerDto customer = (CustomerDto) userDtoFactory.createUserDto(RoleType.ROLE_CUSTOMER);
        Long id = 0L;
        assertEquals(id,customer.getId() );
        assertEquals("username0", customer.getUsername());
        assertEquals("password0", customer.getPassword());
        assertEquals("firstName0", customer.getFirstName());
        assertEquals("lastName0", customer.getLastName());
        assertEquals("0@email.com", customer.getEmail());
        assertEquals("addressLine10", customer.getAddressLine1());
        assertEquals("addressLine20", customer.getAddressLine2());
        assertEquals("city0", customer.getCity());
        assertEquals("country0", customer.getCountry());
        assertEquals("postCode0", customer.getPostCode());
        assertEquals(12340, customer.getPhoneNumber());

        Collection<Role> roleCollection = customer.getRoles();
        boolean contains = isRoleInCollection(roleCollection, "ROLE_CUSTOMER");
        assertTrue(contains);
    }

    @Test
    public void testCreateUserStaticValuesForEmployee(){
        EmployeeDto employee = (EmployeeDto) userDtoFactory.createUserDto(RoleType.ROLE_EMPLOYEE);
        testEmployeeStaticValues(employee);
        Collection<Role> roleCollection = employee.getRoles();
        boolean containsRole = isRoleInCollection(roleCollection, "ROLE_EMPLOYEE");
        assertTrue(containsRole);
    }

    @Test
    public void testCreateUserStaticValuesForAdmin(){
        EmployeeDto adminEmployee = (EmployeeDto) userDtoFactory.createUserDto(RoleType.ROLE_ADMIN);
        testEmployeeStaticValues(adminEmployee);
        Collection<Role> roleCollection = adminEmployee.getRoles();
        boolean containsRole = isRoleInCollection(roleCollection, "ROLE_EMPLOYEE");
        assertTrue(containsRole);
        containsRole = isRoleInCollection(roleCollection, "ROLE_ADMIN");
        assertTrue(containsRole);
    }

    private void testEmployeeStaticValues(EmployeeDto employee){
        Long id = 0L;
        assertEquals(id,employee.getId() );
        assertEquals("username0", employee.getUsername());
        assertEquals("password0", employee.getPassword());
        assertEquals("firstName0", employee.getFirstName());
        assertEquals("lastName0", employee.getLastName());
        assertEquals("0@email.com", employee.getEmail());
        assertEquals("department0", employee.getDepartment());
        assertEquals("title0", employee.getTitle());
    }

    private boolean isRoleInCollection(Collection<Role> roleCollection, String roleName){
        boolean containsRole = false;
        for(Role role: roleCollection){
            if(role.getName().equals(roleName)){
                containsRole = true;
                break;
            }
        }
        return containsRole;
    }


}