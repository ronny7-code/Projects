package org.bj.HospitalManagementSystem.repository;

import jakarta.transaction.Transactional;
import org.bj.HospitalManagementSystem.entity.BloodGroup;
import org.bj.HospitalManagementSystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.bj.HospitalManagementSystem.dto.bloodGroupCountResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByName(String name);

    Patient findByBirthDate(LocalDate date);

    @Query("SELECT p FROM Patient p WHERE p.bloodGroup = ?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroup bloodGroup);

    @Query("SELECT p FROM Patient p WHERE p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);

    @Query("SELECT new org.bj.HospitalManagementSystem.dto.bloodGroupCountResponseEntity(p.bloodGroup, Count(p)) FROM Patient p GROUP BY p.bloodGroup")
//    List<Object[]> findByCountEachBloodGroupType();
    List<bloodGroupCountResponseEntity> countEachBloodGroupType();

    @Query(value = "SELECT * FROM patient", nativeQuery = true)
    List<Patient> findAllPatients();

    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :name WHERE p.id = :id")
    int updateNameWithId(@Param("name") String name, @Param("id") long id);

    // @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments a LEFT JOIN FETCH a.doctor")
    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments")
    List<Patient> findAllPatientWithAppointment();
}