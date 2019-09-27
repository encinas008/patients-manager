package org.encinas.business;

import org.encinas.business.dtos.HistoryDto;
import org.encinas.business.parsers.HistoryParser;
import org.encinas.dao.entity.History;
import org.encinas.dao.entity.Patient;
import org.encinas.dao.repository.HistoryDao;
import org.encinas.dao.repository.PatientDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {
    private PatientDao patientDao;
    private HistoryDao historyDao;
    private HistoryParser historyParser;

    public HistoryService(PatientDao patientDao, HistoryParser historyParser, HistoryDao historyDao) {
        this.patientDao = patientDao;
        this.historyParser = historyParser;
        this.historyDao = historyDao;
    }

    public HistoryDto createHistory(int patientId, HistoryDto historyDto) {
        Optional<Patient> patient = patientDao.findById(patientId);
        if (patient.isPresent()) {
            Patient patientEntity = patient.get();
            History history = historyParser.parseDtoToEntity(historyDto);
            history.setPatient(patientEntity);
            historyDao.save(history);

            return historyParser.parseEntityToDto(history);
        }

        return null;
    }

    public HistoryDto getHistoryById(int historyId) {
        Optional<History> history = historyDao.findById(historyId);
        return history.map(value -> historyParser.parseEntityToDto(value)).orElse(null);
    }

    public Boolean deleteHistory(int historyId) {
        Optional<History> history = historyDao.findById(historyId);
        if (history.isPresent()) {
            historyDao.delete(history.get());
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    public HistoryDto updateHistory(int historyId, HistoryDto historyDto) {
        Optional<History> history = historyDao.findById(historyId);
        if (history.isPresent()) {
            History historyEntity = history.get();
            historyEntity.setBloodSugarLevel(historyDto.getBloodSugarLevel());
            historyEntity.setHeight(historyDto.getHeight());
            historyEntity.setWeight(historyDto.getWeight());
            historyEntity.setSymptom(historyDto.getSymptom());
            historyEntity.setTreatment(historyDto.getTreatment());
            historyEntity.setObservation(historyDto.getObservation());
            historyDao.save(historyEntity);

            return historyParser.parseEntityToDto(historyEntity);
        }

        return null;
    }

    public List<HistoryDto> getHistoriesByPatientId(int patientId) {
        Optional<Patient> patient = patientDao.findById(patientId);
        List<HistoryDto> historyDtoList = new ArrayList<>();
        if (patient.isPresent()) {
            List<History> historyEntities = patient.get().getHistories();
            for (History history : historyEntities) {
                HistoryDto historyDto = historyParser.parseEntityToDto(history);
                historyDtoList.add(historyDto);
            }
        }

        return historyDtoList;
    }
}
