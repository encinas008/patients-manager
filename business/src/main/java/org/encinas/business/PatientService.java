package org.encinas.business;

import org.encinas.business.dtos.HistoryDto;
import org.encinas.business.dtos.PatientDto;
import org.encinas.business.parsers.HistoryParser;
import org.encinas.business.parsers.PatientParser;
import org.encinas.dao.entity.HistoryEntity;
import org.encinas.dao.entity.PatientEntity;
import org.encinas.dao.repository.HistoryDao;
import org.encinas.dao.repository.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientDao patientDao;

    @Autowired
    HistoryDao historyDao;

    @Autowired
    PatientParser patientParser;

    @Autowired
    HistoryParser historyParser;

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
}
