package com.hospital.system.dto;

import com.hospital.system.entity.Patient;
import com.hospital.system.entity.PatientLabTest; // 【修正点1】引入 PatientLabTest
import com.hospital.system.entity.Prescription;

import java.math.BigDecimal;
import java.util.List;

public class DischargeSummaryDTO {

    private Patient patient;
    private List<Prescription> prescriptions;
    private List<PatientLabTest> labTests; // 【修正点2】添加 labTests 字段
    private BigDecimal totalFee;

    // --- Getters and Setters ---

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    // 【修正点3】为 labTests 添加 Getter 和 Setter
    public List<PatientLabTest> getLabTests() {
        return labTests;
    }

    public void setLabTests(List<PatientLabTest> labTests) {
        this.labTests = labTests;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }
}