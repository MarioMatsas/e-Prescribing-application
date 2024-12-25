package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.PharmacudicalProduct;

import java.util.List;

public interface PharmacudicalProductDAO {
    void delete(PharmacudicalProduct entity);

    List<PharmacudicalProduct> findAll();

    void save(PharmacudicalProduct entity);

    List<PharmacudicalProduct> findPharmacudicalProductByName(String name);
}
