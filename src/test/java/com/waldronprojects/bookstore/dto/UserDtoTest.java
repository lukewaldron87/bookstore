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

public class UserDtoTest {

    private UserDto userDto;
    private FieldModifier fieldModifier;
    private RoleEntityCollectionFactory roleEntityCollectionFactory;

    @Before
    public void setUp(){
        userDto = new UserDto();
        fieldModifier = new FieldModifier(userDto);
        roleEntityCollectionFactory = new UnitTestRoleEntityCollectionFactory();
    }

    @Test
    public void testConstructorWithoutRole_setsAllFields() throws NoSuchFieldException, IllegalAccessException {
        String username = "username";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "email";
        userDto = new UserDto(username,
                            password,
                            firstName,
                            lastName,
                            email);
        fieldModifier = new FieldModifier(userDto);
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
        Collection<Role> roleCollection = roleEntityCollectionFactory.createRole(RoleType.ROLE_ADMIN);
        userDto = new UserDto(username,
                              password,
                              firstName,
                              lastName,
                              email,
                              roleCollection);
        fieldModifier = new FieldModifier(userDto);
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
        Long returnedId = userDto.getId();
        assertEquals(id, returnedId);
    }

    @Test
    public void testSetId_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        Long id = 1L;
        String fieldName = "id";
        userDto.setId(id);
        Object fieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(id, fieldValue);
    }

    @Test
    public void testGetUsername_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String userName = "userName";
        String fieldName = "username";
        fieldModifier.setField(fieldName, userName);
        String returnedUserName = userDto.getUsername();
        assertEquals(userName, returnedUserName);
    }

    @Test
    public void testSetUsername_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String userName = "userName";
        String fieldName = "username";
        userDto.setUsername(userName);
        Object fieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(userName, fieldValue);
    }

    @Test
    public void testGetPassword_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "password";
        String fieldValue = "password";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedValue = userDto.getPassword();
        assertEquals(fieldValue, returnedValue);
    }

    @Test
    public void testSetPassword_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "password";
        String fieldName = "password";
        userDto.setPassword(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);

    }

    @Test
    public void testGetMatchingPassword_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "matchingPassword";
        String fieldValue = "password";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedValue = userDto.getMatchingPassword();
        assertEquals(fieldValue, returnedValue);
    }

    @Test
    public void testSetMatchingPassword_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "matchingPassword";
        String fieldValue = "password";
        userDto.setMatchingPassword(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetFirstName_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "firstName";
        String fieldValue = "firstName";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedValue = userDto.getFirstName();
        assertEquals(fieldValue, returnedValue);
    }

    @Test
    public void testSetFirstName_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "firstName";
        String fieldValue = "firstName";
        userDto.setFirstName(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetLastName_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "lastName";
        String fieldValue = "lastName";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedValue = userDto.getLastName();
        assertEquals(fieldValue, returnedValue);
    }

    @Test
    public void testSetLastName_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "lastName";
        String fieldValue = "lastName";
        userDto.setLastName(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetEmail_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "email";
        String fieldValue = "email";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedValue = userDto.getEmail();
        assertEquals(fieldValue, returnedValue);
    }

    @Test
    public void testSetEmail_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "email";
        String fieldValue = "email";
        userDto.setEmail(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetRoles_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "roles";
        Collection<Role> roleCollection = roleEntityCollectionFactory.createRole(RoleType.ROLE_ADMIN);
        fieldModifier.setField(fieldName, (Object) roleCollection);
        Collection<Role> returnedRoleCollection = userDto.getRoles();
        assertEquals(roleCollection, returnedRoleCollection);
    }

    @Test
    public void testSetRoles_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "roles";
        Collection<Role> roleCollection = roleEntityCollectionFactory.createRole(RoleType.ROLE_ADMIN);
        userDto.setRoles(roleCollection);
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
        Collection<Role> roleCollection = roleEntityCollectionFactory.createRole(RoleType.ROLE_ADMIN);
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
        String expected = "UserDto{id=null, username='username', password='*********', firstName='firstName', lastName='lastName', email='email', roles=[Role{id=1, name='ROLE_EMPLOYEE'}, Role{id=2, name='ROLE_ADMIN'}]}";
        String returnedString = userDto.toString();
        assertEquals(expected, returnedString);
    }

}