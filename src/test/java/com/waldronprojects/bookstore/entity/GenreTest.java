package com.waldronprojects.bookstore.entity;

import com.waldronprojects.bookstore.util.FieldModifier;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GenreTest {

    //create global variables
    private Genre genre;
    private FieldModifier fieldModifier;

    //setup class
    @Before
    public void setUp(){
        genre = new Genre();
        fieldModifier = new FieldModifier(genre);
    }

    //test constructors
    @Test
    public void testConstructorWithoutId_setsAllFields() throws NoSuchFieldException,
                                                                IllegalAccessException{
        String name = "name";
        String description = "description";
        Genre genre = new Genre(name, description);
        fieldModifier = new FieldModifier(genre);
        assertEquals(name, fieldModifier.getFieldValue("name"));
        assertEquals(description, fieldModifier.getFieldValue("description"));
    }

    @Test
    public void testConstructorWithId_setsAllFields() throws NoSuchFieldException,
            IllegalAccessException{
        int id = 1;
        String name = "name";
        String description = "description";
        Genre genre = new Genre(id, name, description);
        fieldModifier = new FieldModifier(genre);
        assertEquals(id, fieldModifier.getFieldValue("id"));
        assertEquals(name, fieldModifier.getFieldValue("name"));
        assertEquals(description, fieldModifier.getFieldValue("description"));
    }

    @Test
    public void testGetId() throws NoSuchFieldException, IllegalAccessException{
        int fieldValue = 1;
        String fieldName = "id";
        fieldModifier.setField(fieldName, fieldValue);
        int returnedFieldValue = genre.getId();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetId() throws NoSuchFieldException, IllegalAccessException {
        int fieldValue = 1;
        String fieldName = "id";
        genre.setId(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetName() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "name";
        String fieldName = "name";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedFieldValue = genre.getName();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetName() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "name";
        String fieldName = "name";
        genre.setName(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetDescription() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "description";
        String fieldName = "description";
        fieldModifier.setField(fieldName, fieldValue);
        String returnedFieldValue = genre.getDescription();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetDescription() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "description";
        String fieldName = "description";
        genre.setDescription(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testToString(){
        int id = 1;
        String name = "name";
        String description = "description";
        Genre genre = new Genre(id, name, description);
        StringBuilder expectedGenreString = new StringBuilder();
        expectedGenreString.append("Genre{ id=").append(id)
                .append(", name='").append(name).append("'")
                .append(", description='").append(description).append("'")
                .append('}');
        String genreString = genre.toString();
        assertEquals(expectedGenreString.toString(), genreString);
    }
}
