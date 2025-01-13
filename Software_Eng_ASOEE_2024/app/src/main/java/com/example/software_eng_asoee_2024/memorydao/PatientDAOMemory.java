package com.example.software_eng_asoee_2024.memorydao;

import com.example.software_eng_asoee_2024.dao.PatientDAO;
import com.example.software_eng_asoee_2024.domain.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientDAOMemory implements PatientDAO {
    static ArrayList<Patient> entities = new ArrayList<Patient>();

    /**
     * Βγάζει από τον διαθέσιμο ασθενή από την λίστα.
     * @param entity γιατρός προς αφαίρεση
     */
    public void delete(Patient entity) {
        entities.remove(entity);
    }

    /**
     * Βρίσκει και επιστρέφει όλους τους διαθέσιμους ασθενείς.
     * @return επιστρέφει όλους τους διαθέσιμους ασθενείς
     */
    public List<Patient> findAll() {
        ArrayList<Patient> result = new ArrayList<Patient>();
        result.addAll(entities);
        return result;
    }

    /**
     * Αποθηκεύει τον ασθενή στην λίστα με τους διαθέσιμους.
     * @param entity ασθενής που είναι να αποθηκευτεί
     */
    public void save(Patient entity) {
        entities.add(entity);
    }

    /**
     * Βρίσκει τον ασθενή με συγκερκιμένο ΑΜΚΑ.
     * Αν δεν τον βρεί, επιστρέφει null.
     * @param SSN το ΑΜΚΑ του ασθενή που θέλουμε να βρούμε
     * @return επιστρέφει τον ασθενή
     */
    public Patient find(int SSN) {
        for (Patient patient : entities)
            if (patient.getSSN() == SSN)
                return patient;
        // Not found
        return null;
    }
}
