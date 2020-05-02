package com.waldronprojects.bookstore.service;

import java.util.List;

import com.waldronprojects.bookstore.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public Customer getCustomer(Long id);

	public void saveCustomer(Customer customer);

	public void deleteCustomer(Long id);

}
