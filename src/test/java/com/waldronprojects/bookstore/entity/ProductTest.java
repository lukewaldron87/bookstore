package com.waldronprojects.bookstore.entity;

import com.waldronprojects.bookstore.util.FieldModifier;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductTest {

    private Product product;
    private FieldModifier fieldModifier;

    @Before
    public void setUp() throws Exception {
        product = new Product();
        fieldModifier = new FieldModifier(product);
    }

    @Test
    public void testConstructorWithoutId_setsAllFields() throws NoSuchFieldException,
                                                                IllegalAccessException {
        String productName = "productName";
        double unitPrice = 1.1;
        String description = "description";
        int unitsInStock = 1;
        Product product = new Product(productName, unitPrice, description, unitsInStock);
        fieldModifier = new FieldModifier(product);
        assertEquals(productName, fieldModifier.getFieldValue("productName"));
        assertEquals(unitPrice, fieldModifier.getFieldValue("unitPrice"));
        assertEquals(description, fieldModifier.getFieldValue("description"));
        assertEquals(unitsInStock, fieldModifier.getFieldValue("unitsInStock"));

    }

    @Test
    public void testConstructorWithId_setsAllFields() throws NoSuchFieldException,
                                                             IllegalAccessException {
        int id = 1;
        String productName = "productName";
        double unitPrice = 1.1;
        String description = "description";
        int unitsInStock = 1;
        Product product = new Product(id, productName, unitPrice, description, unitsInStock);
        fieldModifier = new FieldModifier(product);
        assertEquals(id, fieldModifier.getFieldValue("id"));
        assertEquals(productName, fieldModifier.getFieldValue("productName"));
        assertEquals(unitPrice, fieldModifier.getFieldValue("unitPrice"));
        assertEquals(description, fieldModifier.getFieldValue("description"));
        assertEquals(unitsInStock, fieldModifier.getFieldValue("unitsInStock"));

    }

    @Test
    public void testGetId() throws NoSuchFieldException, IllegalAccessException {
        int fieldValue = 1;
        String fieldName = "id";
        fieldModifier.setField(fieldName, fieldValue);
        int returnedFieldValue = product.getId();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetId() throws NoSuchFieldException, IllegalAccessException {
        int fieldValue = 1;
        String fieldName = "id";
        product.setId(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetProductName() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "productName";
        String fieldName = "productName";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedFieldValue = product.getProductName();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetProductName() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "productName";
        String fieldName = "productName";
        product.setProductName(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetUnitPrice() throws NoSuchFieldException, IllegalAccessException {
        double fieldValue = 1.1;
        String fieldName = "unitPrice";
        fieldModifier.setField(fieldName, fieldValue);
        double returnedFieldValue = product.getUnitPrice();
        assertEquals(fieldValue, returnedFieldValue, 0);
    }

    @Test
    public void testSetUnitPrice() throws NoSuchFieldException, IllegalAccessException {
        double fieldValue = 1.1;
        String fieldName = "unitPrice";
        product.setUnitPrice(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetDescription() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "description";
        String fieldName = "description";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedFieldValue = product.getDescription();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetDescription() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "description";
        String fieldName = "description";
        product.setDescription(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetUnitsInUnitsInStock() throws NoSuchFieldException, IllegalAccessException{
        int filedValue = 1;
        String fieldName = "unitsInStock";
        fieldModifier.setField(fieldName, filedValue);
        int returnedFieldValue = product.getUnitsInStock();
        assertEquals(filedValue, returnedFieldValue);
    }

    @Test
    public void testSetUnitsInStock() throws NoSuchFieldException, IllegalAccessException{
        int filedValue = 1;
        String fieldName = "unitsInStock";
        product.setUnitsInStock(filedValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(filedValue, returnedFieldValue);
    }

    @Test
    public void testToString(){
        String productName = "productName";
        double unitPrice = 2.2;
        String description = "description";
        int unitsInStock = 1;
        Product product = new Product(productName, unitPrice, description, unitsInStock);
        String productString = product.toString();
        StringBuilder expectedProductStringBuilder = new StringBuilder();
        expectedProductStringBuilder.append("Product [id=null, productName=").append(productName)
                                    .append(", unitPrice=").append(unitPrice)
                                    .append(", description=").append(description)
                                    .append(", unitsInStock=").append(unitsInStock)
                                    .append("]");
        assertEquals(expectedProductStringBuilder.toString(), productString);
    }
}