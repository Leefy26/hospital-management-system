package com.hospital.system.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String title; // 职称

    // 多对一关系：多个医生可以属于一个科室
    @ManyToOne(fetch = FetchType.LAZY) // LAZY表示懒加载，性能更好
    @JoinColumn(name = "department_id", nullable = false) // 通过 department_id 字段进行关联
    private Department department;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}