package com.hospital.system.repository;

import com.hospital.system.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository 注解是Spring提供的一个语义化注解，表明这个接口是用于数据访问的。
@Repository
// 继承 JpaRepository<实体类, 主键类型> 后，我们就自动拥有了所有基础的CRUD方法。
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    // 是的，您没看错，一个方法都不用写！
    // save(), findById(), findAll(), deleteById() 等方法已经由Spring Data JPA在幕后为我们实现了。
    // 这就是现代框架的魅力！
}