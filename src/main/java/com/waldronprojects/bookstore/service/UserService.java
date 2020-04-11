package com.waldronprojects.bookstore.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.waldronprojects.bookstore.entity.User;

public interface UserService extends UserDetailsService{

	public User findUserName(String userName);
	
	//void save(CrmUser crmUser);
    void save(User user);
}
