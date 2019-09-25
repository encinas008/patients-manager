package org.encinas.business;

import org.encinas.business.dtos.PatientDto;
import org.encinas.business.parsers.PatientParser;
import org.encinas.dao.entity.PatientEntity;
import org.encinas.dao.repository.PatientDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTests {
    @Mock
    private PatientDao patientDao;

    private PatientService sut;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(1);
        patientEntity.setFullName("Rafael Encinas");
        patientEntity.setDni("7888281R");
        patientEntity.setAddress("Av KapaYupanqui");

        when(patientDao.save(any(PatientEntity.class))).thenReturn(patientEntity);
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
        PatientDto result = sut.createPatient(patientDto);

        PatientDto expectedPatient = new PatientDto();
        expectedPatient.setId(1);
        expectedPatient.setFullName("Rafael Encinas");
        expectedPatient.setDni("7888281R");
        expectedPatient.setAddress("Av KapaYupanqui");

        assertEquals(expectedPatient, result);
    }
}
