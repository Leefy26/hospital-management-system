package com.hospital.system.controller;

import com.hospital.system.entity.Patient;
import com.hospital.system.service.PatientService;
import com.hospital.system.service.DischargeService;
import com.hospital.system.dto.DischargeSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.hospital.system.repository.PrescriptionRepository;
import com.hospital.system.repository.PatientLabTestRepository;
import com.hospital.system.entity.Prescription;
import com.hospital.system.entity.PatientLabTest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // 处理 GET /patient/list 请求，显示所有病人列表
    @GetMapping("/list")
    public String listPatients(Model model) {
        List<Patient> patientList = patientService.findAll();
        model.addAttribute("patientList", patientList);
        return "patient_list"; // 返回视图名 patient_list.jsp
    }

    @Autowired
    private DischargeService dischargeService;


    @GetMapping("/discharge/{id}")
    public String showDischargeSummary(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            DischargeSummaryDTO summary = dischargeService.getDischargeSummary(id);
            model.addAttribute("summary", summary);
            return "discharge_summary"; // 返回新的结算页面
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "获取结算信息失败: " + e.getMessage());
            return "redirect:/patient/list";
        }
    }

    @PostMapping("/discharge")
    public String confirmDischarge(@RequestParam Integer patientId, RedirectAttributes redirectAttributes) {
        try {
            dischargeService.dischargePatient(patientId);
            redirectAttributes.addFlashAttribute("successMessage", "病人ID为 " + patientId + " 的出院手续已成功办理！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "办理出院失败: " + e.getMessage());
        }
        return "redirect:/patient/list";
    }

    @Autowired
    private PrescriptionRepository prescriptionRepository; // 需要注入处方Repository
    @Autowired
    private PatientLabTestRepository patientLabTestRepository; // 需要注入检验单Repository

    @GetMapping("/details/{id}")
    public String viewPatientDetails(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Patient> patientOpt = patientService.findById(id);
        if (patientOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "找不到ID为 " + id + " 的病人。");
            return "redirect:/patient/list";
        }

        // 1. 获取病人基本信息
        Patient patient = patientOpt.get();

        // 2. 获取该病人的所有处方记录
        List<Prescription> prescriptions = prescriptionRepository.findByPatientId(id);

        // 3. 获取该病人的所有检验记录
        List<PatientLabTest> labTests = patientLabTestRepository.findByPatientId(id);

        BigDecimal totalPrescriptionFee = prescriptions.stream()
                .map(Prescription::getTotalFee)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalLabFee = labTests.stream()
                .map(PatientLabTest::getFee)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 4. 将所有信息放入模型，传递给视图
        model.addAttribute("patient", patient);
        model.addAttribute("prescriptions", prescriptions);
        model.addAttribute("labTests", labTests);
        model.addAttribute("totalLabFee", totalLabFee);
        model.addAttribute("totalPrescriptionFee", totalPrescriptionFee);

        return "patient_details"; // 返回新的详情页面
    }
}