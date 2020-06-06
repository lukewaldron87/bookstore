package com.waldronprojects.bookstore.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.waldronprojects.bookstore.config.MvcConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MvcConfig.class)
//@Transactional
public class CustomerDaoTest {

	@Autowired
	CustomerDao customerDao;
	
	@Test
    @Transactional
    @Rollback(true)
	public void testAddCustomer() {
		
	}
	
}
