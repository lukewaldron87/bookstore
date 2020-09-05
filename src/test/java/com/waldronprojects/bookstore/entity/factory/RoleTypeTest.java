package com.waldronprojects.bookstore.entity.factory;

import org.junit.Test;

import static org.junit.Assert.*;

public class RoleTypeTest {

    @Test
    public void testToString_ROLE_CUSTOMER() {
        String expectedString = "ROLE_CUSTOMER";
        assertEquals(expectedString, RoleType.ROLE_CUSTOMER.toString());
    }

    @Test
    public void testToString_ROLE_EMPLOYEE() {
        String expectedString = "ROLE_EMPLOYEE";
        assertEquals(expectedString, RoleType.ROLE_EMPLOYEE.toString());
    }

    @Test
    public void testToString_ROLE_ADMIN() {
        String expectedString = "ROLE_ADMIN";
        assertEquals(expectedString, RoleType.ROLE_ADMIN.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToString_throwsErrorWhenStringSet() {
        RoleType.valueOf("test").toString();
    }
}