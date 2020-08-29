package com.waldronprojects.bookstore.dto;

import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.entity.factory.RoleEntityCollectionFactory;
import com.waldronprojects.bookstore.entity.factory.RoleType;
import com.waldronprojects.bookstore.util.FieldModifier;
import com.waldronprojects.bookstore.util.UnitTestRoleEntityCollectionFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class EmployeeDtoTest {

    private EmployeeDto employeeDto;
    private FieldModifier fieldModifier;

    @Before
    public void setUp() {
        employeeDto = new EmployeeDto();
        fieldModifier = new FieldModifier(employeeDto);
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
        Boolean isAdmin = true;
        employeeDto = new EmployeeDto(username, password, firstName,
                                      lastName, email, department,
                                      title, isAdmin);
        fieldModifier = new FieldModifier(employeeDto);
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
        Boolean isAdmin = true;
        RoleEntityCollectionFactory roleEntityCollectionFactory =
                new UnitTestRoleEntityCollectionFactory();
        Collection<Role> roleCollection = roleEntityCollectionFactory
                .createRole(RoleType.ROLE_ADMIN);
        employeeDto = new EmployeeDto(username, password, firstName,
                                      lastName, email, roleCollection,
                                      department, title, isAdmin);
        fieldModifier = new FieldModifier(employeeDto);
        assertEquals(department, fieldModifier.getFieldValue("department"));
        assertEquals(title, fieldModifier.getFieldValue("title"));
    }

    @Test
    public void testGetDepartment() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "department";
        String fieldName = "department";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedFieldValue = employeeDto.getDepartment();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetDepartment() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "department";
        String fieldName = "department";
        employeeDto.setDepartment(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetTitle() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "title";
        String fieldName = "title";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedFieldValue = employeeDto.getTitle();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetTitle() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "title";
        String fieldName = "title";
        employeeDto.setTitle(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetIsAdmin() throws NoSuchFieldException, IllegalAccessException {
        boolean fieldValue = true;
        String fieldName = "isAdmin";
        fieldModifier.setField(fieldName, fieldValue);
        boolean returnedFieldValue = employeeDto.getIsAdmin();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetIsAdmin() throws NoSuchFieldException, IllegalAccessException {
        boolean fieldValue = true;
        String fieldName = "isAdmin";
        employeeDto.setIsAdmin(fieldValue);
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
        Boolean isAdmin = true;
        RoleEntityCollectionFactory roleEntityCollectionFactory =
                new UnitTestRoleEntityCollectionFactory();
        Collection<Role> roleCollection = roleEntityCollectionFactory
                .createRole(RoleType.ROLE_ADMIN);
        employeeDto = new EmployeeDto(username, password, firstName,
                                      lastName, email, roleCollection,
                                      department, title, isAdmin);
        fieldModifier = new FieldModifier(employeeDto);
        String expectedString = "EmployeeDto [UserDto{id=null, username='username', password='*********', firstName='firstName', lastName='lastName', email='email', roles=[Role{id=null, name='ROLE_EMPLOYEE'}, Role{id=null, name='ROLE_ADMIN'}]} department=department, title=title isAdmin=true]";
        String returnedString = employeeDto.toString();
        assertEquals(expectedString, returnedString);
    }
}