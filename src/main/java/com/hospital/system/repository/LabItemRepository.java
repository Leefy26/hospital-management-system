package com.hospital.system.repository;

import com.hospital.system.entity.LabItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 检验项目(LabItem)的数据访问接口
 */
@Repository
public interface LabItemRepository extends JpaRepository<LabItem, Integer> {
}