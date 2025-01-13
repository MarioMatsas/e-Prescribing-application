package com.example.software_eng_asoee_2024.memorydao;

import com.example.software_eng_asoee_2024.dao.PrescriptionDAO;
import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.PrescriptionLine;
import com.example.software_eng_asoee_2024.domain.Unit;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAOMemory implements PrescriptionDAO {
    static ArrayList<Prescription> entities = new ArrayList<Prescription>();

    /**
     * Βγάζει από τις διαθέσιμες συνταγές την συγκεκριμένη.
     * @param entity συνταγή προς αφαίρεση
     */
    public void delete(Prescription entity) {
        entities.remove(entity);
    }

    /**
     * Βρίσκει και επιστρέφει όλες τις διαθέσιμες συνταγές.
     * @return επιστρέφει όλες τις διαθέσιμες συνταγές.
     */
    public List<Prescription> findAll() {
        ArrayList<Prescription> result = new ArrayList<Prescription>(entities);
        return result;
    }

    /**
     * Αποθηκεύει την συνταγή στην λίστα με τις διαθέσιμους.
     * @param entity συνταγή που είναι να αποθηκευτεί
     */
    public void save(Prescription entity) {
        entities.add(entity);
    }

    /**
     * Βρίσκει την συνταγή με συγκερκιμένο id.
     * Αν δεν την βρεί, επιστρέφει null
     * @param Id το id της συνταγής που θέλουμε να βρούμε
     * @return επιστρέφει την συνταγή με το συγκεκριμένο id
     */
    public Prescription findPrescriptionById(int Id){
        for (Prescription prescr : entities){
            if (prescr.getId()==Id){
                return prescr;
            }
        }
        return null;
    }

    /**
     * Βρίσκει όλες τις συνταγές του συγκερκιμένου ασθενή.
     * @param patient ο ασθενής του οποίου θέλουμε να βρούμε τις συνταγες
     * @return όλες τις συνταγές πυ αντιστοιχούν σε αυτό τον ασθενή
     */
    public List<Prescription> findPrescriptionByPatient(Patient patient){
        ArrayList<Prescription> result = new ArrayList<Prescription>();
        for (Prescription prescr : entities){
            if (prescr.getPatient().equals(patient)){
                result.add(prescr);
            }
        }
        return result;
    }
}
