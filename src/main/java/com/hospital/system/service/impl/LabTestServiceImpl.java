package com.hospital.system.service.impl;

import com.hospital.system.repository.LabItemRepository;
import com.hospital.system.repository.PatientLabTestRepository;
import com.hospital.system.repository.PatientRepository;
import com.hospital.system.repository.DoctorRepository;
import com.hospital.system.entity.Patient;
import com.hospital.system.entity.Doctor;
import com.hospital.system.entity.LabItem;
import com.hospital.system.entity.PatientLabTest;
import com.hospital.system.service.LabTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;


@Service
public class LabTestServiceImpl implements LabTestService {
    @Autowired private PatientLabTestRepository patientLabTestRepository;
    @Autowired private PatientRepository patientRepository;
    @Autowired private DoctorRepository doctorRepository;
    @Autowired private LabItemRepository labItemRepository;

    @Override
    @Transactional
    public void orderTest(Integer patientId, Integer doctorId, Integer labItemId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new IllegalArgumentException("无效的病人ID"));
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new IllegalArgumentException("无效的医生ID"));
        LabItem labItem = labItemRepository.findById(labItemId).orElseThrow(() -> new IllegalArgumentException("无效的检验项目ID"));

        PatientLabTest newTest = new PatientLabTest();
        newTest.setPatient(patient);
        newTest.setDoctor(doctor);
        newTest.setLabItem(labItem);
        newTest.setFee(labItem.getPrice()); // 从检验项目中复制价格
        newTest.setTestTime(LocalDateTime.now());

        patientLabTestRepository.save(newTest);
    }
}