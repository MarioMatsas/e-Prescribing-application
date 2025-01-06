package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.NOHCS_Employee;

import java.util.List;

public interface NOHCSEmployeeDAO {
    void delete(NOHCS_Employee entity);

    void save(NOHCS_Employee entity);

    List<NOHCS_Employee> findAll();

    NOHCS_Employee find(String firstName, String lastName);
}
