package com.waldronprojects.bookstore.entity;

import com.waldronprojects.bookstore.util.FieldModifier;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductTypeTest {

    private ProductType productType;
    private FieldModifier fieldModifier;

    @Before
    public void setUp() {
        productType = new ProductType();
        fieldModifier = new FieldModifier(productType);
    }

    @Test
    public void testConstructorWithoutId_setsAllFields() throws NoSuchFieldException,
                                                                IllegalAccessException{
        String name = "name";
        String description = "description";
        ProductType productType = new ProductType(name, description);
        fieldModifier = new FieldModifier(productType);
        assertEquals(name, fieldModifier.getFieldValue("name"));
        assertEquals(description, fieldModifier.getFieldValue("description"));
    }

    @Test
    public void testConstructorWithId_setsAllFields() throws NoSuchFieldException,
                                                             IllegalAccessException{
        Long id = 1L;
        String name = "name";
        String description = "description";
        ProductType productType = new ProductType(id, name, description);
        fieldModifier = new FieldModifier(productType);
        assertEquals(id, fieldModifier.getFieldValue("id"));
        assertEquals(name, fieldModifier.getFieldValue("name"));
        assertEquals(description, fieldModifier.getFieldValue("description"));
    }

    @Test
    public void testGetId() throws NoSuchFieldException, IllegalAccessException{
        Long fieldValue = 1L;
        String fieldName = "id";
        fieldModifier.setField(fieldName, fieldValue);
        Long returnedFieldValue = productType.getId();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetId() throws NoSuchFieldException, IllegalAccessException {
        Long fieldValue = 1L;
        String fieldName = "id";
        productType.setId(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetName() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "name";
        String fieldName = "name";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedFieldValue = productType.getName();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetName() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "name";
        String fieldName = "name";
        productType.setName(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetDescription() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "description";
        String fieldName = "description";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedFieldValue = productType.getDescription();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetDescription() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "description";
        String fieldName = "description";
        productType.setDescription(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testToString(){
        Long id = 1L;
        String name = "name";
        String description = "description";
        ProductType productType = new ProductType(id, name, description);
        StringBuilder expectedProductTypeString = new StringBuilder();
        expectedProductTypeString.append("ProductType{id=").append(id)
                .append(", name='").append(name).append("'")
                .append(", description='").append(description).append("'")
                .append('}');
        String productTypeString = productType.toString();
        assertEquals(expectedProductTypeString.toString(), productTypeString);
    }

}
