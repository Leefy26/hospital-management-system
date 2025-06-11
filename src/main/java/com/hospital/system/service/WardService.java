package com.hospital.system.service;

import com.hospital.system.entity.Ward;
import java.util.List;
import java.util.Optional;

public interface WardService {
    List<Ward> findAll();
    Optional<Ward> findById(Integer id);
    Ward save(Ward ward);
    void deleteById(Integer id);
    List<Ward> findAvailableWards();
}