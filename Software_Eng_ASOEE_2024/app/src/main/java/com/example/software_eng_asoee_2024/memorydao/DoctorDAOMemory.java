package com.example.software_eng_asoee_2024.memorydao;

import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.dao.DoctorDAO;

import java.util.ArrayList;
import java.util.List;

public class DoctorDAOMemory implements DoctorDAO {

    static ArrayList<Doctor> entities = new ArrayList<Doctor>();

    /**
     * Βγάζει από τους διαθέσιμους γιατρούς τον συγκεκριμένο.
     * @param entity γιατρός προς αφαίρεση
     */
    public void delete(Doctor entity) {
        entities.remove(entity);
    }

    /**
     * Βρίσκει και επιστρέφει όλους τους διαθέσιμους γιατρούς.
     * @return επιστρέφει όλους τους διαθέσιμους γιατρούς
     */
    public List<Doctor> findAll() {
        ArrayList<Doctor> result = new ArrayList<Doctor>();
        result.addAll(entities);
        return result;
    }

    /**
     * Αποθηκεύει τον γιατρό στην λίστα με τους διαθέσιμους.
     * @param entity γιατρός που είναι να αποθηκευτεί
     */
    public void save(Doctor entity) {
        entities.add(entity);
    }

    /**
     * Βρίσκει τον γιατρό με συγκερκιμένο όνομα και επίθετο.
     * Αν δεν τον βρεί, επιστρέφει null
     * @param firstName το όνομα του γιατρού που θέλουμε να βρούμε
     * @param lastName το επώνυμο του γιατρού που θέλουμε να βρούμε
     * @return επιστρέφει τον γιατρό
     */
    public Doctor find(String firstName, String lastName) {
        for (Doctor doctor : entities)
            if (doctor.getFirstName().equals(firstName) && doctor.getLastName().equals(lastName))
                return doctor;
        // Not found
        return null;
    }
}
