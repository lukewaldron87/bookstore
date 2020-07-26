package com.waldronprojects.bookstore.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.waldronprojects.bookstore.entity.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Product getProduct(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.get(Product.class, id);
	}
	
	
	@Override
	public List<Product> getProducts() {

		// get current hibernate Session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//get all products from the database and sort my name
		Query<Product> customQuery = 
				currentSession.createQuery("from Product order by product_name",
										   Product.class);
	
		return customQuery.getResultList();
	}

	@Override
	public void saveProduct(Product product) {

		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the customer
		currentSession.saveOrUpdate(product);
	}

	@Override
	public void deleteProduct(int id) {

		Session currentSession = sessionFactory.getCurrentSession();
	
		Query query = currentSession.createQuery("delete from Product where id=:productId");
		query.setParameter("productId", id);
		query.executeUpdate();
	}

}
