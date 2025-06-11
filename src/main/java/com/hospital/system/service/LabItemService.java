package com.hospital.system.service;
import com.hospital.system.entity.LabItem;
import java.util.List;
import java.util.Optional;

public interface LabItemService {
    List<LabItem> findAll();
    Optional<LabItem> findById(Integer id);
    LabItem save(LabItem labItem);
    void deleteById(Integer id);
}