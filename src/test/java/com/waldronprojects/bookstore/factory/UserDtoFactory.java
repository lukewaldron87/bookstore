package com.waldronprojects.bookstore.factory;

import com.waldronprojects.bookstore.dto.UserDto;

public abstract class UserDtoFactory {
	
	public abstract UserDto createUserDto(UserType userType);
	

}
