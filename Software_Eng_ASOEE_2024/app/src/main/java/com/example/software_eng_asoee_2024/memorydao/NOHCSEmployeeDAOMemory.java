package com.example.software_eng_asoee_2024.memorydao;

import com.example.software_eng_asoee_2024.dao.NOHCSEmployeeDAO;
import com.example.software_eng_asoee_2024.domain.NOHCS_Employee;

import java.util.ArrayList;
import java.util.List;

public class NOHCSEmployeeDAOMemory implements NOHCSEmployeeDAO {

    static ArrayList<NOHCS_Employee> entities = new ArrayList<NOHCS_Employee>();

    public void delete(NOHCS_Employee entity) {
        entities.remove(entity);
    }

    public List<NOHCS_Employee> findAll() {
        return new ArrayList<NOHCS_Employee>(entities);
    }

    public void save(NOHCS_Employee entity) {
        entities.add(entity);
    }

    public NOHCS_Employee find(String firstName, String lastName) {
        for (NOHCS_Employee employee : entities)
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName))
                return employee;
        // Not found
        return null;
    }
}
