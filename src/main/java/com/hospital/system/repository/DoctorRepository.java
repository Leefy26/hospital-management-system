package com.hospital.system.repository;

import com.hospital.system.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    List<Doctor> findByDepartmentId(Integer departmentId);
    Optional<Doctor> findByUserId(Integer userId);
}