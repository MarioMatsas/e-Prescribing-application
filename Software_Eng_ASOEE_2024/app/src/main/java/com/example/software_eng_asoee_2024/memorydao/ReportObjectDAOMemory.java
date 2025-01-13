package com.example.software_eng_asoee_2024.memorydao;

import com.example.software_eng_asoee_2024.dao.ReportObjectDAO;
import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Date;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.helperClasses.DoctorPatientSubstanceTruple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ReportObjectDAOMemory implements ReportObjectDAO {
    static Integer month = 0;
    static Integer year = 0;
    static HashMap<DoctorPatientSubstanceTruple, Double> map = new HashMap<DoctorPatientSubstanceTruple, Double>();
    static HashMap<Doctor, Integer> unlawful = new HashMap<Doctor, Integer>();

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
    public void update(Doctor doctor, Patient patient, ActiveSubstance substance, Date date, Double amount){
        // Check for month change
        if (date.getMonth().equals(month) || date.getYear().equals(year)){
            month = date.getMonth();
            year = date.getYear();
            clearData();
        }
        // Check if the truple is already in the map
        boolean found = false;
        for (DoctorPatientSubstanceTruple truple : map.keySet()){

            if (truple.getDoctor() == doctor && truple.getPatient() == patient && truple.getActiveSub() == substance){

                map.put(truple, map.get(truple) + amount);
                checkForUnlawful(truple);
                found = true;
                break;
            }

        }
        // If it doesn't then add it
        if (!found){
            DoctorPatientSubstanceTruple truple = new DoctorPatientSubstanceTruple(doctor, patient, substance);
            map.put(truple, amount);
            checkForUnlawful(truple);
        }

    }

    /**
     * Ελέγχει αν το ποσό της συντγογραφημένης ουσίας,
     * που δίνεται σε συγκεκριμένο ασθενή από συγκεκριμένο γιατρό,
     * ξεπερνά το καθορισμένο όριο.(ο γιατρός είναι παράνομος)
     * Αν ναι, και ο γιατρός υπάρχει ήδη στην λίστα,
     * ανανεώνουμε το πλήθος τψν φορών που έχει περάσει το όριο μηνιαίας δόσης.
     * Αν δεν υπάρχει στην λίστα, απλά τον προθέτει στην λίστα παράνομων γιατρών.
     * @param truple η τριάδα γιατρού, ασθενή, ουσίας που ελέγχεται
     */
    public void checkForUnlawful(DoctorPatientSubstanceTruple truple) {
        // Check if the prescribed amount exceeds the expected quantity for the active substance
        if (map.get(truple) > truple.getActiveSub().getExpectedQuantityPerMonth()) {
            // Check if the doctor is already in the unlawful map
            if (unlawful.containsKey(truple.getDoctor())) {
                unlawful.put(truple.getDoctor(), unlawful.get(truple.getDoctor()) + 1);
            } else {
                unlawful.put(truple.getDoctor(), 1);
            }
        }
    }

    public HashMap<Doctor, Integer> getUnlawfulDoctors() {
        return unlawful;
    }

    public HashMap<DoctorPatientSubstanceTruple, Double> getMap() {
        return map;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }
    public void clearData(){
        map.clear();
        unlawful.clear();
    }

}