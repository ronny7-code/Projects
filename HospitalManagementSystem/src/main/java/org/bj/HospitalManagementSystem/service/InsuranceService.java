package org.bj.HospitalManagementSystem.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bj.HospitalManagementSystem.entity.Insurance;
import org.bj.HospitalManagementSystem.entity.Patient;
import org.bj.HospitalManagementSystem.repository.InsuranceRepository;
import org.bj.HospitalManagementSystem.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));
        patient.setInsurance(insurance);

        insurance.setPatient(patient); // To maintain bidirectional consistency
        return patient;
    }

    @Transactional
    public Patient disassociateInsuranceFromPatient(Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        patient.setInsurance(null);
        return patient;
    }

}