package com.waldronprojects.bookstore.factory;

import com.waldronprojects.bookstore.entity.User;

public abstract class UserEntityFactory {
	
	public abstract User createUser(UserType userType);

}
