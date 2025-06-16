package com.hospital.system.service;

import com.hospital.system.entity.Patient;

public interface AdmissionService {
    Patient registerPatient(Patient patient, Integer departmentId, Integer doctorId, Integer wardId);
}