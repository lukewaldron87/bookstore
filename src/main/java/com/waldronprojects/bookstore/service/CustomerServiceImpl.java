package com.waldronprojects.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waldronprojects.bookstore.dao.CustomerDao;
import com.waldronprojects.bookstore.dao.UserDao;
import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.User;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDao.getCustomers();
	}

}
