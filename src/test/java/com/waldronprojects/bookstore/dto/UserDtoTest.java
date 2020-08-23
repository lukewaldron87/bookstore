package com.waldronprojects.bookstore.dto;

import com.waldronprojects.bookstore.entity.Role;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class UserDtoTest {

    UserDto userDto;

    @Before
    public void setUp(){
        userDto = new UserDto();
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
        assertEquals(username, getFieldValue("username"));
        assertEquals(password, getFieldValue("password"));
        assertEquals(firstName, getFieldValue("firstName"));
        assertEquals(lastName, getFieldValue("lastName"));
        assertEquals(email, getFieldValue("email"));
    }

    @Test
    public void testConstructorWithRole_setsAllFields() throws NoSuchFieldException, IllegalAccessException {
        String username = "username";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "email";
        Collection<Role> roleCollection = createAdminEmployeeRoleCollection();
        userDto = new UserDto(username,
                              password,
                              firstName,
                              lastName,
                              email,
                              roleCollection);
        assertEquals(username, getFieldValue("username"));
        assertEquals(password, getFieldValue("password"));
        assertEquals(firstName, getFieldValue("firstName"));
        assertEquals(lastName, getFieldValue("lastName"));
        assertEquals(email, getFieldValue("email"));
        assertEquals(roleCollection, getFieldValue("roles"));
    }

    @Test
    public void testGetId_getsValue() throws NoSuchFieldException, IllegalAccessException {
        Long id = 1L;
        setField("id", id);
        Long returnedId = userDto.getId();
        assertEquals(id, returnedId);
    }

    @Test
    public void testSetId_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        Long id = 1L;
        String fieldName = "id";
        userDto.setId(id);
        Object fieldValue = getFieldValue(fieldName);
        assertEquals(id, fieldValue);
    }

    @Test
    public void testGetUsername_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String userName = "userName";
        String fieldName = "username";
        setField(fieldName, userName);
        String returnedUserName = userDto.getUsername();
        assertEquals(userName, returnedUserName);
    }

    @Test
    public void testSetUsername_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String userName = "userName";
        String fieldName = "username";
        userDto.setUsername(userName);
        Object fieldValue = getFieldValue(fieldName);
        assertEquals(userName, fieldValue);
    }

    @Test
    public void testGetPassword_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "password";
        String fieldValue = "password";
        setField(fieldName, fieldValue);
        String returnedValue = userDto.getPassword();
        assertEquals(fieldValue, returnedValue);
    }

    @Test
    public void testSetPassword_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "password";
        String fieldName = "password";
        userDto.setPassword(fieldValue);
        Object returnedFieldValue = getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);

    }

    @Test
    public void testGetMatchingPassword_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "matchingPassword";
        String fieldValue = "password";
        setField(fieldName, fieldValue);
        String returnedValue = userDto.getMatchingPassword();
        assertEquals(fieldValue, returnedValue);
    }

    @Test
    public void testSetMatchingPassword_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "matchingPassword";
        String fieldValue = "password";
        userDto.setMatchingPassword(fieldValue);
        Object returnedFieldValue = getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetFirstName_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "firstName";
        String fieldValue = "firstName";
        setField(fieldName, fieldValue);
        String returnedValue = userDto.getFirstName();
        assertEquals(fieldValue, returnedValue);
    }

    @Test
    public void testSetFirstName_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "firstName";
        String fieldValue = "firstName";
        userDto.setFirstName(fieldValue);
        Object returnedFieldValue = getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetLastName_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "lastName";
        String fieldValue = "lastName";
        setField(fieldName, fieldValue);
        String returnedValue = userDto.getLastName();
        assertEquals(fieldValue, returnedValue);
    }

    @Test
    public void testSetLastName_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "lastName";
        String fieldValue = "lastName";
        userDto.setLastName(fieldValue);
        Object returnedFieldValue = getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetEmail_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "email";
        String fieldValue = "email";
        setField(fieldName, fieldValue);
        String returnedValue = userDto.getEmail();
        assertEquals(fieldValue, returnedValue);
    }

    @Test
    public void testSetEmail_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "email";
        String fieldValue = "email";
        userDto.setEmail(fieldValue);
        Object returnedFieldValue = getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetRoles_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "roles";
        Collection<Role> roleCollection = createAdminEmployeeRoleCollection();
        setField(fieldName, (Object) roleCollection);
        Collection<Role> returnedRoleCollection = userDto.getRoles();
        assertEquals(roleCollection, returnedRoleCollection);
    }

    @Test
    public void testSetRoles_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldName = "roles";
        Collection<Role> roleCollection = createAdminEmployeeRoleCollection();
        userDto.setRoles(roleCollection);
        Object returnedRoleCollection = getFieldValue(fieldName);
        assertEquals(roleCollection, returnedRoleCollection);
    }

    private static Collection<Role> createAdminEmployeeRoleCollection() {
        Collection<Role> roleCollection = new ArrayList<Role>();
        roleCollection.add(new Role("ROLE_EMPLOYEE"));
        roleCollection.add(new Role("ROLE_ADMIN"));
        return roleCollection;
    }

    @Test
    public void testToString_returnsCorrectString() throws NoSuchFieldException, IllegalAccessException {
        String username = "username";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "email";
        Collection<Role> roleCollection = createAdminEmployeeRoleCollection();
        Long idIndex = 1L;
        for(Role role: roleCollection){
            role.setId(idIndex);
            idIndex++;
        }
        setField("username", username);
        setField("password", password);
        setField("firstName", firstName);
        setField("lastName", lastName);
        setField("email", email);
        setField("roles", roleCollection);
        String expected = "User{id=null, username='username', password='*********', firstName='firstName', lastName='lastName', email='email', roles=[Role{id=1, name='ROLE_EMPLOYEE'}, Role{id=2, name='ROLE_ADMIN'}]}";
        String returnedString = userDto.toString();
        assertEquals(expected, returnedString);
    }

    private void setField(String fieldName, Object inputVariable) throws NoSuchFieldException, IllegalAccessException {
        Field field = getField(fieldName);
        field.set(userDto, inputVariable);
    }

    private Object getFieldValue(String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = getField(fieldName);
        return field.get(userDto);
    }

    private Field getField(String fieldName) throws NoSuchFieldException {
        Field field = userDto.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }

}