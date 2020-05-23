package com.waldronprojects.bookstore.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.waldronprojects.bookstore.entity.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Employee> getEmployees() {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Employee> query = currentSession.
				createQuery("from Employee order by lastName", 
						Employee.class);
		
		return query.getResultList();
	}

}
