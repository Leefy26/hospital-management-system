package com.hospital.system.controller;

import com.hospital.system.entity.Department;
import com.hospital.system.entity.Doctor;
import com.hospital.system.service.DepartmentService;
import com.hospital.system.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // 我们需要科室列表，所以也要注入DepartmentService
    @Autowired
    private DepartmentService departmentService;

    // 显示医生列表
    @GetMapping("/list")
    public String listDoctors(Model model) {
        List<Doctor> doctorList = doctorService.findAll();
        model.addAttribute("doctorList", doctorList);
        return "doctor_list";
    }

    // 显示新增医生表单
    @GetMapping("/add")
    public String showAddForm(Model model) {
        List<Department> departmentList = departmentService.findAll(); // 获取所有科室
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("departmentList", departmentList); // 将科室列表传给视图
        model.addAttribute("action", "add");
        return "doctor_form";
    }

    // 保存新增的医生
    @PostMapping("/add")
    public String addDoctor(@ModelAttribute Doctor doctor) {
        doctorService.save(doctor);
        return "redirect:/doctor/list";
    }

    // 显示修改医生表单
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Optional<Doctor> doctor = doctorService.findById(id);
        if (doctor.isPresent()) {
            List<Department> departmentList = departmentService.findAll(); // 同样需要科室列表
            model.addAttribute("doctor", doctor.get());
            model.addAttribute("departmentList", departmentList);
            model.addAttribute("action", "edit");
            return "doctor_form";
        }
        return "redirect:/doctor/list";
    }

    // 更新医生信息
    @PostMapping("/update")
    public String updateDoctor(@ModelAttribute Doctor doctor) {
        doctorService.save(doctor);
        return "redirect:/doctor/list";
    }

    // 删除医生
    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Integer id) {
        doctorService.deleteById(id);
        return "redirect:/doctor/list";
    }
}