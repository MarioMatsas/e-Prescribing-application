package com.example.software_eng_asoee_2024.dao;

import java.util.List;
import com.example.software_eng_asoee_2024.domain.Doctor;

public interface DoctorDAO{
    /**
     * Βγάζει από τους διαθέσιμους γιατρούς τον συγκεκριμένο.
     * @param entity γιατρός προς αφαίρεση
     */
    void delete(Doctor entity);

    /**
     * Βρίσκει και επιστρέφει όλους τους διαθέσιμους γιατρούς.
     * @return επιστρέφει όλους τους διαθέσιμους γιατρούς
     */
    List<Doctor> findAll();

    /**
     * Αποθηκεύει τον γιατρό στην λίστα με τους διαθέσιμους.
     * @param entity γιατρός που είναι να αποθηκευτεί
     */
    void save(Doctor entity);

    /**
     * Βρίσκει τον γιατρό με συγκερκιμένο όνομα και επίθετο.
     * Αν δεν τον βρεί, επιστρέφει null
     * @param firstName το όνομα του γιατρού που θέλουμε να βρούμε
     * @param lastName το επώνυμο του γιατρού που θέλουμε να βρούμε
     * @return επιστρέφει τον γιατρό
     */
    Doctor find(String firstName, String lastName);
}
