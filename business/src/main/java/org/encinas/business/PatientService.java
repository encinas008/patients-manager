package org.encinas.business;

import org.encinas.business.dtos.PatientDto;
import org.encinas.business.parsers.PatientParser;
import org.encinas.dao.entity.PatientEntity;
import org.encinas.dao.repository.PatientDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    private PatientDao patientDao;
    private PatientParser patientParser;

    public PatientService(PatientDao patientDao, PatientParser patientParser) {
        this.patientDao = patientDao;
        this.patientParser = patientParser;
    }

    public PatientDto createPatient(PatientDto patientDto) {
        PatientEntity patientEntity = patientParser.parseDtoToEntity(patientDto);
        PatientEntity patientSaved = patientDao.save(patientEntity);

        return patientParser.parseEntityToDto(patientSaved);
    }

    public List<PatientDto> getPatients() {
        List<PatientDto> patientList = new ArrayList<>();

        List<PatientEntity> patientEntities = patientDao.findAll();
        for (PatientEntity patientEntity: patientEntities) {
            PatientDto patientDto = patientParser.parseEntityToDto(patientEntity);
            patientList.add(patientDto);
        }

        return patientList;
    }

    public PatientDto getPatient(int id) {
        PatientEntity patientEntity = patientDao.getOne(id);
        if (patientEntity != null) {
            return patientParser.parseEntityToDto(patientEntity);
        }

        return null;
    }

    public PatientDto updatePatient(int patientId, PatientDto patientDto) {
        PatientEntity patientEntity = patientDao.getOne(patientId);
        if (patientEntity != null) {
            patientEntity.setFullName(patientDto.getFullName());
            patientEntity.setDni(patientDto.getDni());
            patientEntity.setAddress(patientDto.getAddress());
            patientDao.save(patientEntity);

            return patientParser.parseEntityToDto(patientEntity);
        }

        return null;
    }

    public boolean deletePatient(int patientId) {
        PatientEntity patientEntity = patientDao.getOne(patientId);
        if (patientEntity != null) {
            patientDao.delete(patientEntity);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
