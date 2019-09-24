package org.encinas.business;

import org.encinas.business.dtos.HistoryDto;
import org.encinas.business.dtos.PatientDto;
import org.encinas.business.parsers.HistoryParser;
import org.encinas.business.parsers.PatientParser;
import org.encinas.dao.entity.HistoryEntity;
import org.encinas.dao.entity.PatientEntity;
import org.encinas.dao.repository.PatientDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    private PatientDao patientDao;
    private PatientParser patientParser;
    private HistoryParser historyParser;

    public PatientService(PatientDao patientDao, PatientParser patientParser, HistoryParser historyParser) {
        this.patientDao = patientDao;
        this.patientParser = patientParser;
        this.historyParser = historyParser;
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

    public PatientDto createPatient(PatientDto patientDto) {
        PatientEntity patientEntity = patientParser.parseDtoToEntity(patientDto);
        PatientEntity patientSaved = patientDao.save(patientEntity);

        return patientParser.parseEntityToDto(patientSaved);
    }

    public HistoryDto createHistory(int patientId, HistoryDto historyDto) {
        PatientEntity patientEntity = patientDao.findById(patientId).orElse(null);
        if (patientEntity != null) {
            HistoryEntity historyEntity = historyParser.parseDtoToEntity(historyDto);
            patientEntity.getHistories().add(historyEntity);
            patientDao.save(patientEntity);

            return historyParser.parseEntityToDto(historyEntity);
        }
        return null;
    }

    public PatientDto getPatient(int id) {
        PatientEntity patientEntity = patientDao.findById(id).orElse(null);
        if (patientEntity != null) {
            return patientParser.parseEntityToDto(patientEntity);
        }
        return null;
    }
}
