package com.waldronprojects.bookstore.dao;

import com.waldronprojects.bookstore.entity.User;
import com.waldronprojects.bookstore.entity.factory.UserType;

import java.util.List;

public interface UserDao {

    User findByUsername(String username);
    
    void addUser(User user);

    User findUserById(Long id);

	void deleteUser(Long id);

    List<User> getUsersOfType(UserType userType);
}
