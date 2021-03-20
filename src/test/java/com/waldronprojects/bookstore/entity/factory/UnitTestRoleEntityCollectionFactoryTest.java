package com.waldronprojects.bookstore.entity.factory;

import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.util.UnitTestRoleEntityCollectionFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnitTestRoleEntityCollectionFactoryTest {

    private RoleEntityCollectionFactory roleEntityFactory;

    @Before
    public void setUp(){
        roleEntityFactory = new UnitTestRoleEntityCollectionFactory();
    }

    @Test
    public void testCreateRole_returnsCollectionOfRoles(){
        Collection<Role> roleCollection = roleEntityFactory.createRoleCollection(RoleType.ROLE_CUSTOMER);
        for(Role role: roleCollection){
            assertTrue(role instanceof Role);
        }
    }

    @Test
    public void testCreateRole_createsCustomerRoleCorrectly() {
        Collection<Role> roleCollection = roleEntityFactory.createRoleCollection(RoleType.ROLE_CUSTOMER);
        Iterator<Role> iterator = roleCollection.iterator();
        while (iterator.hasNext()) {
            Role role = iterator.next();
            assertEquals(RoleType.ROLE_CUSTOMER.toString(), role.getName());
        }
    }

    @Test
    public void testCreateRole_createsEmployeeRoleCorrectly() {
        Collection<Role> roleCollection = roleEntityFactory.createRoleCollection(RoleType.ROLE_EMPLOYEE);
        Iterator<Role> iterator = roleCollection.iterator();
        while (iterator.hasNext()) {
            Role role = iterator.next();
            assertEquals(RoleType.ROLE_EMPLOYEE.toString(), role.getName());
        }
    }

    @Test
    public void testCreateRole_createsAdminRoleCorrectly() {
        Collection<Role> roleCollection = roleEntityFactory.createRoleCollection(RoleType.ROLE_ADMIN);
        Role[] roleArray = roleCollection.stream()
                .map(x -> new Role(x.getName()))
                .toArray(size -> new Role[size]);
        assertEquals(RoleType.ROLE_EMPLOYEE.toString(), roleArray[0].getName());
        assertEquals(RoleType.ROLE_ADMIN.toString(), roleArray[1].getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateRole_throwsExceptionForIncorrectArgument(){
        Collection<Role> roleCollection = roleEntityFactory.createRoleCollection(RoleType.valueOf("BAD_ROLE_TYPE"));
    }
}
