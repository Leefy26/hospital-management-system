package com.hospital.system.service;
import com.hospital.system.dto.DischargeSummaryDTO;

public interface DischargeService {
    /**
     * 为指定ID的病人办理出院手续
     * @param patientId 病人ID
     */
    void dischargePatient(Integer patientId);

    DischargeSummaryDTO getDischargeSummary(Integer patientId);
}