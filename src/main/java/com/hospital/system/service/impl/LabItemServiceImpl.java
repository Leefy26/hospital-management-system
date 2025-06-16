package com.hospital.system.service.impl;

import com.hospital.system.entity.LabItem;
import com.hospital.system.repository.LabItemRepository;
import com.hospital.system.service.LabItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LabItemServiceImpl implements LabItemService {

    @Autowired
    private LabItemRepository labItemRepository;


    @Override
    public List<LabItem> findAll() {
        return labItemRepository.findAll();
    }


    @Override
    public Optional<LabItem> findById(Integer id) {
        return labItemRepository.findById(id);
    }


    @Override
    public LabItem save(LabItem labItem) {
        return labItemRepository.save(labItem);
    }


    @Override
    public void deleteById(Integer id) {
        labItemRepository.deleteById(id);
    }
}