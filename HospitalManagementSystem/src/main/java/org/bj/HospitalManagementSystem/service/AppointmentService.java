package org.bj.HospitalManagementSystem.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bj.HospitalManagementSystem.entity.Appointment;
import org.bj.HospitalManagementSystem.entity.Doctor;
import org.bj.HospitalManagementSystem.entity.Patient;
import org.bj.HospitalManagementSystem.repository.AppointmentRepository;
import org.bj.HospitalManagementSystem.repository.DoctorRepository;
import org.bj.HospitalManagementSystem.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long doctorId, Long patientId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have id ");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment); // To maintain bidirectional consistency

        return  appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment reassignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId){

        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor); // This will automatically call the update operation
        doctor.getAppointments().add(appointment); // To maintain bidirectional consistency
        return appointment;
    }
}