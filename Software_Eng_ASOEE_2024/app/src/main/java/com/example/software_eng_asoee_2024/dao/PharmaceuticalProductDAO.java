package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;

import java.util.List;

public interface PharmaceuticalProductDAO {
    void delete(PharmaceuticalProduct entity);

    List<PharmaceuticalProduct> findAll();

    void save(PharmaceuticalProduct entity);
    public void edit(PharmaceuticalProduct edit, PharmaceuticalProduct editTo);

    PharmaceuticalProduct find(String name);
}
