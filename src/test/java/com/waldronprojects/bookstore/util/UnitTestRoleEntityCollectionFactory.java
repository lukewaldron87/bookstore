package com.waldronprojects.bookstore.util;

import com.waldronprojects.bookstore.entity.Role;
import com.waldronprojects.bookstore.entity.factory.RoleEntityCollectionFactory;
import com.waldronprojects.bookstore.entity.factory.RoleType;

import java.util.*;

public class UnitTestRoleEntityCollectionFactory extends RoleEntityCollectionFactory {

    private final Map<RoleType, Collection<Role>> FACTORY_MAP;

    public UnitTestRoleEntityCollectionFactory() {
        final Map<RoleType, Collection<Role>> factoryMap = new EnumMap<>(RoleType.class);
        factoryMap.put(RoleType.ROLE_CUSTOMER, createCustomerRole());
        factoryMap.put(RoleType.ROLE_EMPLOYEE, createEmployeeRole());
        factoryMap.put(RoleType.ROLE_ADMIN, createAdminRole());
        this.FACTORY_MAP = Collections.unmodifiableMap(factoryMap);
    }

    @Override
    public Collection<Role> createRoleCollection(RoleType roleType) {
        return FACTORY_MAP.get(roleType);
    }

    private Collection<Role> createCustomerRole(){
        Collection<Role> roleCollection = new ArrayList<>();
        roleCollection.add(new Role(RoleType.ROLE_CUSTOMER.toString()));
        return roleCollection;
    }

    private Collection<Role> createEmployeeRole(){
        Collection<Role> roleCollection = new ArrayList<>();
        roleCollection.add(new Role(RoleType.ROLE_EMPLOYEE.toString()));
        return roleCollection;
    }

    /**
     * Admins must be Employees so we need to return a list with both roles
     * @return A Collection of roles with an Admin and Employee Role
     */
    private Collection<Role> createAdminRole(){
        Collection<Role> roleCollection = createEmployeeRole();
        roleCollection.add(new Role(RoleType.ROLE_ADMIN.toString()));
        return roleCollection;
    }
}
