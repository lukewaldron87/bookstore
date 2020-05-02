package com.waldronprojects.bookstore.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.entity.User;

public interface UserService extends UserDetailsService{

	public User findUsername(String username);
	
    void save(UserDto user);
}
