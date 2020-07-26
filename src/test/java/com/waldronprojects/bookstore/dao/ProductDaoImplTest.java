package com.waldronprojects.bookstore.dao;

import com.waldronprojects.bookstore.config.MvcConfig;
import com.waldronprojects.bookstore.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = MvcConfig.class)
public class ProductDaoImplTest {

    @Autowired
    ProductDao productDao;

    @Test
    @Transactional
    @Rollback(true)
    public void getProduct() {
        int id = 3;
        String productName = "book1";
        Product product = productDao.getProduct(id);
        assertEquals(id, product.getId());
        assertEquals(productName, product.getProductName());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void getProducts() {
        int numberOfProducts = 4;
        List<Product> productList = productDao.getProducts();
        assertEquals(numberOfProducts, productList.size());
    }

    @Test
    public void saveProduct() {
    }

    @Test
    public void deleteProduct() {
    }
}