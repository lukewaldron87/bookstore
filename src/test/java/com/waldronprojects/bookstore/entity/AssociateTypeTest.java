package com.waldronprojects.bookstore.entity;

import com.waldronprojects.bookstore.util.FieldModifier;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AssociateTypeTest {

    private AssociateType associateType;
    private FieldModifier fieldModifier;

    @Before
    public void setUp() {
        associateType = new AssociateType();
        fieldModifier = new FieldModifier(associateType);
    }

    @Test
    public void testConstructorWithId_setsAllFields() throws NoSuchFieldException,
                                                             IllegalAccessException{
        long id = 1;
        String name = "name";
        String description = "description";
        AssociateType associateType = new AssociateType(id, name, description);
        fieldModifier = new FieldModifier(associateType);
        assertEquals(id, fieldModifier.getFieldValue("id"));
        assertEquals(name, fieldModifier.getFieldValue("name"));
        assertEquals(description, fieldModifier.getFieldValue("description"));
    }

    @Test
    public void testGetId() throws NoSuchFieldException, IllegalAccessException{
        long fieldValue = 1;
        String fieldName = "id";
        fieldModifier.setField(fieldName, fieldValue);
        Object returnedFieldValue = associateType.getId();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetId() throws NoSuchFieldException, IllegalAccessException {
        long fieldValue = 1;
        String fieldName = "id";
        associateType.setId(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetName() throws NoSuchFieldException, IllegalAccessException{
        String fieldValue = "name";
        String fieldName = "name";
        fieldModifier.setField(fieldName, fieldValue);
        Object returnedFieldValue = associateType.getName();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetName() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "name";
        String fieldName = "name";
        associateType.setName(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetDescription() throws NoSuchFieldException, IllegalAccessException{
        String fieldValue = "description";
        String fieldName = "description";
        fieldModifier.setField(fieldName, fieldValue);
        Object returnedFieldValue = associateType.getDescription();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetDescription() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "description";
        String fieldName = "associateTypeCollection";
        associateType.setDescription(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testToString(){
        long id = 1;
        String name = "name";
        String description = "description";
        AssociateType associateType = new AssociateType(id, name, description);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AssociateType {")
                .append(" id='").append(id).append("'")
                .append(", name='").append(name).append("'")
                .append(", description='").append(description).append("'")
                .append("}");
        String associateTypeString = associateType.toString();
        assertEquals(stringBuilder.toString(), associateTypeString);
    }
}
