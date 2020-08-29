package com.waldronprojects.bookstore.entity;

import com.waldronprojects.bookstore.entity.factory.RoleEntityCollectionFactory;
import com.waldronprojects.bookstore.entity.factory.RoleType;
import com.waldronprojects.bookstore.util.FieldModifier;
import com.waldronprojects.bookstore.util.UnitTestRoleEntityCollectionFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    private Customer customer;
    private FieldModifier fieldModifier;

    @Before
    public void setUp(){
        customer = new Customer();
        fieldModifier = new FieldModifier(customer);
    }

    @Test
    public void testConstructorWithoutRole_setsAllFields() throws NoSuchFieldException,
                                                                    IllegalAccessException {
        String username = "username";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "email";
        String addressLine1 = "addressLine1";
        String addressLine2 = "addressLine2";
        String city = "city";
        String country = "country";
        String postCode = "postCode";
        int phoneNumber = 1234;

        customer = new Customer(username,
                                password,
                                firstName,
                                lastName,
                                email,
                                addressLine1,
                                addressLine2,
                                city,
                                country,
                                postCode,
                                phoneNumber);
        fieldModifier = new FieldModifier(customer);

        assertEquals(addressLine1, fieldModifier.getFieldValue("addressLine1"));
        assertEquals(addressLine2, fieldModifier.getFieldValue("addressLine2"));
        assertEquals(city, fieldModifier.getFieldValue("city"));
        assertEquals(country, fieldModifier.getFieldValue("country"));
        assertEquals(postCode, fieldModifier.getFieldValue("postCode"));
        assertEquals(phoneNumber, fieldModifier.getFieldValue("phoneNumber"));
    }

    @Test
    public void testConstructorWithRole_setsAllFields() throws NoSuchFieldException,
                                                                IllegalAccessException {
        String username = "username";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "email";
        String addressLine1 = "addressLine1";
        String addressLine2 = "addressLine2";
        String city = "city";
        String country = "country";
        String postCode = "postCode";
        int phoneNumber = 1234;
        RoleEntityCollectionFactory roleEntityCollectionFactory =
                new UnitTestRoleEntityCollectionFactory();
        Collection<Role> roleCollection = roleEntityCollectionFactory
                .createRole(RoleType.ROLE_ADMIN);

        customer = new Customer(username,
                password,
                firstName,
                lastName,
                email,
                roleCollection,
                addressLine1,
                addressLine2,
                city,
                country,
                postCode,
                phoneNumber);
        fieldModifier = new FieldModifier(customer);

        assertEquals(addressLine1, fieldModifier.getFieldValue("addressLine1"));
        assertEquals(addressLine2, fieldModifier.getFieldValue("addressLine2"));
        assertEquals(city, fieldModifier.getFieldValue("city"));
        assertEquals(country, fieldModifier.getFieldValue("country"));
        assertEquals(postCode, fieldModifier.getFieldValue("postCode"));
        assertEquals(phoneNumber, fieldModifier.getFieldValue("phoneNumber"));
    }

    @Test
    public void testGetAddressLine1_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "addressLine1";
        String fieldName = "addressLine1";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedFieldValue = customer.getAddressLine1();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetAddressLine1_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "addressLine1";
        String fieldName = "addressLine1";
        customer.setAddressLine1(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetAddressLine2_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "addressLine2";
        String fieldName = "addressLine2";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedFieldValue = customer.getAddressLine2();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetAddressLine2_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "addressLine2";
        String fieldName = "addressLine2";
        customer.setAddressLine2(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetCity_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "city";
        String fieldName = "city";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedFieldValue = customer.getCity();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetCity_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "city";
        String fieldName = "city";
        customer.setCity(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetCountry_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "country";
        String fieldName = "country";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedFieldValue = customer.getCountry();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetCountry_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "country";
        String fieldName = "country";
        customer.setCountry(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetPostCode_getsValue() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "postCode";
        String fieldName = "postCode";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedFieldValue = customer.getPostCode();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetPostCode_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "postCode";
        String fieldName = "postCode";
        customer.setPostCode(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetPhoneNumber_getsValue() throws NoSuchFieldException, IllegalAccessException {
        int fieldValue = 1234;
        String fieldName = "phoneNumber";
        fieldModifier.setField(fieldName, fieldValue);
        int returnedFieldValue = customer.getPhoneNumber();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetPhoneNumber_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        int fieldValue = 1234;
        String fieldName = "phoneNumber";
        customer.setPhoneNumber(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testToString_returnsCorrectString() {String username = "username";
        String password = "password";
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "email";
        String addressLine1 = "addressLine1";
        String addressLine2 = "addressLine2";
        String city = "city";
        String country = "country";
        String postCode = "postCode";
        int phoneNumber = 1234;
        RoleEntityCollectionFactory roleEntityCollectionFactory =
                new UnitTestRoleEntityCollectionFactory();
        Collection<Role> roleCollection = roleEntityCollectionFactory
                .createRole(RoleType.ROLE_ADMIN);
        customer = new Customer(username,
                password,
                firstName,
                lastName,
                email,
                roleCollection,
                addressLine1,
                addressLine2,
                city,
                country,
                postCode,
                phoneNumber);
        fieldModifier = new FieldModifier(customer);
        String expectedString = "Customer [User{id=null, username='username', password='*********', firstName='firstName', lastName='lastName', email='email', roles=[Role{id=null, name='ROLE_EMPLOYEE'}, Role{id=null, name='ROLE_ADMIN'}]}  addressLine1=addressLine1, addressLine2=addressLine2, city=city, country=country, postCode=postCode, phoneNumber=1234]";
        String returnedString = customer.toString();
        assertEquals(expectedString, returnedString);
    }
}