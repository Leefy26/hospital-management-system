package com.hospital.system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @Entity 注解表明这是一个JPA实体类，它会映射到数据库中的一个表。
@Entity
// @Table 注解指定了它映射到哪张表，这里是 'departments' 表。
@Table(name = "departments")
public class Department {

    // @Id 表明这是主键字段。
    @Id
    // @GeneratedValue 定义了主键的生成策略，IDENTITY 表示由数据库自增生成。
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String location;

    private String tel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}