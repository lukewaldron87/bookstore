package com.waldronprojects.bookstore.entity.factory;

import com.waldronprojects.bookstore.entity.Product;
import com.waldronprojects.bookstore.util.UnitTestProductEntityFactory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

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
        Object product = factory.createProduct(ProductTypeEnum.GENERIC);
        assertTrue(product instanceof Product);
    }

    @Test
    public void testCreateProductStaticValues(){
        Product product = factory.createProduct(ProductTypeEnum.GENERIC);
        Long id = 1L;
        assertEquals(product.getId(), id);
        assertEquals(product.getProductName(), "genericProduct");
        assertEquals(product.getUnitPrice(), new BigDecimal("1.10"));
        assertEquals(product.getDescription(), "productDescription");
        assertEquals(product.getUnitsInStock(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateProductIllegalArgument(){
        Product product = factory.createProduct(ProductTypeEnum.valueOf(BAD_PRODUCT_TYPE));
    }
}