package org.encinas.api.web;

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

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity createPatient(@Valid @RequestBody PatientDto patientDto) throws Exception{
        PatientDto patient = patientService.createPatient(patientDto);

        return new ResponseEntity(patient, HttpStatus.OK);
    }

    @PostMapping(value = "/{patientId}/histories")
    public ResponseEntity addHistory(@PathVariable("patientId") int patientId, @RequestBody HistoryDto historyDto) {
        HistoryDto history = patientService.createHistory(patientId, historyDto);

        return new ResponseEntity(history, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllPatients() {
        List<PatientDto> patients = patientService.getPatients();

        return new ResponseEntity(patients, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getPatientById(@PathVariable("id") int id) {
        PatientDto patient = patientService.getPatient(id);

        return new ResponseEntity(patient, HttpStatus.OK);
    }
}
