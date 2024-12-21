package com.example.software_eng_asoee_2024.dao;

import java.util.List;
import com.example.software_eng_asoee_2024.Doctor;

public interface DoctorDAO{
    void delete(Doctor entity);

    List<Doctor> findAll();

    void save(Doctor entity);

    Doctor find(String firstName, String lastName);
}
