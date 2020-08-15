package com.waldronprojects.bookstore.dto.factory;

import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.entity.factory.UserType;

public abstract class UserDtoFactory {
	
	public abstract UserDto createUserDto(UserType userType);
	

}
