package com.hospital.system.service;
import com.hospital.system.dto.DischargeSummaryDTO;

public interface DischargeService {

    void dischargePatient(Integer patientId);

    DischargeSummaryDTO getDischargeSummary(Integer patientId);
}