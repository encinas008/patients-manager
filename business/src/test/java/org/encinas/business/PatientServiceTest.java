package org.encinas.business;

import org.encinas.business.dtos.PatientDto;
import org.encinas.business.parsers.PatientParser;
import org.encinas.dao.entity.Patient;
import org.encinas.dao.repository.PatientDao;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {
    @Mock
    private PatientDao patientDao;

    private PatientService sut;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Patient patient = new Patient();
        patient.setId(1);
        patient.setFullName("Rafael Encinas");
        patient.setDni("7888281R");
        patient.setAddress("Av KapaYupanqui");

        when(patientDao.save(any(Patient.class))).thenReturn(patient);
        PatientParser patientParser = new PatientParser();

        sut = new PatientService(patientDao, patientParser);
    }

    @After
    public void after() {
        sut = null;
        patientDao = null;
    }

    @Test
    public void TestCreatePatient_WithValidData_ShouldReturnPatientDto() {
        PatientDto patientDto = new PatientDto();
        patientDto.setFullName("Rafael Encinas");
        patientDto.setDni("7888281R");
        patientDto.setAddress("Av KapaYupanqui");

        try {
            PatientDto result = sut.createPatient(patientDto);

            PatientDto expectedPatient = new PatientDto();
            expectedPatient.setId(1);
            expectedPatient.setFullName("Rafael Encinas");
            expectedPatient.setDni("7888281R");
            expectedPatient.setAddress("Av KapaYupanqui");

            assertEquals(expectedPatient, result);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
