package com.waldronprojects.bookstore.entity.factory;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTypeTest {

    @Test
    public void testToString_CUSTOMER() {
        String expectedString = "CUSTOMER";
        assertEquals(expectedString, UserType.CUSTOMER.toString());
    }

    @Test
    public void testToString_EMPLOYEE() {
        String expectedString = "EMPLOYEE";
        assertEquals(expectedString, UserType.EMPLOYEE.toString());
    }
}
