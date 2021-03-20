package com.waldronprojects.bookstore.entity;

import com.waldronprojects.bookstore.util.FieldModifier;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ImageTest {

    private Image image;
    private FieldModifier fieldModifier;

    @Before
    public void setUp() {
        image = new Image();
        fieldModifier = new FieldModifier(image);
    }

    @Test
    public void testConstructorWithId_setsAllFields() throws NoSuchFieldException,
                                                             IllegalAccessException{
        long id = 1;
        String fileUrl = "fileUrl";
        Image image = new Image(id, fileUrl);
        fieldModifier = new FieldModifier(image);
        assertEquals(id, fieldModifier.getFieldValue("id"));
        assertEquals(fileUrl, fieldModifier.getFieldValue("fileUrl"));
    }

    @Test
    public void testGetId() throws NoSuchFieldException, IllegalAccessException{
        long fieldValue = 1;
        String fieldName = "id";
        fieldModifier.setField(fieldName, fieldValue);
        Object returnedFieldValue = image.getId();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetId() throws NoSuchFieldException, IllegalAccessException {
        long fieldValue = 1;
        String fieldName = "id";
        image.setId(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testGetFileUrl() throws NoSuchFieldException, IllegalAccessException{
        String fieldValue = "fileUrl";
        String fieldName = "fileUrl";
        fieldModifier.setField(fieldName, fieldValue);
        Object returnedFieldValue = image.getFileUrl();
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testSetFileUrl() throws NoSuchFieldException, IllegalAccessException {
        String fieldValue = "fileUrl";
        String fieldName = "fileUrl";
        image.setFileUrl(fieldValue);
        Object returnedFieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(fieldValue, returnedFieldValue);
    }

    @Test
    public void testToString(){
        long id = 1;
        long productId = 1;
        String fileUrl = "fileUrl";
        Image image = new Image(id, fileUrl);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Image {")
                .append(" id='").append(id).append("'")
                .append(", fileUrl='").append(fileUrl).append("'")
                .append("}");
        String imageString = image.toString();
        assertEquals(stringBuilder.toString(), imageString);
    }
}

