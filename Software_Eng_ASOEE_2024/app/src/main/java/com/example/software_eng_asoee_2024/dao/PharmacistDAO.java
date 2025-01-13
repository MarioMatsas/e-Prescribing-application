package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.Pharmacist;

import java.util.List;

public interface PharmacistDAO {
    /**
     * Βγάζει από τους διαθέσιμους φαρμακοποιούς τον συγκεκριμένο.
     * @param entity φαρμακοποιός προς αφαίρεση
     */
    void delete(Pharmacist entity);

    /**
     * Βρίσκει και επιστρέφει όλους τους διαθέσιμους φαρμακοποιούς.
     * @return επιστρέφει όλους τους διαθέσιμους φαρμακοποιούς.
     */
    List<Pharmacist> findAll();

    /**
     * Αποθηκεύει τον φαρμακοποιό στην λίστα με τους διαθέσιμους.
     * @param entity φαρμακοποιός που είναι να αποθηκευτεί
     */
    void save(Pharmacist entity);

    /**
     * Βρίσκει τον φαρμακοποιό με συγκερκιμένο όνομα και επίθετο.
     * Αν δεν τον βρεί, επιστρέφει null
     * @param firstName το όνομα του φαρμακοποιού που θέλουμε να βρούμε
     * @param lastName το επώνυμο του φαρμακοποιού που θέλουμε να βρούμε
     * @return επιστρέφει τον φαρμακοποιό
     */
    Pharmacist find(String firstName, String lastName);
}
