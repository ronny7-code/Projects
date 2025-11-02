package org.bj.HospitalManagementSystem;

import org.bj.HospitalManagementSystem.dto.bloodGroupCountResponseEntity;
import org.bj.HospitalManagementSystem.entity.Patient;
import org.bj.HospitalManagementSystem.repository.PatientRepository;
import org.bj.HospitalManagementSystem.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientRepository(){
        List<Patient> patients = patientRepository.findAllPatientWithAppointment();
        for(Patient patient : patients){
            System.out.println(patient);
        }
    }

    @Test
    public void testTransactionMethods(){
//        Patient patient = patientService.getPatientById(1);
//        System.out.println(patient);

//        Patient patient = patientRepository.findByName("Cristiano Ronaldo");
//        System.out.println(patient);
//        Patient patient1 = patientRepository.findByBirthDate(LocalDate.of(2062, 10, 22));
//        System.out.println(patient1);

//        List<Patient> patientList = patientRepository.findByBloodGroup(BloodGroup.O_POSITIVE);
//        for(Patient patient : patientList){
//            System.out.println(patient);
//        }
//        List<Patient> patientList = patientRepository.findByBornAfterDate(LocalDate.of(2010, 1, 1));
//        for(Patient patient : patientList){
//            System.out.println(patient);
//        }
        List<bloodGroupCountResponseEntity> bloodGroupList = patientRepository.countEachBloodGroupType();
        for(bloodGroupCountResponseEntity patient : bloodGroupList){
            System.out.println(patient);
        }

//        List<Patient> patients = patientRepository.findAllPatients();
//        for(Patient patient: patients){
//            System.out.println(patient);
//        }

//        int rowsAffected = patientRepository.updateNameWithId("Bijay Tamang", 1);
//        System.out.println("Successful! " + rowsAffected);
    }
}