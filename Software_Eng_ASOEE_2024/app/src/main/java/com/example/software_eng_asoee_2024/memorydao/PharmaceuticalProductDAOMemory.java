package com.example.software_eng_asoee_2024.memorydao;

import com.example.software_eng_asoee_2024.dao.PharmaceuticalProductDAO;
import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.MedicineType;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.domain.Prescription;

import java.util.ArrayList;
import java.util.List;

public class PharmaceuticalProductDAOMemory implements PharmaceuticalProductDAO {
    static ArrayList<PharmaceuticalProduct> entities = new ArrayList<PharmaceuticalProduct>();

    public void delete(PharmaceuticalProduct entity) {
        entities.remove(entity);
    }

    public List<PharmaceuticalProduct> findAll() {
        ArrayList<PharmaceuticalProduct> result = new ArrayList<PharmaceuticalProduct>();
        result.addAll(entities);
        return result;
    }

    public void save(PharmaceuticalProduct entity) {
        entities.add(entity);
    }

    public PharmaceuticalProduct find(String name){
        for (PharmaceuticalProduct pr : entities){
            if (pr.getName().equals(name)){
                return pr;
            }
        }
        return null;
    }
}
