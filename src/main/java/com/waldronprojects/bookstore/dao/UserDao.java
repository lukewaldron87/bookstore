package com.waldronprojects.bookstore.dao;

import com.waldronprojects.bookstore.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    
    void save(User user);
}
