package com.hospital.system.repository;

import com.hospital.system.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    List<Patient> findByDoctorId(Integer doctorId);
}