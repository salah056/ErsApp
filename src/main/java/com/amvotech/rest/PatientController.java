package com.amvotech.rest;

import com.amvotech.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.amvotech.model.Patient;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping(value = "/patients")
    public ResponseEntity<Patient> registerPatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.safePatient(patient));
    }

    @GetMapping(value = "/getpatients")
    public ResponseEntity<List<Patient>> getAllPatient() {
        return ResponseEntity.ok(patientService.getAllPatient());
    }

    @GetMapping(value = "/getpatientbyid/{pid}")
    public ResponseEntity<Optional<Patient>> getPatientById(@PathVariable String pid) {
        Long tempId = Long.valueOf(pid);
        return ResponseEntity.ok(patientService.getPatientById(tempId));
    }

    @PutMapping(value = "/updatepatient/{pid}")
    public ResponseEntity<Optional<Patient>> updatePatientById(@PathVariable String pid, @RequestBody Patient patient) {
        Long tempId = Long.valueOf(pid);
        Optional<Patient> patientTemp = patientService.getPatientById(tempId);
        Patient actualData = patientTemp.get();
        if (patient.getName() != null) {
            actualData.setName(patient.getName());
        }
        if (patient.getDateOfBirth() != null) {
            actualData.setDateOfBirth(patient.getDateOfBirth());
        }
        if (patient.getContactDetails() != null) {
            actualData.setContactDetails(patient.getContactDetails());
        }
        patientService.safePatient(actualData);
        return ResponseEntity.ok(Optional.ofNullable(patientService.safePatient(actualData)));

    }

    @DeleteMapping(value = "/deleteById/{patientId}")
    public ResponseEntity<String> deletePatientById(@PathVariable String patientId) {
        Long tempId = Long.valueOf(patientId);
        patientService.deletePatientById(tempId);

        return ResponseEntity.ok("patient Id" + patientId + "deleted");

    }
}