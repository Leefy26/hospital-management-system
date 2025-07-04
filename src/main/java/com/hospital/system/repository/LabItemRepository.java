package com.hospital.system.repository;

import com.hospital.system.entity.LabItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabItemRepository extends JpaRepository<LabItem, Integer> {
}