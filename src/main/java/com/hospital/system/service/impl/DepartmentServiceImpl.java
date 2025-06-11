package com.hospital.system.service.impl;

import com.hospital.system.entity.Department;
import com.hospital.system.repository.DepartmentRepository;
import com.hospital.system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @Service 注解表明这是一个业务逻辑层的组件。
@Service
public class DepartmentServiceImpl implements DepartmentService {

    // @Autowired 注解让Spring自动将 DepartmentRepository 的实例注入到这里。
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> findById(Integer id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void deleteById(Integer id) {
        departmentRepository.deleteById(id);
    }
}