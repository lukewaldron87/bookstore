package com.waldronprojects.bookstore.entity.factory;

import com.waldronprojects.bookstore.entity.Product;
import com.waldronprojects.bookstore.util.UnitTestProductEntityFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class UnitTestProductEntityFactoryTest {

    private ProductEntityFactory factory;
    private static final String BAD_PRODUCT_TYPE = "badProductType";

    @Before
    public void setUp(){
        factory = new UnitTestProductEntityFactory();
    }

    @Test
    public void testCreateProductReturnsCorrectType(){
        Object product = factory.createProduct(ProductType.GENERIC);
        assertTrue(product instanceof Product);
    }

    @Test
    public void testCreateProductStaticValues(){
        Product product = factory.createProduct(ProductType.GENERIC);
        assertEquals(product.getId(), 1);
        assertEquals(product.getProductName(), "genericProduct");
        assertEquals(product.getUnitPrice(), 1.1, 0.001);
        assertEquals(product.getDescription(), "productDescription");
        assertEquals(product.getUnitsInStock(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateProductIllegalArgument(){
        Product product = factory.createProduct(ProductType.valueOf(BAD_PRODUCT_TYPE));
    }
}