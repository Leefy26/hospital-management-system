package com.hospital.system.controller;

import com.hospital.system.entity.Doctor;
import com.hospital.system.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// 【修改点1】我们将基础路径改成一个更简单、更独特的路径
@RequestMapping("/api-test")
public class DoctorApiController {

    @Autowired
    private DoctorRepository doctorRepository;

    /**
     * 根据科室ID，获取该科室下的所有医生列表
     * @param departmentId 科室ID
     * @return 医生列表的JSON数据
     */
    // 【修改点2】我们将方法路径也简化
    @GetMapping("/get-doctors/{departmentId}")
    public List<Doctor> getDoctorsByDepartment(@PathVariable Integer departmentId) {
        return doctorRepository.findByDepartmentId(departmentId);
    }
}