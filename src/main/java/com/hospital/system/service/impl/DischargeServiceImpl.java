package com.hospital.system.service.impl;

import com.hospital.system.entity.Patient;
import com.hospital.system.entity.PatientLabTest;
import com.hospital.system.entity.Prescription;
import com.hospital.system.entity.Ward;
import com.hospital.system.repository.PatientLabTestRepository;
import com.hospital.system.repository.PatientRepository;
import com.hospital.system.repository.WardRepository;
import com.hospital.system.repository.PrescriptionRepository;
import com.hospital.system.service.DischargeService;
import com.hospital.system.dto.DischargeSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DischargeServiceImpl implements DischargeService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private PatientLabTestRepository patientLabTestRepository;


    @Override
    @Transactional // 开启事务，保证数据一致性
    public void dischargePatient(Integer patientId) {
        // 1. 查找病人，如果找不到或病人已出院，则抛出异常
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("无效的病人ID: " + patientId));

        if (!"住院".equals(patient.getStatus())) {
            throw new IllegalStateException("病人 " + patient.getName() + " 已是出院状态，无法重复办理。");
        }

        // 2. 查找病人所在的病房
        Ward ward = patient.getWard();
        if (ward == null) {
            throw new IllegalStateException("病人数据异常，未关联病房。");
        }

        // 3. 更新病人信息
        patient.setStatus("出院");
        patient.setDischargeDate(LocalDateTime.now());
        patientRepository.save(patient);

        // 4. 更新病房床位信息
        int usedBeds = ward.getUsedBeds();
        if (usedBeds > 0) {
            ward.setUsedBeds(usedBeds - 1);
            wardRepository.save(ward);
        }
    }


    @Override
    public DischargeSummaryDTO getDischargeSummary(Integer patientId) {
        // 1. 查找病人
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("无效的病人ID: " + patientId));

        // 2. 查找该病人的所有处方
        List<Prescription> prescriptions = prescriptionRepository.findByPatientId(patientId);
        // 3. 查找该病人的所有检验单
        List<PatientLabTest> labTests = patientLabTestRepository.findByPatientId(patientId);

        // 4. 计算处方总费用
        BigDecimal prescriptionFee = prescriptions.stream()
                .map(Prescription::getTotalFee)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 5. 计算检验总费用
        BigDecimal labFee = labTests.stream()
                .map(PatientLabTest::getFee)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 6. 计算总费用
        BigDecimal totalFee = prescriptionFee.add(labFee);

        // 7. 打包成DTO返回
        DischargeSummaryDTO summary = new DischargeSummaryDTO();
        summary.setPatient(patient);
        summary.setPrescriptions(prescriptions);
        summary.setLabTests(labTests);
        summary.setTotalFee(totalFee); // 确保这里设置的是计算后的总和

        return summary;
    }
}