package org.encinas.business;

import org.encinas.business.dtos.HistoryDto;
import org.encinas.business.parsers.HistoryParser;
import org.encinas.dao.entity.HistoryEntity;
import org.encinas.dao.entity.PatientEntity;
import org.encinas.dao.repository.HistoryDao;
import org.encinas.dao.repository.PatientDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        PatientEntity patientEntity = patientDao.getOne(patientId);
        if (patientEntity != null) {
            HistoryEntity historyEntity = historyParser.parseDtoToEntity(historyDto);
            patientEntity.getHistories().add(historyEntity);
            patientDao.save(patientEntity);

            return historyParser.parseEntityToDto(historyEntity);
        }

        return null;
    }

    public HistoryDto getHistoryById(int historyId) {
        HistoryEntity historyEntity = historyDao.getOne(historyId);
        if (historyEntity != null) {
            return historyParser.parseEntityToDto(historyEntity);
        }

        return null;
    }

    public Boolean deleteHistory(int historyId) {
        HistoryEntity historyEntity = historyDao.getOne(historyId);
        if (historyEntity != null) {
            historyDao.delete(historyEntity);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    public HistoryDto updateHistory(int historyId, HistoryDto historyDto) {
        HistoryEntity historyEntity = historyDao.getOne(historyId);
        if (historyEntity != null) {
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
        PatientEntity patientEntity = patientDao.getOne(patientId);
        List<HistoryDto> historyDtoList = new ArrayList<>();
        if (patientEntity != null) {
            List<HistoryEntity> historyEntities = patientEntity.getHistories();
            for (HistoryEntity historyEntity: historyEntities) {
                HistoryDto historyDto = historyParser.parseEntityToDto(historyEntity);
                historyDtoList.add(historyDto);
            }
        }

        return historyDtoList;
    }
}
