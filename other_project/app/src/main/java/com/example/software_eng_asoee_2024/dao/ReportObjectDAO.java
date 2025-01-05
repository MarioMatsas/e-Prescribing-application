package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;

import java.util.List;

public interface ReportObjectDAO {
    void delete(ActiveSubstance entity);

    List<ActiveSubstance> findAll();

    void save(ActiveSubstance entity);

    ActiveSubstance find(String name);
}
