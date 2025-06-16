package com.hospital.system.controller;

import com.hospital.system.entity.*;
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

import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;
import com.hospital.system.repository.DoctorRepository;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Optional;
@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/list")
    public String listPatients(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/login";
        }

        List<Patient> patientList;

        if ("ROLE_DOCTOR".equals(loggedInUser.getRole())) {
            Optional<Doctor> doctorOpt = doctorRepository.findByUserId(loggedInUser.getId());

            if (doctorOpt.isPresent()) {
                Doctor doctor = doctorOpt.get();

                model.addAttribute("currentDoctor", doctor);

                patientList = patientService.findByDoctorId(doctor.getId());
            } else {
                patientList = new ArrayList<>();
                model.addAttribute("errorMessage", "未找到与您账户关联的医生信息！");
            }
        } else if ("ROLE_ADMIN".equals(loggedInUser.getRole())) {
            patientList = patientService.findAll();
        } else {
            patientList = new ArrayList<>();
        }

        model.addAttribute("patientList", patientList);
        return "patient_list";
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

        Patient patient = patientOpt.get();

        List<Prescription> prescriptions = prescriptionRepository.findByPatientId(id);

        List<PatientLabTest> labTests = patientLabTestRepository.findByPatientId(id);

        BigDecimal totalPrescriptionFee = prescriptions.stream()
                .map(Prescription::getTotalFee)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalLabFee = labTests.stream()
                .map(PatientLabTest::getFee)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("patient", patient);
        model.addAttribute("prescriptions", prescriptions);
        model.addAttribute("labTests", labTests);
        model.addAttribute("totalLabFee", totalLabFee);
        model.addAttribute("totalPrescriptionFee", totalPrescriptionFee);

        return "patient_details"; // 返回新的详情页面
    }
}