package com.hospital.system.controller;

import com.hospital.system.entity.Department;
import com.hospital.system.entity.Doctor;
import com.hospital.system.service.DepartmentService;
import com.hospital.system.service.DoctorService;
import com.hospital.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/list")
    public String listDoctors(Model model) {
        List<Doctor> doctorList = doctorService.findAll();
        model.addAttribute("doctorList", doctorList);
        return "doctor_list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        List<Department> departmentList = departmentService.findAll();
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("departmentList", departmentList);
        model.addAttribute("action", "add");
        return "doctor_form";
    }

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addDoctor(@ModelAttribute Doctor doctor,
                            @RequestParam String username,
                            @RequestParam String password,
                            RedirectAttributes redirectAttributes) {
        try {
            doctorService.createDoctorAndUser(doctor, username, password);
            redirectAttributes.addFlashAttribute("successMessage", "医生 " + doctor.getName() + " 及登录账户创建成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "创建失败: " + e.getMessage());
        }
        return "redirect:/doctor/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Optional<Doctor> doctor = doctorService.findById(id);
        if (doctor.isPresent()) {
            List<Department> departmentList = departmentService.findAll();
            model.addAttribute("doctor", doctor.get());
            model.addAttribute("departmentList", departmentList);
            model.addAttribute("action", "edit");
            return "doctor_form";
        }
        return "redirect:/doctor/list";
    }

    @PostMapping("/update")
    public String updateDoctor(@ModelAttribute Doctor doctor) {
        doctorService.save(doctor);
        return "redirect:/doctor/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Integer id) {
        doctorService.deleteById(id);
        return "redirect:/doctor/list";
    }
}