package com.hospital.system.controller;

import com.hospital.system.entity.Department;
import com.hospital.system.entity.Doctor;
import com.hospital.system.entity.Patient;
import com.hospital.system.entity.Ward;
import com.hospital.system.service.AdmissionService;
import com.hospital.system.service.DepartmentService;
import com.hospital.system.service.DoctorService;
import com.hospital.system.service.WardService; // 假设您已在WardService中添加了findAvailableWards方法
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admission")
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private WardService wardService;

    @GetMapping("/register")
    public String showAdmissionForm(Model model) {
        List<Department> departmentList = departmentService.findAll();
        List<Doctor> doctorList = doctorService.findAll();
        // List<Ward> availableWardList = wardService.findAvailableWards();
        List<Ward> availableWardList = wardService.findAll();

        model.addAttribute("patient", new Patient());
        model.addAttribute("departmentList", departmentList);
        model.addAttribute("doctorList", doctorList);
        model.addAttribute("wardList", availableWardList);
        return "admission_form";
    }

    @PostMapping("/register")
    public String processAdmission(@ModelAttribute Patient patient,
                                   @RequestParam Integer departmentId,
                                   @RequestParam Integer doctorId,
                                   @RequestParam Integer wardId,
                                   RedirectAttributes redirectAttributes) {
        try {
            admissionService.registerPatient(patient, departmentId, doctorId, wardId);
            redirectAttributes.addFlashAttribute("successMessage", "病人 " + patient.getName() + " 住院登记成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "登记失败: " + e.getMessage());
            return "redirect:/admission/register";
        }
        return "redirect:/admin/dashboard";
    }
}