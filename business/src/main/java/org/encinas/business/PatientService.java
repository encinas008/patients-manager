package org.encinas.business;

import org.encinas.business.dtos.PatientDto;
import org.encinas.business.exceptions.DuplicatedDniException;
import org.encinas.business.parsers.PatientParser;
import org.encinas.dao.entity.Patient;
import org.encinas.dao.repository.PatientDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private PatientDao patientDao;
    private PatientParser patientParser;

    public PatientService(PatientDao patientDao, PatientParser patientParser) {
        this.patientDao = patientDao;
        this.patientParser = patientParser;
    }

    public PatientDto createPatient(PatientDto patientDto) throws DuplicatedDniException {
        Patient patient = patientDao.findPatientByDni(patientDto.getDni());
        if (patient != null) {
            throw new DuplicatedDniException("DNI already is taken");
        }

        Patient patientEntity = patientParser.parseDtoToEntity(patientDto);
        Patient patientSaved = patientDao.save(patientEntity);

        return patientParser.parseEntityToDto(patientSaved);
    }

    public List<PatientDto> getPatients() {
        List<PatientDto> patientList = new ArrayList<>();

        List<Patient> patientEntities = patientDao.findAll();
        for (Patient patient : patientEntities) {
            PatientDto patientDto = patientParser.parseEntityToDto(patient);
            patientList.add(patientDto);
        }

        return patientList;
    }

    public PatientDto getPatient(int id) {
        Optional<Patient> patient = patientDao.findById(id);
        return patient.map(value -> patientParser.parseEntityToDto(value)).orElse(null);
    }

    public PatientDto updatePatient(int patientId, PatientDto patientDto) {
        Optional<Patient> patient = patientDao.findById(patientId);
        if (patient.isPresent()) {
            Patient patientEntity = patient.get();
            patientEntity.setFullName(patientDto.getFullName());
            patientEntity.setDni(patientDto.getDni());
            patientEntity.setAddress(patientDto.getAddress());
            patientDao.save(patientEntity);

            return patientParser.parseEntityToDto(patientEntity);
        }

        return null;
    }

    public boolean deletePatient(int patientId) {
        Optional<Patient> patient = patientDao.findById(patientId);
        if (patient.isPresent()) {
            patientDao.delete(patient.get());
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
