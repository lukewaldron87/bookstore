package com.waldronprojects.bookstore.validation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class EmailValidatorTest {

    private EmailValidator emailValidator;

    @Before
    public void setUp(){
        emailValidator = new EmailValidator();
    }

    @Test
    public void testIsValid() {
        boolean isValidEmail = emailValidator.isValid("test@test.com", null);
        assertTrue(isValidEmail);
    }

    @Test
    public void estIsNotValidEmailMissingAt(){
        boolean isValidEmail = emailValidator.isValid("testtest.com", null);
        assertFalse(isValidEmail);
    }

    @Test
    public void testIsNotValidEmailMissingDot(){
        boolean isValidEmail = emailValidator.isValid("test@testcom", null);
        assertFalse(isValidEmail);
    }

    @Test
    public void testIsNotValidNoEmail(){
        boolean isValidEmail = emailValidator.isValid("", null);
        assertFalse(isValidEmail);
    }

    @Test
    public void testIsNotValidNullEmail(){
        boolean isValidEmail = emailValidator.isValid(null, null);
        assertFalse(isValidEmail);
    }
}