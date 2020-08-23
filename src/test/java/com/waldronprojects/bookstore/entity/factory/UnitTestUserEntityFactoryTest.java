package com.waldronprojects.bookstore.entity.factory;

import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.Employee;
import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.util.UnitTestUserEntityFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnitTestUserEntityFactoryTest {

    private UserEntityFactory userEntityFactory;

    @Before
    public void getUp() {
        userEntityFactory = new UnitTestUserEntityFactory();
    }

    @Test
    public void testCreateUserReturnsCorrectCustomerType(){
        User user = userEntityFactory.createUser(UserType.CUSTOMER);
        assertTrue(user instanceof Customer);
    }

    @Test
    public void testCreateUserReturnsCorrectEmployeeType(){
        User user = userEntityFactory.createUser(UserType.EMPLOYEE);
        assertTrue(user instanceof Employee);
    }

    @Test
    public void testCreateUserReturnsCorrectAdminType(){
        User user = userEntityFactory.createUser(UserType.ADMIN);
        assertTrue(user instanceof Employee);
    }

    @Test
    public void testCreateUserStaticValuesForCustomer(){
        Customer customer = (Customer) userEntityFactory.createUser(UserType.CUSTOMER);
        assertEquals(customer.getUsername(), "username0");
        assertEquals(customer.getPassword(), "password0");
        assertEquals(customer.getFirstName(), "firstName0");
        assertEquals(customer.getLastName(), "lastName0");
        assertEquals(customer.getEmail(), "0@email.com");
        assertEquals(customer.getAddressLine1(), "addressLine10");
        assertEquals(customer.getAddressLine2(), "addressLine20");
        assertEquals(customer.getCity(), "city0");
        assertEquals(customer.getCountry(), "country0");
        assertEquals(customer.getPostCode(), "postCode0");
        assertEquals(customer.getPhoneNumber(), 12340);

        Collection<Role> roleCollection = customer.getRoles();
        boolean contains = isRoleInCollection(roleCollection, "ROLE_CUSTOMER");
        assertTrue(contains);
    }
    
    @Test
    public void testCreateUserStaticValuesForEmployee(){
        Employee employee = (Employee) userEntityFactory.createUser(UserType.EMPLOYEE);
        testEmployeeStaticValues(employee);
        Collection<Role> roleCollection = employee.getRoles();
        boolean containsRole = isRoleInCollection(roleCollection, "ROLE_EMPLOYEE");
        assertTrue(containsRole);
    }

    @Test
    public void testCreateUserStaticValuesForAdmin(){
        Employee adminEmployee = (Employee) userEntityFactory.createUser(UserType.ADMIN);
        testEmployeeStaticValues(adminEmployee);
        Collection<Role> roleCollection = adminEmployee.getRoles();
        boolean containsRole = isRoleInCollection(roleCollection, "ROLE_EMPLOYEE");
        assertTrue(containsRole);
        containsRole = isRoleInCollection(roleCollection, "ROLE_ADMIN");
        assertTrue(containsRole);
    }

    private void testEmployeeStaticValues(Employee employee){
        assertEquals(employee.getUsername(), "username0");
        assertEquals(employee.getPassword(), "password0");
        assertEquals(employee.getFirstName(), "firstName0");
        assertEquals(employee.getLastName(), "lastName0");
        assertEquals(employee.getEmail(), "0@email.com");
        assertEquals(employee.getDepartment(), "department0");
        assertEquals(employee.getTitle(), "title0");
    }

    private boolean isRoleInCollection(Collection<Role> roleCollection, String roleName){
        boolean containsRole = false;
        for(Role role: roleCollection){
            if(role.getName().equals(roleName)){
                containsRole = true;
            }
        }
        return containsRole;
    }
}