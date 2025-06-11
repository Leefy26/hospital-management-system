package com.hospital.system.service.impl;

import com.hospital.system.entity.Department;
import com.hospital.system.entity.Doctor;
import com.hospital.system.entity.Patient;
import com.hospital.system.entity.Ward;
import com.hospital.system.repository.DepartmentRepository;
import com.hospital.system.repository.DoctorRepository;
import com.hospital.system.repository.PatientRepository;
import com.hospital.system.repository.WardRepository;
import com.hospital.system.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AdmissionServiceImpl implements AdmissionService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private WardRepository wardRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    // @Transactional 注解！保证这个方法里的所有数据库操作是一个整体事务。
    @Override
    @Transactional
    public Patient registerPatient(Patient patient, Integer departmentId, Integer doctorId, Integer wardId) {
        // 1. 查找相关的实体
        Ward ward = wardRepository.findById(wardId)
                .orElseThrow(() -> new IllegalArgumentException("无效的病房ID: " + wardId));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("无效的医生ID: " + doctorId));
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("无效的科室ID: " + departmentId));

        // 2. 检查是否有空床位
        if (ward.getUsedBeds() >= ward.getTotalBeds()) {
            throw new IllegalStateException("病房 " + ward.getWardNo() + " 已满，无可用床位。");
        }

        // 3. 更新病房信息：已用床位数 +1
        ward.setUsedBeds(ward.getUsedBeds() + 1);
        wardRepository.save(ward);

        // 4. 完善病人信息
        patient.setDepartment(department);
        patient.setDoctor(doctor);
        patient.setWard(ward);
        patient.setAdmissionDate(LocalDateTime.now()); // 设置入院时间为当前时间
        patient.setStatus("住院"); // 设置状态为“住院”

        // 5. 保存新的病人记录
        return patientRepository.save(patient);
    }
}