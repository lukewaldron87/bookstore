package com.waldronprojects.bookstore.validation;

import com.waldronprojects.bookstore.dto.UserDto;
import com.waldronprojects.bookstore.util.UnitTestUserDtoFactory;
import com.waldronprojects.bookstore.dto.factory.UserDtoFactory;
import com.waldronprojects.bookstore.entity.factory.UserType;
import org.hibernate.annotations.common.annotationfactory.AnnotationDescriptor;
import org.hibernate.annotations.common.annotationfactory.AnnotationFactory;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FieldMatchValidatorTest {

    private UserDto userDto;
    private FieldMatchValidator validator;
    private ConstraintValidatorContext context;

    @Before
    public void setUp(){
        UserDtoFactory factory = new UnitTestUserDtoFactory();
        userDto = factory.createUserDto(UserType.CUSTOMER);
        FieldMatch fieldMatch = createAnnotation("password",
                                                "matchingPassword",
                                                "The password fields must match");
        validator = createFieldMatchValidator(fieldMatch);
        mockConstraintValidatorContext();
    }

    private FieldMatch createAnnotation(String first, String second, String message) {
        AnnotationDescriptor descriptor =
                new AnnotationDescriptor( FieldMatch.class );
        descriptor.setValue( "first", first );
        descriptor.setValue( "second", second );
        descriptor.setValue("message", message);

        return AnnotationFactory.create(descriptor);
    }

    private FieldMatchValidator createFieldMatchValidator(FieldMatch fieldMatch) {
        FieldMatchValidator validator = new FieldMatchValidator();
        validator.initialize(fieldMatch);
        return validator;
    }

    private void mockConstraintValidatorContext(){
        context = mock(ConstraintValidatorContext.class);
        ConstraintValidatorContext.ConstraintViolationBuilder builder =
                mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext nodeBuilderCustomizableContext =
                mock(ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext.class);

        // add a return for each call when setting the reply
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        when(context.buildConstraintViolationWithTemplate(anyString()).addPropertyNode(anyString()))
                .thenReturn(nodeBuilderCustomizableContext);
        when(context.buildConstraintViolationWithTemplate(anyString()).addPropertyNode(anyString()).addConstraintViolation())
                .thenReturn(context);
    }

    @Test
    public void testIsValid() {
        boolean stringsMatch = validator.isValid(userDto, context);
        assertTrue(stringsMatch);
    }

    @Test
    public void testIsValidNotMatching() {
        userDto.setMatchingPassword("notMatching");
        boolean stringsMatch = validator.isValid(userDto, context);
        assertFalse(stringsMatch);
    }
}