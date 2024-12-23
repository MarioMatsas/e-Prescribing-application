package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Prescription;

import java.util.List;

public interface PrescriptionDAO {
    void delete(Prescription entity);

    List<Prescription> findAll();

    void save(Prescription entity);

    List<Prescription> findPrescriptionByPatient(Patient patient);
}
