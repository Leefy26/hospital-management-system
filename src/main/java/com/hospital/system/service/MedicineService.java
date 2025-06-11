package com.hospital.system.service;

import com.hospital.system.entity.Medicine;
import java.util.List;
import java.util.Optional;

public interface MedicineService {
    List<Medicine> findAll();
    Optional<Medicine> findById(Integer id);
    Medicine save(Medicine department);
    void deleteById(Integer id);
}
