package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.Patient;

import java.util.List;

public interface PatientDAO {
    void delete(Patient entity);

    List<Patient> findAll();

    void save(Patient entity);

    Patient find(int SSN);
}
