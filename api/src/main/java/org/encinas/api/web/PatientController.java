package org.encinas.api.web;

import org.encinas.business.PatientService;
import org.encinas.business.dtos.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping
    public ResponseEntity createPatient(@RequestBody PatientDto patientDto) {
        PatientDto patient = patientService.createPatient(patientDto);

        return new ResponseEntity(patient, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity getAllPatients() {
        List<PatientDto> patients = patientService.getPatients();

        return new ResponseEntity(patients, HttpStatus.OK);
    }
}
