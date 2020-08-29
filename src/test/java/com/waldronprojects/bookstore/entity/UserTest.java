package com.waldronprojects.bookstore.entity;

import com.waldronprojects.bookstore.entity.factory.RoleEntityCollectionFactory;
import com.waldronprojects.bookstore.entity.factory.RoleType;
import com.waldronprojects.bookstore.util.FieldModifier;
import com.waldronprojects.bookstore.util.UnitTestRoleEntityCollectionFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class UserTest {

    User user;
    FieldModifier fieldModifier;

    @Before
    public void setUp(){
        user = new User();
        fieldModifier = new FieldModifier(user);
    }

    @Test
    public void testConstructorWithoutRole_setsAllFields() throws NoSuchFieldException, IllegalAccessException {
        String username = "username";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "email";
        user = new User(username,
                        password,
                        firstName,
                        lastName,
                        email);
        fieldModifier = new FieldModifier(user);
        assertEquals(username, fieldModifier.getFieldValue("username"));
        assertEquals(password, fieldModifier.getFieldValue("password"));
        assertEquals(firstName, fieldModifier.getFieldValue("firstName"));
        assertEquals(lastName, fieldModifier.getFieldValue("lastName"));
        assertEquals(email, fieldModifier.getFieldValue("email"));
    }

    @Test
    public void testConstructorWithRole_setsAllFields() throws NoSuchFieldException, IllegalAccessException {
        String username = "username";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "email";
        RoleEntityCollectionFactory roleEntityCollectionFactory =
                new UnitTestRoleEntityCollectionFactory();
        Collection<Role> roleCollection = roleEntityCollectionFactory
                .createRole(RoleType.ROLE_ADMIN);
        user = new User(username,
                password,
                firstName,
                lastName,
                email,
                roleCollection);
        fieldModifier = new FieldModifier(user);
        assertEquals(username, fieldModifier.getFieldValue("username"));
        assertEquals(password, fieldModifier.getFieldValue("password"));
        assertEquals(firstName, fieldModifier.getFieldValue("firstName"));
        assertEquals(lastName, fieldModifier.getFieldValue("lastName"));
        assertEquals(email, fieldModifier.getFieldValue("email"));
        assertEquals(roleCollection, fieldModifier.getFieldValue("roles"));
    }

    @Test
    public void testGetId_getsValue() throws NoSuchFieldException, IllegalAccessException {
        Long id = 1L;
        fieldModifier.setField("id", id);
        Long returnedId = user.getId();
        assertEquals(id, returnedId);
    }

    @Test
    public void testSetId_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        Long id = 1L;
        String fieldName = "id";
        user.setId(id);
        Object fieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(id, fieldValue);
    }

    @Test
    public void testGetUsername_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String userName = "userName";
        String fieldName = "username";
        fieldModifier.setField(fieldName, userName);
        String returnedUserName = user.getUsername();
        assertEquals(userName, returnedUserName);
    }

    @Test
    public void testSetUsername_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String userName = "userName";
        String fieldName = "username";
        user.setUsername(userName);
        Object fieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(userName, fieldValue);
    }

    @Test
    public void testGetPassword_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "password";
        String fieldValue = "password";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedValue = user.getPassword();
        assertEquals(fieldValue, returnedValue);
    }

    @Test
    public void testSetPassword_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "password";
        String fieldName = "password";
        user.setPassword(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);

    }

    @Test
    public void testGetFirstName_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "firstName";
        String fieldValue = "firstName";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedValue = user.getFirstName();
        assertEquals(fieldValue, returnedValue);
    }

    @Test
    public void testSetFirstName_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "firstName";
        String fieldValue = "firstName";
        user.setFirstName(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetLastName_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "lastName";
        String fieldValue = "lastName";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedValue = user.getLastName();
        assertEquals(fieldValue, returnedValue);
    }

    @Test
    public void testSetLastName_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "lastName";
        String fieldValue = "lastName";
        user.setLastName(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetEmail_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "email";
        String fieldValue = "email";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedValue = user.getEmail();
        assertEquals(fieldValue, returnedValue);
    }

    @Test
    public void testSetEmail_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "email";
        String fieldValue = "email";
        user.setEmail(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetRoles_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "roles";
        RoleEntityCollectionFactory roleEntityCollectionFactory =
                new UnitTestRoleEntityCollectionFactory();
        Collection<Role> roleCollection = roleEntityCollectionFactory
                .createRole(RoleType.ROLE_ADMIN);
        fieldModifier.setField(fieldName, (Object) roleCollection);
        Collection<Role> returnedRoleCollection = user.getRoles();
        assertEquals(roleCollection, returnedRoleCollection);
    }

    @Test
    public void testSetRoles_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "roles";
        RoleEntityCollectionFactory roleEntityCollectionFactory =
                new UnitTestRoleEntityCollectionFactory();
        Collection<Role> roleCollection = roleEntityCollectionFactory
                .createRole(RoleType.ROLE_ADMIN);
        user.setRoles(roleCollection);
        Object returnedRoleCollection = fieldModifier.getFieldValue(fieldName);
        assertEquals(roleCollection, returnedRoleCollection);
    }

    @Test
    public void testToString_returnsCorrectString() throws NoSuchFieldException, IllegalAccessException {
        String username = "username";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "email";
        RoleEntityCollectionFactory roleEntityCollectionFactory =
                new UnitTestRoleEntityCollectionFactory();
        Collection<Role> roleCollection = roleEntityCollectionFactory
                .createRole(RoleType.ROLE_ADMIN);
        Long idIndex = 1L;
        for(Role role: roleCollection){
            role.setId(idIndex);
            idIndex++;
        }
        fieldModifier.setField("username", username);
        fieldModifier.setField("password", password);
        fieldModifier.setField("firstName", firstName);
        fieldModifier.setField("lastName", lastName);
        fieldModifier.setField("email", email);
        fieldModifier.setField("roles", roleCollection);
        String expected = "User{id=null, username='username', password='*********', firstName='firstName', lastName='lastName', email='email', roles=[Role{id=1, name='ROLE_EMPLOYEE'}, Role{id=2, name='ROLE_ADMIN'}]}";
        String returnedString = user.toString();
        assertEquals(expected, returnedString);
    }
}