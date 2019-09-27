package org.encinas.api.web;

import org.encinas.business.HistoryService;
import org.encinas.business.dtos.HistoryDto;
import org.encinas.business.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/histories")
public class HistoryController {
    private HistoryService historyService;

    private final static String SUCCESSFULLY_RETRIEVED_HISTORIES = "Successfully was retrieved histories";
    private final static String SUCCESSFULLY_UPDATED_HISTORY = "Successfully was updated the History";
    private final static String COULD_NOT_FOUND_HISTORY= "Couldn't be found the history";
    private final static String SUCCESSFULLY_DELETED_HISTORY = "Successfully was deleted the History";

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping(value = "/{id}", name = "Gets a History")
    public ResponseEntity getHistory(@PathVariable("id") int historyId) {
        HistoryDto historyDto = historyService.getHistoryById(historyId);
        Response response = new Response.Builder().message(SUCCESSFULLY_RETRIEVED_HISTORIES).data(historyDto)
                                        .status(HttpStatus.OK).builder();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping(value = "/{id}", name = "Updates a History")
    public ResponseEntity updateHistory(@PathVariable("id") int historyId, @RequestBody HistoryDto history) {
        HistoryDto historyDto = historyService.updateHistory(historyId, history);
        Response response = new Response.Builder().message(SUCCESSFULLY_UPDATED_HISTORY)
                                        .data(historyDto).status(HttpStatus.OK).builder();
        if (historyDto == null) {
            response = new Response.Builder()
                                   .message(COULD_NOT_FOUND_HISTORY).data(historyDto)
                                   .status(HttpStatus.NOT_FOUND).builder();
        }

        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping(value = "/{id}", name = "Deletes a History")
    public ResponseEntity deleteHistory(@PathVariable("id") int historyId) {
        boolean historyDeleted = historyService.deleteHistory(historyId);
        Response response = new Response.Builder().message(SUCCESSFULLY_DELETED_HISTORY)
                                                  .status(HttpStatus.NO_CONTENT).builder();
        if (!historyDeleted) {
            response = new Response.Builder()
                                   .message(COULD_NOT_FOUND_HISTORY)
                                   .status(HttpStatus.NOT_FOUND).builder();
            return new ResponseEntity<>(response.getStatus());
        }
        return new ResponseEntity<>(response.getStatus());
    }
}
