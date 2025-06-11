package com.hospital.system.service;
public interface LabTestService {
    void orderTest(Integer patientId, Integer doctorId, Integer labItemId);
}