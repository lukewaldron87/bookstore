package com.waldronprojects.bookstore.entity;

import com.waldronprojects.bookstore.util.FieldModifier;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoleTest {

    Role role;
    private FieldModifier fieldModifier;

    @Before
    public void setUp() throws Exception {
        role = new Role();
        fieldModifier = new FieldModifier(role);
    }

    @Test
    public void testConstructor_setsAllFields() throws NoSuchFieldException, IllegalAccessException {
        String name = "name";
        role = new Role(name);
        fieldModifier = new FieldModifier(role);
        assertEquals(name, fieldModifier.getFieldValue("name"));
    }

    @Test
    public void getId() throws NoSuchFieldException, IllegalAccessException {
        Long id = 1L;
        fieldModifier.setField("id", id);
        Long returnedId = role.getId();
        assertEquals(id, returnedId);
    }

    @Test
    public void setId() throws NoSuchFieldException, IllegalAccessException {
        Long id = 1L;
        String fieldName = "id";
        role.setId(id);
        Object fieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(id, fieldValue);
    }

    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        String name = "name";
        String fieldName = "name";
        fieldModifier.setField(fieldName, name);
        String returnedUserName = role.getName();
        assertEquals(name, returnedUserName);
    }

    @Test
    public void setName() throws NoSuchFieldException, IllegalAccessException {
        String name = "name";
        String fieldName = "name";
        role.setName(name);
        Object fieldValue = fieldModifier.getFieldValue(fieldName);
        assertEquals(name, fieldValue);
    }

    @Test
    public void testToString() {
        role = new Role("name");
        role.setId(1L);
        fieldModifier = new FieldModifier(role);String expected = "Role{id=1, name='name'}";
        String returnedString = role.toString();
        assertEquals(expected, returnedString);
    }
}