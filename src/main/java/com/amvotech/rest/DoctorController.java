package com.amvotech.rest;

import com.amvotech.model.Doctor;
import com.amvotech.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping(value = "/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping(value = "/doctor/{did}")
    public ResponseEntity<Optional<Doctor>> getDoctorById(@PathVariable Long did) {
        return ResponseEntity.ok(doctorService.getDoctorById(did));
    }

    @PostMapping(value = "/doctors")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.createDoctor(doctor));
    }

    @PutMapping(value = "/doctor/{did}")
    public ResponseEntity<Optional<Doctor>> updateDoctorById(@PathVariable String did,
                                                             @RequestBody Doctor doctor) {
        Long tempId = Long.valueOf(did);
        Optional<Doctor> doctorTemp = doctorService.getDoctorById(tempId);
        Doctor actualData = doctorTemp.get();
        if (doctor.getDoctorName() != null) {
            actualData.setDoctorName(doctor.getDoctorName());
        }
        if (doctor.getDoctorSpecialty() != null) {
            actualData.setDoctorSpecialty(doctor.getDoctorSpecialty());
        }

        doctorService.createDoctor(actualData);
        return ResponseEntity.ok(Optional.ofNullable(doctorService.createDoctor(actualData)));

    }

    @DeleteMapping(value = "/doctor/{id}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable String doctorId) {
        Long tempId = Long.valueOf(doctorId);
        doctorService.deleteDoctorById(tempId);
        return ResponseEntity.ok("patient Id" + doctorId + "deleted");


    }
}

