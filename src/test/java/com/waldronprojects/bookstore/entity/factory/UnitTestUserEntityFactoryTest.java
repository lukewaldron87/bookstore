package com.waldronprojects.bookstore.entity.factory;

import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.Employee;
import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.util.UnitTestUserEntityFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class UnitTestUserEntityFactoryTest {

    private UserEntityFactory userEntityFactory;

    @Before
    public void setUp() {
        userEntityFactory = new UnitTestUserEntityFactory();
    }

    @Test
    public void testCreateUserReturnsCorrectCustomerType(){
        User user = userEntityFactory.createUser(RoleType.ROLE_CUSTOMER);
        assertTrue(user instanceof Customer);
    }

    @Test
    public void testCreateUserReturnsCorrectEmployeeType(){
        User user = userEntityFactory.createUser(RoleType.ROLE_EMPLOYEE);
        assertTrue(user instanceof Employee);
    }

    @Test
    public void testCreateUserReturnsCorrectAdminType(){
        User user = userEntityFactory.createUser(RoleType.ROLE_ADMIN);
        assertTrue(user instanceof Employee);
    }

    @Test
    public void testCreateUserStaticValuesForCustomer(){
        Customer customer = (Customer) userEntityFactory.createUser(RoleType.ROLE_CUSTOMER);
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
        assertEquals("12340", customer.getPhoneNumber());

        Collection<Role> roleCollection = customer.getRoles();
        boolean contains = isRoleInCollection(roleCollection, "ROLE_CUSTOMER");
        assertTrue(contains);
    }
    
    @Test
    public void testCreateUserStaticValuesForEmployee(){
        Employee employee = (Employee) userEntityFactory.createUser(RoleType.ROLE_EMPLOYEE);
        testEmployeeStaticValues(employee);
        Collection<Role> roleCollection = employee.getRoles();
        boolean containsRole = isRoleInCollection(roleCollection, "ROLE_EMPLOYEE");
        assertTrue(containsRole);
    }

    @Test
    public void testCreateUserStaticValuesForAdmin(){
        Employee adminEmployee = (Employee) userEntityFactory.createUser(RoleType.ROLE_ADMIN);
        testEmployeeStaticValues(adminEmployee);
        Collection<Role> roleCollection = adminEmployee.getRoles();
        boolean containsRole = isRoleInCollection(roleCollection, "ROLE_EMPLOYEE");
        assertTrue(containsRole);
        containsRole = isRoleInCollection(roleCollection, "ROLE_ADMIN");
        assertTrue(containsRole);
    }

    private void testEmployeeStaticValues(Employee employee){
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

    @Test
    public void testCreatePartialCustomerUser_hasNullFields(){
        Customer customer = (Customer) userEntityFactory.createPartialUser(RoleType.ROLE_CUSTOMER);
        assertNull(customer.getUsername());
        assertNull(customer.getPassword());
        assertNull(customer.getEmail());
        assertNull(customer.getAddressLine1());
        assertNull(customer.getPhoneNumber());
    }

    @Test
    public void testCreatePartialEmployeeUser_hasNullFields(){
        Employee employee = (Employee) userEntityFactory.createPartialUser(RoleType.ROLE_EMPLOYEE);
        assertNull(employee.getUsername());
        assertNull(employee.getPassword());
        assertNull(employee.getEmail());
        assertNull(employee.getDepartment());
    }

    @Test
    public void testCreatePartialAdminUser_hasNullFields(){
        Employee employee = (Employee) userEntityFactory.createPartialUser(RoleType.ROLE_ADMIN);
        assertNull(employee.getUsername());
        assertNull(employee.getPassword());
        assertNull(employee.getEmail());
        assertNull(employee.getEmail());
        assertNull(employee.getDepartment());
    }
}