package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.Patient;

import java.util.List;

public interface PatientDAO {
    /**
     * Βγάζει από τον διαθέσιμο ασθενή από την λίστα.
     * @param entity γιατρός προς αφαίρεση
     */
    void delete(Patient entity);

    /**
     * Βρίσκει και επιστρέφει όλους τους διαθέσιμους ασθενείς.
     * @return επιστρέφει όλους τους διαθέσιμους ασθενείς
     */
    List<Patient> findAll();

    /**
     * Αποθηκεύει τον ασθενή στην λίστα με τους διαθέσιμους.
     * @param entity ασθενής που είναι να αποθηκευτεί
     */
    void save(Patient entity);

    /**
     * Βρίσκει τον ασθενή με συγκερκιμένο ΑΜΚΑ.
     * Αν δεν τον βρεί, επιστρέφει null.
     * @param SSN το ΑΜΚΑ του ασθενή που θέλουμε να βρούμε
     * @return επιστρέφει τον ασθενή
     */
    Patient find(int SSN);
}
