package com.waldronprojects.bookstore.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FieldModifierTest {

    class TestClass{
        private String testField;

        public String getTestField() {
            return testField;
        }

        public void setTestField(String testField) {
            this.testField = testField;
        }
    }

    private FieldModifier fieldModifier;
    private TestClass testClass;
    private static final String FIELD_NAME = "testField";

    @Before
    public void setUp(){
        testClass = new TestClass();
        fieldModifier = new FieldModifier(testClass);
    }

    @Test
    public void testSetField_setsFieldCorrectly() throws NoSuchFieldException, IllegalAccessException {
        String inputValue = "test";
        fieldModifier.setField(FIELD_NAME, inputValue);
        assertEquals(inputValue, testClass.getTestField());
    }

    @Test
    public void testGetField_getsFieldCorrectly() throws NoSuchFieldException, IllegalAccessException {
        String inputValue = "test";
        testClass.setTestField(inputValue);;
        Object returnedValue = fieldModifier.getFieldValue(FIELD_NAME);
        assertEquals(inputValue, returnedValue);
    }
}
