package com.waldronprojects.bookstore.entity;

import com.waldronprojects.bookstore.util.FieldModifier;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AssociateTest {

    private Associate associate;
    private FieldModifier fieldModifier;

    @Before
    public void setUp() {
        associate = new Associate();
        fieldModifier = new FieldModifier(associate);
    }

    @Test
    public void testConstructorWithId_setsAllFields() throws NoSuchFieldException,
                                                             IllegalAccessException{
        long id = 1;
        String name = "name";
        String description = "description";
        Associate associate = new Associate(id, name, description);
        fieldModifier = new FieldModifier(associate);
        assertEquals(id, fieldModifier.getFieldValue("id"));
        assertEquals(name, fieldModifier.getFieldValue("name"));
        assertEquals(description, fieldModifier.getFieldValue("description"));
    }
    @Test
    public void testGetId() throws NoSuchFieldException, IllegalAccessException{
        long fieldValue = 1;
        String fieldName = "id";
        fieldModifier.setField(fieldName, fieldValue);
        Object returnedFieldValue = associate.getId();
        assertEquals(fieldValue, returnedFieldValue);
    }
    @Test
    public void testSetId() throws NoSuchFieldException, IllegalAccessException {
        long fieldValue = 1;
        String fieldName = "id";
        associate.setId(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }
    @Test
    public void testGetName() throws NoSuchFieldException, IllegalAccessException{
        String fieldValue = "name";
        String fieldName = "name";
        fieldModifier.setField(fieldName, fieldValue);
        Object returnedFieldValue = associate.getName();
        assertEquals(fieldValue, returnedFieldValue);
    }
    @Test
    public void testSetName() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "name";
        String fieldName = "name";
        associate.setName(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }
    @Test
    public void testGetDescription() throws NoSuchFieldException, IllegalAccessException{
        String fieldValue = "description";
        String fieldName = "description";
        fieldModifier.setField(fieldName, fieldValue);
        Object returnedFieldValue = associate.getDescription();
        assertEquals(fieldValue, returnedFieldValue);
    }
    @Test
    public void testSetDescription() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "description";
        String fieldName = "description";
        associate.setDescription(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }
    @Test
    public void testToString(){
        long id = 1;
        String name = "name";
        String description = "description";
        Associate associate = new Associate(id, name, description);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Associate {")
                .append(" id='").append(id).append("'")
                .append(", name='").append(name).append("'")
                .append(", description='").append(description).append("'")
                .append("}");
        String associateString = associate.toString();
        assertEquals(stringBuilder.toString(), associateString);
    }
}
