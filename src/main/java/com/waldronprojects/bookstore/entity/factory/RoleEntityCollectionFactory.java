package com.waldronprojects.bookstore.entity.factory;

import com.waldronprojects.bookstore.entity.Role;

import java.util.Collection;

public abstract class RoleEntityCollectionFactory {
    public abstract Collection<Role> createRoleCollection(RoleType roleCustomer);
}
