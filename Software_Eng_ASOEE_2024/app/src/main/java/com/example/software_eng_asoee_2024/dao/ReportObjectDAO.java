package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Date;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.helperClasses.DoctorPatientSubstanceTruple;


import java.util.HashMap;
import java.util.List;

public interface ReportObjectDAO {
    /**
     * Πρώτα ελέγχει αν έχει αλλάξει ο μήνας. Αν ναι,
     * αδειάζει τα δεδομένα που υπήρχαν για προηγούμενο μήνα,
     * μιας και έχουμε διαφορετικό report ανα μήνα.
     * Μετά ελέγχει αν ο ίδιος γιατρός έχει δώσει στον ίδιο ασθενή την ίδια ουσία προηγουμένως.
     * Αν ναι, ενημερώνει το map κατάλληλα, αλλιώς προσθέτει αυτή την τριάδα στο map.
     * @param substance η χορηγημένη ουσία
     * @param patient ο ασθενής που του χορηγήθηκε η ουσία
     * @param doctor ο ύποπτος γιατρός
     * @param date η τωρινή, για εκείνη την στιγμή, ημερομηνία
     * @param amount το πλήθος ποου έχει δώσει ο γιατρός στον ασθενή
     */
    void update(Doctor doctor, Patient patient, ActiveSubstance substance, Date date, Double amount);

    /**
     * Ελέγχει αν το ποσό της συντγογραφημένης ουσίας,
     * που δίνεται σε συγκεκριμένο ασθενή από συγκεκριμένο γιατρό,
     * ξεπερνά το καθορισμένο όριο.(ο γιατρός είναι παράνομος)
     * Αν ναι, και ο γιατρός υπάρχει ήδη στην λίστα,
     * ανανεώνουμε το πλήθος τψν φορών που έχει περάσει το όριο μηνιαίας δόσης.
     * Αν δεν υπάρχει στην λίστα, απλά τον προθέτει στην λίστα παράνομων γιατρών.
     * @param truple η τριάδα γιατρού, ασθενή, ουσίας που ελέγχεται
     */
    void checkForUnlawful(DoctorPatientSubstanceTruple truple);

    HashMap<Doctor, Integer> getUnlawfulDoctors();
    HashMap<DoctorPatientSubstanceTruple, Double> getMap();
    Integer getYear();

    Integer getMonth();

    void clearData();
}
