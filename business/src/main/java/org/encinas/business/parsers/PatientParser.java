package org.encinas.business.parsers;

import org.encinas.business.dtos.PatientDto;
import org.encinas.dao.entity.PatientEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class PatientParser implements Parser<PatientEntity, PatientDto> {
    @Override
    public PatientEntity parseDtoToEntity(PatientDto dto) {
        PatientEntity patientEntity = new PatientEntity();
        BeanUtils.copyProperties(dto, patientEntity);

        return patientEntity;
    }

    @Override
    public PatientDto parseEntityToDto(PatientEntity entity) {
        PatientDto patientDto = new PatientDto();
        BeanUtils.copyProperties(entity, patientDto);

        return patientDto;
    }
}
