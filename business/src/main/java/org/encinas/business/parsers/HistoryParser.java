package org.encinas.business.parsers;

import org.encinas.business.dtos.HistoryDto;
import org.encinas.dao.entity.HistoryEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class HistoryParser implements Parser<HistoryEntity, HistoryDto> {
    @Override
    public HistoryEntity parseDtoToEntity(HistoryDto dto) {
        HistoryEntity historyEntity = new HistoryEntity();
        BeanUtils.copyProperties(dto, historyEntity);

        return historyEntity;
    }

    @Override
    public HistoryDto parseEntityToDto(HistoryEntity entity) {
        HistoryDto historyDto = new HistoryDto();
        BeanUtils.copyProperties(entity, historyDto);

        return historyDto;
    }
}
