package com.waldronprojects.bookstore.service;

import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.entity.factory.UserType;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{

	User findUsername(String username);
	
    void saveUser(UserDto user);
    
    UserDto getUser(Long id);
    
    void deleteUser(Long id);

    List<User> getUsersOfType(UserType customer);
}
