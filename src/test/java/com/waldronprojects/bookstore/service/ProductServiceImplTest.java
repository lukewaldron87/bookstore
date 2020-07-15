package com.waldronprojects.bookstore.service;

import com.waldronprojects.bookstore.dao.ProductDao;
import com.waldronprojects.bookstore.entity.Product;
import com.waldronprojects.bookstore.factory.ProductEntityFactory;
import com.waldronprojects.bookstore.factory.UnitTestProductEntityFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductDao productDao;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void testGetProduct() {
        Product product = createProductObject(UnitTestProductEntityFactory.GENERIC);
        Mockito.when(productDao.getProduct(product.getId())).thenReturn(product);
        Product returnedProduct = productService.getProduct(product.getId());
        assertEquals(product, returnedProduct);
    }

    private Product createProductObject(String type){
        ProductEntityFactory productEntityFactory = new UnitTestProductEntityFactory();
        return productEntityFactory.createProduct(type);
    }

    @Test
    public void testGetProducts() {
    }

    @Test
    public void testSaveProduct() {
    }

    @Test
    public void testDeteteProduct() {
    }
}