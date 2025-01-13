package com.example.software_eng_asoee_2024.memorydao;

import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.dao.PharmacistDAO;

import java.util.ArrayList;
import java.util.List;

public class PharmacistDAOMemory implements PharmacistDAO {
    static ArrayList<Pharmacist> entities = new ArrayList<Pharmacist>();

    /**
     * Βγάζει από τους διαθέσιμους φαρμακοποιούς τον συγκεκριμένο.
     * @param entity φαρμακοποιός προς αφαίρεση
     */
    public void delete(Pharmacist entity) {
        entities.remove(entity);
    }

    /**
     * Βρίσκει και επιστρέφει όλους τους διαθέσιμους φαρμακοποιούς.
     * @return επιστρέφει όλους τους διαθέσιμους φαρμακοποιούς.
     */
    public List<Pharmacist> findAll() {
        ArrayList<Pharmacist> result = new ArrayList<Pharmacist>();
        result.addAll(entities);
        return result;
    }

    /**
     * Αποθηκεύει τον φαρμακοποιό στην λίστα με τους διαθέσιμους.
     * @param entity φαρμακοποιός που είναι να αποθηκευτεί
     */
    public void save(Pharmacist entity) {
        entities.add(entity);
    }

    /**
     * Βρίσκει τον φαρμακοποιό με συγκερκιμένο όνομα και επίθετο.
     * Αν δεν τον βρεί, επιστρέφει null
     * @param firstName το όνομα του φαρμακοποιού που θέλουμε να βρούμε
     * @param lastName το επώνυμο του φαρμακοποιού που θέλουμε να βρούμε
     * @return επιστρέφει τον φαρμακοποιό
     */
    public Pharmacist find(String firstName, String lastName) {
        for (Pharmacist pharmacist : entities)
            if (pharmacist.getFirstName().equals(firstName) && pharmacist.getLastName().equals(lastName))
                return pharmacist;
        // Not found
        return null;
    }
}
