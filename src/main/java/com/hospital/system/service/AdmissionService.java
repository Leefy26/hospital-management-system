package com.hospital.system.service;

import com.hospital.system.entity.Patient;

public interface AdmissionService {
    // 定义一个方法，接收病人的基本信息，以及要分配的科室、医生和病房的ID
    Patient registerPatient(Patient patient, Integer departmentId, Integer doctorId, Integer wardId);
}