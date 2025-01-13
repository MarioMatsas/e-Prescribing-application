package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Prescription;

import java.util.List;

public interface PrescriptionDAO {
    /**
     * Βγάζει από τις διαθέσιμες συνταγές την συγκεκριμένη.
     * @param entity συνταγή προς αφαίρεση
     */
    void delete(Prescription entity);

    /**
     * Βρίσκει και επιστρέφει όλες τις διαθέσιμες συνταγές.
     * @return επιστρέφει όλες τις διαθέσιμες συνταγές.
     */
    List<Prescription> findAll();

    /**
     * Αποθηκεύει την συνταγή στην λίστα με τις διαθέσιμους.
     * @param entity συνταγή που είναι να αποθηκευτεί
     */
    void save(Prescription entity);

    /**
     * Βρίσκει την συνταγή με συγκερκιμένο id.
     * Αν δεν την βρεί, επιστρέφει null
     * @param Id το id της συνταγής που θέλουμε να βρούμε
     * @return επιστρέφει την συνταγή με το συγκεκριμένο id
     */
    Prescription findPrescriptionById(int Id);

    /**
     * Βρίσκει όλες τις συνταγές του συγκερκιμένου ασθενή.
     * @param patient ο ασθενής του οποίου θέλουμε να βρούμε τις συνταγες
     * @return επιστρέφει όλες τις συνταγές πυ αντιστοιχούν σε αυτό τον ασθενή
     */
    List<Prescription> findPrescriptionByPatient(Patient patient);
}
