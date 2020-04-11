package com.waldronprojects.bookstore.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Role findRoleByName(String theRoleName) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Role> query = currentSession
								.createQuery("from Role where name=:roleName",
											 Role.class);
		
		Role role = null;
				
		try {
			role = query.getSingleResult();
		}catch (Exception e) {
			role = null;
		}
		
		return role;
	}

}
