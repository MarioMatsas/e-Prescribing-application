package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Doctor;

import java.util.List;

public interface ActiveSubstanceDAO {
    /**
     * Βγάζει από τις διαθέσιμες ουσίες την συγκεκριμένη.
     * @param entity δραστική ουσία προς αφαίρεση
     */
    void delete(ActiveSubstance entity);

    /**
     * Βρίσκει και επιστρέφει όλες τις διαθέσιμες δραστικές ουσίες.
     * @return επιστρέφει όλες τις διαθέσιμες δραστικές ουσίες
     */
    List<ActiveSubstance> findAll();

    /**
     * Αποθηκεύει δραστική ουσία στην λίστα με τις διαθέσιμες.
     * @param entity δραστική ουσία που είναι να αποθηκευτεί
     */
    void save(ActiveSubstance entity);

    /**
     * Επεξεργάζεται μια δραστική ουσία.
     * @param edit δραστική ουσία που είναι να τροποποιηθεί
     * @param editTo δραστική ουσία που είναι να αντιγραφούν τα στοιχεία της
     */
    public void edit(ActiveSubstance edit, ActiveSubstance editTo);

    /**
     * Βρίσκει την δραστική ουσία με συγκερκιμένο όνομα name.
     * Αν δεν την βρεί, επιστρέφει null
     * @param name το όνομα της δραστικής ουσίας που θέλουμε να βρούμε
     * @return επιστρέφει την δραστική ουσία
     */
    ActiveSubstance find(String name);
}
