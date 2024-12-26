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

    public PharmacudicalProductDAOMemory(){
        ActiveSubstanceDAOMemory mem = new ActiveSubstanceDAOMemory();
        ArrayList<ActiveSubstance> as = new ArrayList<ActiveSubstance>();
        ArrayList<ActiveSubstance> as2 = new ArrayList<ActiveSubstance>();
        as.add(mem.find("Paracetamol"));
        as.add(mem.find("Diddy Juice"));
        as2.add(mem.find("Diddy Juice"));
        save(new PharmacudicalProduct("Product1", 600, Form.PILL, MedicineType.GENERIC, as, "8 pills in pack"));
        save(new PharmacudicalProduct("Product2", 300, Form.PILL, MedicineType.GENERIC, as2, "4 pills in pack"));
    }

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

    public List<PharmacudicalProduct> findPharmacudicalProductByName(String name){
        ArrayList<PharmacudicalProduct> result = new ArrayList<PharmacudicalProduct>();
        for (PharmacudicalProduct pr : entities){
            if (pr.getName().equals(name)){
                result.add(pr);
            }
        }
        return result;
    }
}
