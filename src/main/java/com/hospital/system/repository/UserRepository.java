package com.hospital.system.repository;

import com.hospital.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Spring Data JPA 会根据方法名自动生成查询
    // 我们需要一个根据用户名查找用户的方法
    Optional<User> findByUsername(String username);
}