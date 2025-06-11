package com.hospital.system.service;

public interface PrescriptionService {

    /**
     * 创建一张新的处方
     * @param patientId 病人ID
     * @param doctorId 开方医生ID
     * @param diagnosis 临床诊断
     * @param medicineIds 药品ID数组
     * @param quantities 对应的药品数量数组
     * @param notes 对应的药品用法备注数组
     */
    void createPrescription(Integer patientId, Integer doctorId, String diagnosis,
                            Integer[] medicineIds, Integer[] quantities, String[] notes);
}