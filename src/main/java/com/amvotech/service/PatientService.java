package com.amvotech.service;

import com.amvotech.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    public int getNumberOfPatients();

    public Patient safePatient(Patient patient);

    public List<Patient> getAllPatient();

    public Optional<Patient> getPatientById(Long patientId);

    public void deletePatientById(Long patientId);
}
