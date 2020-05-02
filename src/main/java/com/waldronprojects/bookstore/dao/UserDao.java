package com.waldronprojects.bookstore.dao;

import com.waldronprojects.bookstore.entity.User;

public interface UserDao {

    public User findByUsername(String username);
    
    public void save(User user);

    public User findUserById(Long id);

	public void deleteUser(Long id);
}
