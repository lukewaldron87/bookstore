package com.waldronprojects.bookstore.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.waldronprojects.bookstore.entity.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Role findRoleByName(String roleName) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Role> query = currentSession
								.createQuery("from Role where name=:roleName",
											 Role.class);
		query.setParameter("roleName", roleName);
		
		Role role = null;
		try {
			role = query.getSingleResult();
		}catch (Exception e) {
			role = new Role();
		}
		
		return role;
	}

}
