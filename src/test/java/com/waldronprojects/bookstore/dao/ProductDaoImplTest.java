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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = MvcConfig.class)
public class ProductDaoImplTest {

    @Autowired
    ProductDao productDao;

    @Test
    @Transactional
    @Rollback
    public void getProduct() {
        int id = 3;
        String productName = "book1";
        Product product = productDao.getProduct(id);
        assertEquals(id, product.getId());
        assertEquals(productName, product.getProductName());
    }

    @Test
    @Transactional
    @Rollback
    public void getProducts() {
        int numberOfProducts = 4;
        List<Product> productList = productDao.getProducts();
        assertEquals(numberOfProducts, productList.size());
    }

    @Test
    @Transactional
    @Rollback
    public void saveProduct() {
        int id = 99;
        String productName = "Unit Test Product";
        double unitPrice = 9.9;
        String description = "Unit Test Product Description";
        int stock = 99;
        Product product = new Product(id,
                                      productName,
                                      unitPrice,
                                      description,
                                      stock);
        productDao.saveProduct(product);
        Product returnedProduct = productDao.getProduct(id);
        assertEquals(id, returnedProduct.getId());
    }

    @Test
    @Transactional
    @Rollback
    public void deleteProduct() {
        int id = 3;
        productDao.deleteProduct(id);
        Product product = productDao.getProduct(id);
        assertNull(product);
    }
}