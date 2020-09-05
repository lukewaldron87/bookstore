package com.waldronprojects.bookstore.dao;

import com.waldronprojects.bookstore.entity.Employee;
import com.waldronprojects.bookstore.entity.factory.UserType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.waldronprojects.bookstore.entity.Customer;
import com.waldronprojects.bookstore.entity.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private SessionFactory sessionFactory;

	private final Map<UserType, Class<?>> USER_TYPE_TO_CLASS_MAP;

	public UserDaoImpl() {
		final HashMap<UserType, Class<?>> userTypeToClassHashMap = new HashMap<>();
		userTypeToClassHashMap.put(UserType.CUSTOMER, Customer.class);
		userTypeToClassHashMap.put(UserType.EMPLOYEE, Employee.class);
		USER_TYPE_TO_CLASS_MAP = Collections.unmodifiableMap(userTypeToClassHashMap);
	}

	@Override
	public User findByUsername(String username) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User>  query = currentSession
								.createQuery("from User where username = :username",
											 User.class);
		query.setParameter("username", username);
		
		User user;
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			user = new User();
		}
		
		return user;
	}

	@Override
	public void addUser(User user) {
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

	@Override
	public List<User> getUsersOfType(UserType userType) {
		Class<User> userTypeClass = (Class<User>) USER_TYPE_TO_CLASS_MAP.get(userType);
		String userTypeString = userTypeClass.getSimpleName();
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> query =
				currentSession.createQuery("from "+userTypeString+" order by lastName",
						userTypeClass);
		return query.getResultList();

	}

}
