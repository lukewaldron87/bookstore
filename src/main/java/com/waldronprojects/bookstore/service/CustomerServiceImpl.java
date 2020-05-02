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

	@Override
	@Transactional
	public Customer getCustomer(Long id) {
		User user = userDao.findUserById(id);
		if(user instanceof Customer) {
			return (Customer)user;
		}else {
			return new Customer();
		}
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		userDao.save(customer);
	}

	@Override
	@Transactional
	public void deleteCustomer(Long id) {
		userDao.deleteUser(id);
	}

}
