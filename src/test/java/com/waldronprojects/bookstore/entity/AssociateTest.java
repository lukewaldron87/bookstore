package com.waldronprojects.bookstore.entity;

import com.waldronprojects.bookstore.util.FieldModifier;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

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
        String bio = "bio";
        Collection<AssociateType> associateTypeCollection = createAssociateTypeCollection();
        Associate associate = new Associate(id, name, bio, associateTypeCollection);
        fieldModifier = new FieldModifier(associate);
        assertEquals(id, fieldModifier.getFieldValue("id"));
        assertEquals(name, fieldModifier.getFieldValue("name"));
        assertEquals(bio, fieldModifier.getFieldValue("bio"));
        assertEquals(associateTypeCollection,
                fieldModifier.getFieldValue("associateTypeCollection"));
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
    public void testGetBio() throws NoSuchFieldException, IllegalAccessException{
        String fieldValue = "bio";
        String fieldName = "bio";
        fieldModifier.setField(fieldName, fieldValue);
        Object returnedFieldValue = associate.getBio();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetBio() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "bio";
        String fieldName = "bio";
        associate.setBio(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetAssociateTypeCollection() throws NoSuchFieldException, IllegalAccessException{
        Collection<AssociateType> fieldValue = createAssociateTypeCollection();
        String fieldName = "associateTypeCollection";
        fieldModifier.setField(fieldName, fieldValue);
        Object returnedFieldValue = associate.getAssociateTypeCollection();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetAssociateTypeCollection() throws NoSuchFieldException, IllegalAccessException {
        Collection<AssociateType> fieldValue = createAssociateTypeCollection();
        String fieldName = "associateTypeCollection";
        associate.setAssociateTypeCollection(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testToString(){
        long id = 1;
        String name = "name";
        String bio = "bio";
        Collection<AssociateType> associateTypeCollection = createAssociateTypeCollection();
        Associate associate = new Associate(id, name, bio, associateTypeCollection);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Associate {")
                .append(" id='").append(id).append("'")
                .append(", name='").append(name).append("'")
                .append(", bio='").append(bio).append("'")
                .append(", associateTypeCollection='")
                    .append(associateTypeCollection.toString()).append("'")
                .append("}");
        String associateString = associate.toString();
        assertEquals(stringBuilder.toString(), associateString);
    }

    private Collection<AssociateType> createAssociateTypeCollection() {
        AssociateType associateType1 = new AssociateType(1L, "Author",
                "A person who writes a book");
        AssociateType associateType2 = new AssociateType(2L, "Illustrator",
                "A person who draws pictures for books");
        Collection<AssociateType> associateTypeCollection = new ArrayList<>();
        associateTypeCollection.add(associateType1);
        associateTypeCollection.add(associateType2);
        return associateTypeCollection;
    }
}
