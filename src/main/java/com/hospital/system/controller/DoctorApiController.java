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
@RequestMapping("/api-test")
public class DoctorApiController {

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/get-doctors/{departmentId}")
    public List<Doctor> getDoctorsByDepartment(@PathVariable Integer departmentId) {
        return doctorRepository.findByDepartmentId(departmentId);
    }
}