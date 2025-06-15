package com.hospital.system.service.impl;

import com.hospital.system.entity.User;
import com.hospital.system.repository.UserRepository;
import com.hospital.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // 添加依赖后，这里就不会报错了
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // 我们依然需要用它来校验加密后的密码，这非常重要！
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Optional<User> login(String username, String password) {
        // 从数据库根据用户名查找用户
        Optional<User> userOpt = userRepository.findByUsername(username);

        // 如果找到了用户，并且输入的密码(password)与数据库中加密的密码(userOpt.get().getPassword())匹配
        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
            return userOpt; // 验证成功，返回该用户
        }

        return Optional.empty(); // 验证失败，返回空
    }
}