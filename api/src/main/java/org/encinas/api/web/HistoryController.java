package org.encinas.api.web;

import org.encinas.business.HistoryService;
import org.encinas.business.dtos.HistoryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/histories")
public class HistoryController {
    private HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping(value = "/{id}", name = "Gets a History")
    public ResponseEntity getHistory(@PathVariable("id") int historyId) {
        HistoryDto historyDto = historyService.getHistoryById(historyId);

        return new ResponseEntity<>(historyDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", name = "Updates a History")
    public ResponseEntity updateHistory(@PathVariable("id") int historyId, @RequestBody HistoryDto historyDto) {
        HistoryDto history = historyService.updateHistory(historyId, historyDto);

        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", name = "Deletes a History")
    public ResponseEntity deleteHistory(@PathVariable("id") int historyId) {
        historyService.deleteHistory(historyId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
