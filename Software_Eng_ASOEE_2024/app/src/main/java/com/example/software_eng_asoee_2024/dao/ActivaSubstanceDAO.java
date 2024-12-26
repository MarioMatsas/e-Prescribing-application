package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Doctor;

import java.util.List;

public interface ActivaSubstanceDAO {
    void delete(ActiveSubstance entity);

    List<ActiveSubstance> findAll();

    void save(ActiveSubstance entity);

    ActiveSubstance find(String name);
}
