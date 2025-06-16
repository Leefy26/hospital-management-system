package com.hospital.system.service;

public interface PrescriptionService {
    void createPrescription(Integer patientId, Integer doctorId, String diagnosis,
                            Integer[] medicineIds, Integer[] quantities, String[] notes);
}