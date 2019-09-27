package org.encinas.api.web;

import org.encinas.business.HistoryService;
import org.encinas.business.PatientService;
import org.encinas.business.dtos.HistoryDto;
import org.encinas.business.dtos.PatientDto;
import org.encinas.business.exceptions.DuplicatedDniException;
import org.encinas.business.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {

    private PatientService patientService;
    private HistoryService historyService;

    private final static String SUCCESSFULLY_CREATED_PATIENT = "Successfully was created a new Patient";
    private final static String SUCCESSFULLY_CREATED_HISTORY = "Successfully was created a new history";
    private final static String SUCCESSFULLY_UPDATED_PATIENT = "Successfully was updated the Patient";
    private final static String COULD_NOT_FOUND_PATIENT = "Couldn't be found the patient";
    private final static String SUCCESSFULLY_DELETED_PATIENT = "Successfully was deleted the Patient";
    private final static String SUCCESSFULLY_RETRIEVED_HISTORIES = "Successfully was retrieved histories";
    private final static String SUCCESSFULLY_RETRIEVED_PATIENTS = "Successfully was retrieved patients";

    public PatientController(PatientService patientService, HistoryService historyService) {
        this.patientService = patientService;
        this.historyService = historyService;
    }

    @PostMapping(name = "Creates a patient.")
    public ResponseEntity createPatient(@Valid @RequestBody PatientDto patientDto) throws DuplicatedDniException {
        PatientDto patient = patientService.createPatient(patientDto);
        Response response = new Response.Builder().message(SUCCESSFULLY_CREATED_PATIENT)
                                                  .data(patient)
                                                  .status(HttpStatus.CREATED).builder();

        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping(value = "/{id}", name = "Updates a patient.")
    public ResponseEntity updatePatient(@PathVariable("id") int patientId, @Valid @RequestBody PatientDto patientDto) {
        PatientDto patient = patientService.getPatient(patientId);
        Response response = new Response.Builder().message(COULD_NOT_FOUND_PATIENT)
                                                  .status(HttpStatus.NOT_FOUND).builder();

        if (patient != null) {
            PatientDto patientUpdated = patientService.updatePatient(patientId, patientDto);
            response = new Response.Builder().message(SUCCESSFULLY_UPDATED_PATIENT).data(patientUpdated)
                                             .status(HttpStatus.OK).builder();
        }

        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping(value = "/{id}", name = "Deletes a patient.")
    public ResponseEntity deletePatient(@PathVariable("id") int patientId) {
        boolean patientDeleted = patientService.deletePatient(patientId);
        Response response = new Response.Builder().message(SUCCESSFULLY_DELETED_PATIENT)
                                                  .status(HttpStatus.NO_CONTENT).builder();
        if (!patientDeleted) {
            response = new Response.Builder().message(COULD_NOT_FOUND_PATIENT)
                                             .status(HttpStatus.NOT_FOUND).builder();
        }

        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping(value = "/{patientId}/histories", name = "Creates a history for a patient.")
    public ResponseEntity addHistory(@PathVariable("patientId") int patientId, @Valid @RequestBody HistoryDto historyDto) {
        PatientDto patientDto = patientService.getPatient(patientId);
        Response response = new Response.Builder().message(COULD_NOT_FOUND_PATIENT).status(HttpStatus.NOT_FOUND).builder();

        if (patientDto != null) {
            HistoryDto history = historyService.createHistory(patientId, historyDto);
            response = new Response.Builder().message(SUCCESSFULLY_CREATED_HISTORY)
                                             .data(history).status(HttpStatus.CREATED).builder();
        }

        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping(value = "/{patientId}/histories", name = "Gets histories for a patient.")
    public ResponseEntity getHistoriesByPatientId(@PathVariable("patientId") int patientId) {
        PatientDto patientDto = patientService.getPatient(patientId);
        Response response = new Response.Builder().message(COULD_NOT_FOUND_PATIENT)
                                                  .status(HttpStatus.NOT_FOUND).builder();

        if (patientDto != null) {
            List<HistoryDto> histories = historyService.getHistoriesByPatientId(patientId);
            response = new Response.Builder().message(SUCCESSFULLY_RETRIEVED_HISTORIES)
                                             .data(histories).status(HttpStatus.OK).builder();
        }

        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping(name = "Gets all patient.")
    public ResponseEntity getAllPatients() {
        List<PatientDto> patients = patientService.getPatients();
        Response response = new Response.Builder().message(SUCCESSFULLY_RETRIEVED_PATIENTS).data(patients)
                                                  .status(HttpStatus.OK).builder();

        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping(path = "/{id}", name = "Gets a patient.")
    public ResponseEntity getPatientById(@PathVariable("id") int id) {
        PatientDto patient = patientService.getPatient(id);
        Response response = new Response.Builder().message(COULD_NOT_FOUND_PATIENT)
                                                  .status(HttpStatus.NOT_FOUND).builder();

        if (patient != null) {
            response = new Response.Builder().message(SUCCESSFULLY_RETRIEVED_PATIENTS).data(patient)
                                             .status(HttpStatus.OK).builder();
        }

        return new ResponseEntity<>(response, response.getStatus());
    }

}
