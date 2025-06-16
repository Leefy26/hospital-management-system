package com.hospital.system.service;
import com.hospital.system.entity.User;
import java.util.Optional;

public interface UserService {
    Optional<User> login(String username, String password);
}