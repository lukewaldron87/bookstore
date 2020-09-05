package com.waldronprojects.bookstore.dto.factory;

import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.entity.factory.RoleType;

public abstract class UserDtoFactory {
	
	public abstract UserDto createUserDto(RoleType roleType);
	

}
