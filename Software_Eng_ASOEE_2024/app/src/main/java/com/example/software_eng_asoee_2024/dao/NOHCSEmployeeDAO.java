package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.NOHCS_Employee;

import java.util.List;

public interface NOHCSEmployeeDAO {
    /**
     * Βγάζει από τους διαθέσιμους υπαλλήλους τον συγκεκριμένο.
     * @param entity υπάλληλο προς αφαίρεση
     */
    void delete(NOHCS_Employee entity);

    /**
     * Αποθηκεύει τον υπάλληλο στην λίστα με τους διαθέσιμους.
     * @param entity υπάλληλο που είναι να αποθηκευτεί
     */
    void save(NOHCS_Employee entity);

    /**
     * Βρίσκει και επιστρέφει όλους τους διαθέσιμους υπαλλήλους.
     * @return επιστρέφει όλους τους διαθέσιμους υπαλλήλους.
     */
    List<NOHCS_Employee> findAll();

    /**
     * Βρίσκει τον υπάλληλο με συγκερκιμένο όνομα και επίθετο.
     * Αν δεν τον βρεί, επιστρέφει null
     * @param firstName το όνομα του υπαλλήλου που θέλουμε να βρούμε
     * @param lastName το επώνυμο του υπαλλήλου που θέλουμε να βρούμε
     * @return επιστρέφει τον υπάλληλο
     */
    NOHCS_Employee find(String firstName, String lastName);
}
