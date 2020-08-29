package com.waldronprojects.bookstore.entity;

import com.waldronprojects.bookstore.entity.factory.RoleEntityCollectionFactory;
import com.waldronprojects.bookstore.entity.factory.RoleType;
import com.waldronprojects.bookstore.util.FieldModifier;
import com.waldronprojects.bookstore.util.UnitTestRoleEntityCollectionFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class EmployeeTest {

    private Employee employee;
    private FieldModifier fieldModifier;

    @Before
    public void setUp() {
        employee = new Employee();
        fieldModifier = new FieldModifier(employee);
    }

    @Test
    public void testConstructorWithoutRole_setsAllFields() throws NoSuchFieldException, IllegalAccessException {
        String username = "username";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "email";
        String department = "department";
        String title = "title";
        employee = new Employee(username, password, firstName,
                                lastName, email, department, title);
        fieldModifier = new FieldModifier(employee);
        assertEquals(department, fieldModifier.getFieldValue("department"));
        assertEquals(title, fieldModifier.getFieldValue("title"));

    }

    @Test
    public void testConstructorWithRole_setsAllFields() throws NoSuchFieldException, IllegalAccessException {
        String username = "username";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "email";
        String department = "department";
        String title = "title";
        RoleEntityCollectionFactory roleEntityCollectionFactory =
                new UnitTestRoleEntityCollectionFactory();
        Collection<Role> roleCollection = roleEntityCollectionFactory
                .createRole(RoleType.ROLE_ADMIN);
        employee = new Employee(username, password, firstName,
                                lastName, email, roleCollection,
                                department, title);
        fieldModifier = new FieldModifier(employee);
        assertEquals(department, fieldModifier.getFieldValue("department"));
        assertEquals(title, fieldModifier.getFieldValue("title"));
    }

    @Test
    public void testGetDepartment() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "department";
        String fieldName = "department";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedFieldValue = employee.getDepartment();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetDepartment() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "department";
        String fieldName = "department";
        employee.setDepartment(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetTitle() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "title";
        String fieldName = "title";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedFieldValue = employee.getTitle();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetTitle() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "title";
        String fieldName = "title";
        employee.setTitle(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testToString() {
        String username = "username";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "email";
        String department = "department";
        String title = "title";
        RoleEntityCollectionFactory roleEntityCollectionFactory =
                new UnitTestRoleEntityCollectionFactory();
        Collection<Role> roleCollection = roleEntityCollectionFactory
                .createRole(RoleType.ROLE_ADMIN);
        employee = new Employee(username, password, firstName,
                                lastName, email, roleCollection,
                                department, title);
        fieldModifier = new FieldModifier(employee);
        String expectedString = "Employee [User{id=null, username='username', password='*********', firstName='firstName', lastName='lastName', email='email', roles=[Role{id=null, name='ROLE_EMPLOYEE'}, Role{id=null, name='ROLE_ADMIN'}]}department=department, title=title]";
        String returnedString = employee.toString();
        assertEquals(expectedString, returnedString);
    }
}