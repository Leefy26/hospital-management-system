package com.hospital.system.controller;

import com.hospital.system.entity.Department;
import com.hospital.system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/list")
    public String listDepartments(Model model) {
        List<Department> departmentList = departmentService.findAll();
        model.addAttribute("departmentList", departmentList);
        return "department_list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("action", "add");
        return "department_form";
    }

    @PostMapping("/add")
    public String addDepartment(@ModelAttribute Department department) {
        departmentService.save(department);
        return "redirect:/department/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Optional<Department> department = departmentService.findById(id);
        if (department.isPresent()) {
            model.addAttribute("department", department.get());
            model.addAttribute("action", "edit");
            return "department_form";
        }
        return "redirect:/department/list";
    }

    @PostMapping("/update")
    public String updateDepartment(@ModelAttribute Department department) {
        departmentService.save(department);
        return "redirect:/department/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Integer id) {
        departmentService.deleteById(id);
        return "redirect:/department/list";
    }
}