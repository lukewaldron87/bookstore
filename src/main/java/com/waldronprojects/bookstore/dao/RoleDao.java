package com.waldronprojects.bookstore.dao;

import org.springframework.context.annotation.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
