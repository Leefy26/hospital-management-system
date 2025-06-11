package com.hospital.system.service.impl;

import com.hospital.system.entity.*;
import com.hospital.system.repository.DoctorRepository;
import com.hospital.system.repository.MedicineRepository;
import com.hospital.system.repository.PatientRepository;
import com.hospital.system.repository.PrescriptionRepository;
import com.hospital.system.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    @Transactional // 开启事务！这是保证数据一致性的关键！
    public void createPrescription(Integer patientId, Integer doctorId, String diagnosis,
                                   Integer[] medicineIds, Integer[] quantities, String[] notes) {

        // 1. 创建一张新的处方主记录
        Prescription prescription = new Prescription();

        // 2. 关联病人和医生
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("无效的病人ID: " + patientId));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("无效的医生ID: " + doctorId));

        prescription.setPatient(patient);
        prescription.setDoctor(doctor);
        prescription.setDiagnosis(diagnosis);
        prescription.setCreatedAt(LocalDateTime.now());

        // 3. 循环处理处方中的每一项药品
        BigDecimal totalFee = BigDecimal.ZERO; // 使用BigDecimal处理金额，保证精度
        List<PrescriptionDetail> details = new ArrayList<>();

        for (int i = 0; i < medicineIds.length; i++) {
            Integer medicineId = medicineIds[i];
            Integer quantity = quantities[i];

            // 根据药品ID从数据库中查找药品信息
            Medicine medicine = medicineRepository.findById(medicineId)
                    .orElseThrow(() -> new IllegalArgumentException("无效的药品ID: " + medicineId));

            // 计算该项药品的小计金额
            BigDecimal subtotal = medicine.getUnitPrice().multiply(new BigDecimal(quantity));

            // 创建处方详情记录
            PrescriptionDetail detail = new PrescriptionDetail();
            detail.setMedicine(medicine);
            detail.setQuantity(quantity);
            detail.setNotes(notes[i]);
            detail.setSubtotal(subtotal);
            detail.setPrescription(prescription); // 建立详情与主记录的双向关联

            details.add(detail);

            // 累加总金额
            totalFee = totalFee.add(subtotal);
        }

        // 4. 设置处方详情列表和总金额
        prescription.setDetails(details);
        prescription.setTotalFee(totalFee);

        // 5. 保存处方
        // 因为我们在 Prescription 实体中设置了 CascadeType.ALL，
        // 所以这里只需要保存主记录，JPA会自动帮我们把所有的详情记录一并保存！
        prescriptionRepository.save(prescription);
    }
}