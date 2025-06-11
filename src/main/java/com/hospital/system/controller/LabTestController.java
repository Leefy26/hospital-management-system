package com.hospital.system.controller;

import com.hospital.system.entity.Patient;
import com.hospital.system.service.LabTestService;
import com.hospital.system.service.PatientService;
import com.hospital.system.service.DoctorService;
import com.hospital.system.service.LabItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
@RequestMapping("/labtest")
public class LabTestController {

    @Autowired private LabTestService labTestService;
    @Autowired private PatientService patientService;
    @Autowired private DoctorService doctorService;
    @Autowired private LabItemService labItemService;

    @GetMapping("/new/{patientId}")
    public String showOrderForm(@PathVariable Integer patientId, Model model, RedirectAttributes redirectAttributes) {
        Optional<Patient> patientOpt = patientService.findById(patientId);
        if (patientOpt.isEmpty()) {
            return "redirect:/patient/list";
        }

        // 【新增的后台校验逻辑】
        Patient patient = patientOpt.get();
        if ("出院".equals(patient.getStatus())) {
            redirectAttributes.addFlashAttribute("errorMessage", "操作失败：病人 " + patient.getName() + " 已出院，无法为其开立新检验单。");
            return "redirect:/patient/list";
        }
        model.addAttribute("patient", patientService.findById(patientId).get());
        model.addAttribute("doctorList", doctorService.findAll());
        model.addAttribute("labItemList", labItemService.findAll());
        return "lab_test_order_form"; // 需要创建这个JSP页面
    }

    @PostMapping("/order")
    public String processOrder(@RequestParam Integer patientId, @RequestParam Integer doctorId, @RequestParam Integer labItemId, RedirectAttributes redirectAttributes) {
        try {
            labTestService.orderTest(patientId, doctorId, labItemId);
            redirectAttributes.addFlashAttribute("successMessage", "检验项目开立成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "操作失败: " + e.getMessage());
        }
        return "redirect:/patient/list";
    }
}