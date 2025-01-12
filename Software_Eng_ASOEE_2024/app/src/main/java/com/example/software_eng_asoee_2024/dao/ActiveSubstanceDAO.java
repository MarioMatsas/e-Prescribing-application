package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Doctor;

import java.util.List;

public interface ActiveSubstanceDAO {
    void delete(ActiveSubstance entity);

    List<ActiveSubstance> findAll();

    void save(ActiveSubstance entity);
    public void edit(ActiveSubstance edit, ActiveSubstance editTo);

    ActiveSubstance find(String name);
}
