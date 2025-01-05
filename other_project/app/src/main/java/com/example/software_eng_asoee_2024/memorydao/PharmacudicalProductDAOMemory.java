package com.example.software_eng_asoee_2024.memorydao;

import com.example.software_eng_asoee_2024.dao.PharmacudicalProductDAO;
import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.MedicineType;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.PharmacudicalProduct;
import com.example.software_eng_asoee_2024.domain.Prescription;

import java.util.ArrayList;
import java.util.List;

public class PharmacudicalProductDAOMemory implements PharmacudicalProductDAO {
    static ArrayList<PharmacudicalProduct> entities = new ArrayList<PharmacudicalProduct>();

    public void delete(PharmacudicalProduct entity) {
        entities.remove(entity);
    }

    public List<PharmacudicalProduct> findAll() {
        ArrayList<PharmacudicalProduct> result = new ArrayList<PharmacudicalProduct>();
        result.addAll(entities);
        return result;
    }

    public void save(PharmacudicalProduct entity) {
        entities.add(entity);
    }

    public PharmacudicalProduct find(String name){
        for (PharmacudicalProduct pr : entities){
            if (pr.getName().equals(name)){
                return pr;
            }
        }
        return null;
    }
}
