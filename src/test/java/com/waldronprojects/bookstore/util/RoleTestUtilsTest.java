package com.waldronprojects.bookstore.util;

import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.entity.factory.RoleType;
import com.waldronprojects.bookstore.entity.factory.UserEntityFactory;
import org.junit.Assert;
import org.junit.Test;

public class RoleTestUtilsTest {

    @Test
    public void testTestAssignedRoles(){
        UserEntityFactory userFactory = new UnitTestUserEntityFactory();
        User user = userFactory.createUser(RoleType.ROLE_ADMIN);
        Assert.assertTrue(RoleTestUtils.testAssignedRoles(user, RoleType.ROLE_ADMIN));
    }
}
