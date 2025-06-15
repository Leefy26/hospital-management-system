package com.hospital.system.service;

import com.hospital.system.entity.Doctor;
import java.util.List;
import java.util.Optional;

public interface DoctorService {
    List<Doctor> findAll();
    Optional<Doctor> findById(Integer id);
    Doctor save(Doctor doctor);
    void deleteById(Integer id);
    void createDoctorAndUser(Doctor doctor, String username, String password);

}