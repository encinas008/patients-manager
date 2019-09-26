package org.encinas.business.parsers;

import org.encinas.business.dtos.HistoryDto;
import org.encinas.dao.entity.History;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class HistoryParser implements Parser<History, HistoryDto> {
    @Override
    public History parseDtoToEntity(HistoryDto dto) {
        History history = new History();
        BeanUtils.copyProperties(dto, history);

        return history;
    }

    @Override
    public HistoryDto parseEntityToDto(History entity) {
        HistoryDto historyDto = new HistoryDto();
        BeanUtils.copyProperties(entity, historyDto);

        return historyDto;
    }
}
