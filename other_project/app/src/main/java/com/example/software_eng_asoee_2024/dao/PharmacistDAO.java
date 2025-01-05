package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.Pharmacist;

import java.util.List;

public interface PharmacistDAO {
    void delete(Pharmacist entity);

    List<Pharmacist> findAll();

    void save(Pharmacist entity);

    Pharmacist find(String firstName, String lastName);
}
