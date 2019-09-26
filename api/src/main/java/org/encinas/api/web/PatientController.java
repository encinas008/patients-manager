package org.encinas.api.web;

import org.encinas.business.HistoryService;
import org.encinas.business.PatientService;
import org.encinas.business.dtos.HistoryDto;
import org.encinas.business.dtos.PatientDto;
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

    public PatientController(PatientService patientService, HistoryService historyService) {
        this.patientService = patientService;
        this.historyService = historyService;
    }

    @PostMapping(name = "Creates a patient.")
    public ResponseEntity createPatient(@Valid @RequestBody PatientDto patientDto) {
        PatientDto patient = patientService.createPatient(patientDto);

        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", name = "Updates a patient.")
    public ResponseEntity updatePatient(@PathVariable("id") int patientId, @Valid @RequestBody PatientDto patientDto) {
        PatientDto patient = patientService.updatePatient(patientId, patientDto);

        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", name = "Deletes a patient.")
    public ResponseEntity deletePatient(@PathVariable("id") int patientId) {
        patientService.deletePatient(patientId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/{patientId}/histories", name = "Creates a history for a patient.")
    public ResponseEntity addHistory(@PathVariable("patientId") int patientId, @RequestBody HistoryDto historyDto) {
        HistoryDto history = historyService.createHistory(patientId, historyDto);

        return new ResponseEntity<>(history, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{patientId}/histories", name = "Gets histories for a patient.")
    public ResponseEntity getHistoriesByPatientId(@PathVariable("patientId") int patientId) {
        List<HistoryDto> histories = historyService.getHistoriesByPatientId(patientId);

        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

    @GetMapping(name = "Gets all patient.")
    public ResponseEntity getAllPatients() {
        List<PatientDto> patients = patientService.getPatients();

        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", name = "Gets a patient.")
    public ResponseEntity getPatientById(@PathVariable("id") int id) {
        PatientDto patient = patientService.getPatient(id);

        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

}
