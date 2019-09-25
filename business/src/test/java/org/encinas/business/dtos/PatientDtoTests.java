package org.encinas.business.dtos;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PatientDtoTests {
    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    @Test
    public void shouldHaveNoViolations() {
        PatientDto sut = new PatientDto();
        sut.setFullName("Rafael Encinas");
        sut.setDni("78882847");
        sut.setAddress("Av. Canada");

        Set<ConstraintViolation<PatientDto>> violations = validator.validate(sut);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldDetectInvalidData_WhenFullNameIsLessThanTreeCharacters() {
        PatientDto sut = new PatientDto();
        sut.setFullName("Ra");
        sut.setDni("78882847");
        sut.setAddress("Av. Canada");

        Set<ConstraintViolation<PatientDto>> violations = validator.validate(sut);

        assertEquals(violations.size(), 1);

        ConstraintViolation<PatientDto> violation = violations.iterator().next();
        assertEquals("length must be between 3 and 50", violation.getMessage());
        assertEquals("fullName", violation.getPropertyPath().toString());
        assertEquals("Ra", violation.getInvalidValue());
    }

    @Test
    public void shouldDetectInvalidData_WhenFullNameIsGreaterThanFiftyCharacters() {
        PatientDto sut = new PatientDto();
        sut.setFullName("Joseph Joseph Joseph Joseph Joseph Joseph Joseph Joseph");
        sut.setDni("78882847");
        sut.setAddress("Av. Canada");

        Set<ConstraintViolation<PatientDto>> violations = validator.validate(sut);

        assertEquals(violations.size(), 1);

        ConstraintViolation<PatientDto> violation = violations.iterator().next();
        assertEquals("length must be between 3 and 50", violation.getMessage());
        assertEquals("fullName", violation.getPropertyPath().toString());
    }
}
