package com.waldronprojects.bookstore.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.User;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User findByUsername(String username) {

		Session currentSession = sessionFactory.getCurrentSession();
		Query<User>  query = currentSession
								.createQuery("from User where username = :username",
											 User.class);
		query.setParameter("username", username);
		
		User user = null;
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			user = new User();
		}
		
		return user;
	}

	@Override
	public void save(User user) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(user);
	}

	@Override
	public User findUserById(Long id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.get(User.class, id);
	}

	@Override
	public void deleteUser(Long id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("delete from User where id=:userId");
		query.setParameter("userId", id);
		query.executeUpdate();
		
	}

}
