package org.bj.HospitalManagementSystem.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bj.HospitalManagementSystem.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

//    @Transactional
//    @Override
//    public Patient getPatientById(long id) {
//        Patient p1 = patientRepository.findById(id).orElseThrow();
//        Patient p2 = patientRepository.findById(id).orElseThrow();
//        p1.setName("Cristiano Ronaldo");
//        return p1;
//    }
    @Transactional
    public void removePatient(Long patientId){
        patientRepository.deleteById(patientId);
    }
}