package org.encinas.business.parsers;

import org.encinas.business.dtos.PatientDto;
import org.encinas.dao.entity.Patient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PatientParser implements Parser<Patient, PatientDto> {
    @Override
    public Patient parseDtoToEntity(PatientDto dto) {
        Patient patient = new Patient();
        BeanUtils.copyProperties(dto, patient);

        return patient;
    }

    @Override
    public PatientDto parseEntityToDto(Patient entity) {
        PatientDto patientDto = new PatientDto();
        BeanUtils.copyProperties(entity, patientDto);

        return patientDto;
    }
}
