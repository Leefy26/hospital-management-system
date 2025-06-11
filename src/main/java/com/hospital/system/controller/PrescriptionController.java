package com.hospital.system.controller;

import com.hospital.system.entity.Doctor;
import com.hospital.system.entity.Medicine;
import com.hospital.system.entity.Patient;
import com.hospital.system.service.DoctorService;
import com.hospital.system.service.MedicineService;
import com.hospital.system.service.PatientService;
import com.hospital.system.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/prescription")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private MedicineService medicineService;

    /**
     * 显示为指定病人开立新处方的表单
     */
    @GetMapping("/new/{patientId}")
    public String showNewPrescriptionForm(@PathVariable Integer patientId,
                                          Model model,
                                          RedirectAttributes redirectAttributes) {
        // 1. 获取病人信息
        Optional<Patient> patientOpt = patientService.findById(patientId);
        if (patientOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "操作失败：未找到指定病人。");
            return "redirect:/patient/list";
        }

        Patient patient = patientOpt.get();
        // 【新增的后台校验逻辑】
        if ("出院".equals(patient.getStatus())) {
            redirectAttributes.addFlashAttribute("errorMessage", "操作失败：病人 " + patient.getName() + " 已出院，无法为其开立新处方。");
            return "redirect:/patient/list";
        }

        // 2. 获取所有医生和药品的列表，用于下拉选择
        List<Doctor> doctorList = doctorService.findAll();
        List<Medicine> medicineList = medicineService.findAll();

        // 3. 将数据放入模型，传递给视图
        model.addAttribute("patient", patient);
        model.addAttribute("doctorList", doctorList);
        model.addAttribute("medicineList", medicineList);

        return "prescription_form";
    }


    /**
     * 保存新开立的处方
     */
    @PostMapping("/save")
    public String savePrescription(@RequestParam Integer patientId,
                                   @RequestParam Integer doctorId,
                                   @RequestParam String diagnosis,
                                   @RequestParam Integer[] medicineIds,
                                   @RequestParam Integer[] quantities,
                                   @RequestParam String[] notes,
                                   RedirectAttributes redirectAttributes) {
        try {
            prescriptionService.createPrescription(patientId, doctorId, diagnosis, medicineIds, quantities, notes);
            redirectAttributes.addFlashAttribute("successMessage", "处方开立成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "开立处方失败: " + e.getMessage());
            // 如果失败，最好能重定向回原来的开方页面
            return "redirect:/prescription/new/" + patientId;
        }

        // 成功后，重定向到病人列表页
        return "redirect:/patient/list";
    }
}