package com.hospital.system.service.impl;

import com.hospital.system.entity.Ward;
import com.hospital.system.repository.WardRepository;
import com.hospital.system.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WardServiceImpl implements WardService {

    @Autowired
    private WardRepository wardRepository;

    @Override
    public List<Ward> findAll() {
        return wardRepository.findAll();
    }

    @Override
    public Optional<Ward> findById(Integer id) {
        return wardRepository.findById(id);
    }

    @Override
    public Ward save(Ward ward) {
        return wardRepository.save(ward);
    }

    @Override
    public void deleteById(Integer id) {
        wardRepository.deleteById(id);
    }

    @Override
    public List<Ward> findAvailableWards() {
        return wardRepository.findAvailableWards();
    }
}