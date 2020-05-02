package com.waldronprojects.bookstore.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.waldronprojects.bookstore.entity.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Customer> customQuery =
				currentSession.createQuery("from Customer order by lastName",
						   Customer.class);
		
		return customQuery.getResultList();
	}

}
