package com.example.software_eng_asoee_2024.memorydao;

import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.dao.PharmacistDAO;

import java.util.ArrayList;
import java.util.List;

public class PharmacistDAOMemory implements PharmacistDAO {
    static ArrayList<Pharmacist> entities = new ArrayList<Pharmacist>();


    public void delete(Pharmacist entity) {
        entities.remove(entity);
    }

    public List<Pharmacist> findAll() {
        ArrayList<Pharmacist> result = new ArrayList<Pharmacist>();
        result.addAll(entities);
        return result;
    }

    public void save(Pharmacist entity) {
        entities.add(entity);
    }

    public Pharmacist find(String firstName, String lastName) {
        for (Pharmacist pharmacist : entities)
            if (pharmacist.getFirstName().equals(firstName) && pharmacist.getLastName().equals(lastName))
                return pharmacist;
        // Not found
        return null;
    }
}
