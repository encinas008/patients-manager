package org.encinas.business.dtos;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PatientDtoTest {
    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
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
        ConstraintViolation<PatientDto> violation = violations.iterator().next();

        assertEquals("Ra", violation.getInvalidValue());
    }

    @Test
    public void shouldDetectInvalidData_WhenFullNameIsGreaterThanFiftyCharacters() {
        PatientDto sut = new PatientDto();
        sut.setFullName("Joseph Joseph Joseph Joseph Joseph Joseph Joseph Joseph");
        sut.setDni("78882847");
        sut.setAddress("Av. Canada");

        Set<ConstraintViolation<PatientDto>> violations = validator.validate(sut);
        ConstraintViolation<PatientDto> violation = violations.iterator().next();

        assertEquals("Joseph Joseph Joseph Joseph Joseph Joseph Joseph Joseph", violation.getInvalidValue());
    }
}
