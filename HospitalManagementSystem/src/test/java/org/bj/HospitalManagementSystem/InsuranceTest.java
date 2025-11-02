package org.bj.HospitalManagementSystem;

import org.bj.HospitalManagementSystem.entity.Appointment;
import org.bj.HospitalManagementSystem.entity.Insurance;
import org.bj.HospitalManagementSystem.entity.Patient;
import org.bj.HospitalManagementSystem.service.AppointmentService;
import org.bj.HospitalManagementSystem.service.InsuranceService;
import org.bj.HospitalManagementSystem.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTest {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Test
    public void insurance(){

        Insurance insurance = Insurance.builder()
                .policyNumber("Siddhartha_7777")
                .provider("Siddhartha Bank").
                validUntil(LocalDate.of(2030, 12, 12)).
                build();
        Patient patient = insuranceService.assignInsuranceToPatient(insurance, 1L);
        System.out.println(patient);
        Patient newPatient = insuranceService.disassociateInsuranceFromPatient(patient.getId());
        System.out.println(newPatient);
    }

    @Test
    public void createAppointment(){
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025, 11, 1, 14, 0, 0))
                .reason("Cancer").build();
        Appointment newAppointment = appointmentService.createNewAppointment(appointment, 1L, 2L);
        System.out.println(newAppointment);

        var updatedAppointment = appointmentService.reassignAppointmentToAnotherDoctor(newAppointment.getId(), 3L);
        System.out.println(updatedAppointment);
    }

    @Test
    public void creatingAndDeletingAppointmentsForOnePatient(){
        Appointment appointment1 = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025, 11, 1, 14, 0, 0))
                .reason("Cancer").build();
        Appointment appointment2 = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025, 12, 3, 12, 0, 0))
                .reason("Alzheimer").build();
        Appointment appointment3 = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2026, 1, 1, 16, 0, 0))
                .reason("Pneumonia").build();
        Appointment newAppointment1 = appointmentService.createNewAppointment(appointment1, 2L, 1L);
        Appointment newAppointment2 = appointmentService.createNewAppointment(appointment2, 1L, 1L);
        Appointment newAppointment3 = appointmentService.createNewAppointment(appointment3, 3L, 1L);
        System.out.println(newAppointment1);
        System.out.println(newAppointment2);
        System.out.println(newAppointment3);

        patientService.removePatient(1L);

    }
}