package com.hospital.system.repository;

import com.hospital.system.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, Integer> {
    @Query("SELECT w FROM Ward w WHERE w.usedBeds < w.totalBeds")
    List<Ward> findAvailableWards();
}