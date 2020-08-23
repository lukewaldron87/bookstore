package com.waldronprojects.bookstore.util;

import java.lang.reflect.Field;

public class FieldModifier {
    private final Object object;

    public FieldModifier(Object object) {
        this.object = object;
    }

    public void setField(String fieldName, Object inputVariable) throws NoSuchFieldException, IllegalAccessException {
        Field field = getField(fieldName);
        field.set(object, inputVariable);
    }

    public Object getFieldValue(String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = getField(fieldName);
        return field.get(object);
    }

    private Field getField(String fieldName) throws NoSuchFieldException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }
}