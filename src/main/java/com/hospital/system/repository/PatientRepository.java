package com.hospital.system.repository;

import com.hospital.system.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    // 未来我们可能会在这里添加一些自定义的查询方法，但现在，基础功能足矣。
}