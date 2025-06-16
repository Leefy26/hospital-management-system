package com.hospital.system.repository;

import com.hospital.system.entity.PatientLabTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientLabTestRepository extends JpaRepository<PatientLabTest, Integer> {
    List<PatientLabTest> findByPatientId(Integer patientId);
}