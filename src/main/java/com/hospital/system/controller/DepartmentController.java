package com.hospital.system.controller;

import com.hospital.system.entity.Department;
import com.hospital.system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// @Controller 表明这是一个MVC控制器。
@Controller
// @RequestMapping("/department") 定义了这个控制器下所有方法的基础URL路径。
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // 处理 GET /department/list 请求，显示所有科室列表
    @GetMapping("/list")
    public String listDepartments(Model model) {
        List<Department> departmentList = departmentService.findAll();
        model.addAttribute("departmentList", departmentList);
        return "department_list"; // 返回视图名，会解析为 /WEB-INF/views/department_list.jsp
    }

    // 处理 GET /department/add 请求，显示新增科室的表单页面
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("action", "add"); // 用于表单区分是新增还是修改
        return "department_form";
    }

    // 处理 POST /department/add 请求，保存新增的科室信息
    @PostMapping("/add")
    public String addDepartment(@ModelAttribute Department department) {
        departmentService.save(department);
        return "redirect:/department/list"; // 保存后重定向到列表页面
    }

    // 处理 GET /department/edit/{id} 请求，显示修改科室的表单页面
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Optional<Department> department = departmentService.findById(id);
        if (department.isPresent()) {
            model.addAttribute("department", department.get());
            model.addAttribute("action", "edit");
            return "department_form";
        }
        return "redirect:/department/list"; // 如果找不到，则返回列表页
    }

    // 处理 POST /department/update 请求，更新科室信息
    @PostMapping("/update")
    public String updateDepartment(@ModelAttribute Department department) {
        departmentService.save(department); // save方法兼具新增和修改功能
        return "redirect:/department/list";
    }

    // 处理 GET /department/delete/{id} 请求，删除科室
    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Integer id) {
        departmentService.deleteById(id);
        return "redirect:/department/list";
    }
}