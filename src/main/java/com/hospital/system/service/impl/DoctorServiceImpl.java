package com.hospital.system.service.impl;

import com.hospital.system.entity.Doctor;
import com.hospital.system.entity.User;
import com.hospital.system.repository.DoctorRepository;
import com.hospital.system.repository.UserRepository;
import com.hospital.system.service.DoctorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // 【新增注入】
    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Optional<Doctor> findById(Integer id) {
        return doctorRepository.findById(id);
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteById(Integer id) {
        doctorRepository.deleteById(id);
    }

    // 【新增方法】
    @Override
    @Transactional // 开启事务，保证两步操作要么都成功，要么都失败
    public void createDoctorAndUser(Doctor doctor, String username, String password) {
        // 1. 检查用户名是否已存在
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("用户名 '" + username + "' 已被占用！");
        }

        // 2. 创建一个新的 User 对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // 加密密码
        user.setRole("ROLE_DOCTOR"); // 新建的都是医生角色
        user.setEnabled(true);
        User savedUser = userRepository.save(user); // 先保存User，获取ID

        // 3. 将新建的 User 关联到 Doctor 对象上
        doctor.setUser(savedUser);

        // 4. 保存 Doctor 对象
        doctorRepository.save(doctor);
    }
}