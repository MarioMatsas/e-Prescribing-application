package com.example.software_eng_asoee_2024.memorydao;

import com.example.software_eng_asoee_2024.domain.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientDAOMemory {
    static ArrayList<Patient> entities = new ArrayList<Patient>();

    public PatientDAOMemory(){
        Patient p1 = new Patient("Tom", "Hobs", 123123123);
        save(p1);
    }

    public void delete(Patient entity) {
        entities.remove(entity);
    }

    public List<Patient> findAll() {
        ArrayList<Patient> result = new ArrayList<Patient>();
        result.addAll(entities);
        return result;
    }

    public void save(Patient entity) {
        entities.add(entity);
    }

    public Patient find(int SSN) {
        for (Patient patient : entities)
            if (patient.getSSN() == SSN)
                return patient;
        // Not found
        return null;
    }
}
