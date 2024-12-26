package com.example.software_eng_asoee_2024.memorydao;

import com.example.software_eng_asoee_2024.dao.ActivaSubstanceDAO;
import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Doctor;

import java.util.ArrayList;
import java.util.List;

public class ActiveSubstanceDAOMemory implements ActivaSubstanceDAO {
    static ArrayList<ActiveSubstance> entities = new ArrayList<ActiveSubstance>();

    public ActiveSubstanceDAOMemory(){
        save(new ActiveSubstance("Paracetamol", 20d));
        save(new ActiveSubstance("Diddy Juice", 20d));
    }

    public void delete(ActiveSubstance entity) {
        entities.remove(entity);
    }

    public List<ActiveSubstance> findAll() {
        ArrayList<ActiveSubstance> result = new ArrayList<ActiveSubstance>();
        result.addAll(entities);
        return result;
    }

    public void save(ActiveSubstance entity) {
        entities.add(entity);
    }

    public ActiveSubstance find(String name) {
        for (ActiveSubstance subst : entities)
            if (subst.getName().equals(name))
                return subst;
        // Not found
        return null;
    }
}
