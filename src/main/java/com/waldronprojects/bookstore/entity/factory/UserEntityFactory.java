package com.waldronprojects.bookstore.entity.factory;

import com.waldronprojects.bookstore.entity.User;

public abstract class UserEntityFactory {
	public abstract User createUser(RoleType roleType);
	public abstract User createPartialUser(RoleType roleType);
}
