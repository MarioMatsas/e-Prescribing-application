package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;

import java.util.List;

public interface PharmaceuticalProductDAO {
    /**
     * Βγάζει από τα διαθέσιμα προϊόντα το συγκεκριμένο.
     * @param entity προϊόν προς αφαίρεση
     */
    void delete(PharmaceuticalProduct entity);

    /**
     * Βρίσκει και επιστρέφει όλα τα διαθέσιμα προϊόντα.
     * @return επιστρέφει όλα τα διαθέσιμα προϊόντα.
     */
    List<PharmaceuticalProduct> findAll();

    /**
     * Αποθηκεύει το προϊόν στην λίστα με τους διαθέσιμους.
     * @param entity προϊόν που είναι να αποθηκευτεί
     */
    void save(PharmaceuticalProduct entity);

    /**
     * Επεξεργάζεται ένα προϊόν.
     * @param edit προϊόν που είναι να τροποποιηθεί
     * @param editTo προϊόν που είναι να αντιγραφούν τα στοιχεία του
     */
    public void edit(PharmaceuticalProduct edit, PharmaceuticalProduct editTo);

    /**
     * Βρίσκει το προϊόν με συγκερκιμένο όνομα.
     * Αν δεν τον βρεί, επιστρέφει null
     * @param name το όνομα του προϊόντος που θέλουμε να βρούμε
     * @return επιστρέφει το προϊόν
     */
    PharmaceuticalProduct find(String name);
}
