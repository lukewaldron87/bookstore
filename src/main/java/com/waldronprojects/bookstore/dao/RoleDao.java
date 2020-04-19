package com.waldronprojects.bookstore.dao;

import com.waldronprojects.bookstore.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
