package com.amvotech.service.impl;

import com.amvotech.dao.DoctorRepository;
import com.amvotech.dao.PatientRepository;
import com.amvotech.model.Doctor;
import com.amvotech.model.Patient;
import com.amvotech.service.DoctorService;
import com.amvotech.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Optional<Doctor> getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId);
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctorById(Long doctorId) {
        doctorRepository.deleteById(doctorId);

    }
}

