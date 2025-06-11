package com.hospital.system.service;

import com.hospital.system.entity.Department;
import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> findAll();
    Optional<Department> findById(Integer id);
    Department save(Department department);
    void deleteById(Integer id);
}