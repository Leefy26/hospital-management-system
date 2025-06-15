package com.hospital.system.service;
import com.hospital.system.entity.User;
import java.util.Optional;

public interface UserService {
    // 根据用户名和密码进行登录验证
    Optional<User> login(String username, String password);
}