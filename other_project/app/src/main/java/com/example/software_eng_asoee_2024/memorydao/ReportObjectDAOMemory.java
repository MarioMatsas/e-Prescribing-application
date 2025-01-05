package com.example.software_eng_asoee_2024.memorydao;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Date;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Patient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ReportObjectDAOMemory {
    private Integer month;
    private Integer year;
    static HashMap<DoctorPatientSubstanceTruple, Double> map = new HashMap<>();
    HashSet<Doctor> unlawful = new HashSet<Doctor>();

    public void update(Doctor doctor, Patient patient, ActiveSubstance substance, Date date, Double amount){
        // Check for month change
        if (date.getMonth() != month || date.getYear() != year){
            month = date.getMonth();
            year = date.getYear();
            map.clear();
            unlawful.clear();
        }
        // Check if the pair is already in the map
        for (DoctorPatientSubstanceTruple truple : map.keySet()){
            if (truple.getDoctor() == doctor && truple.getPatient() == patient && truple.getActiveSub() == substance){
                map.put(truple, map.get(truple) + amount);
            }
            break;
        }
        // If it doesn't then add it
        DoctorPatientSubstanceTruple truple = new DoctorPatientSubstanceTruple(doctor, patient, substance);
        map.put(truple, amount);
        checkForUnlawful(truple);
    }

    public void checkForUnlawful(DoctorPatientSubstanceTruple truple){
        if (map.get(truple) > truple.getActiveSub().getExpectedQuantityPerMonth()){
            unlawful.add(truple.getDoctor());
        }
    }

    public HashSet<Doctor> getUnlawfulDoctors(){
        return unlawful;
    }

}

class DoctorPatientSubstanceTruple {
    private Doctor doctor;
    private Patient patient;
    private ActiveSubstance sub;

    // Constructor
    public DoctorPatientSubstanceTruple(Doctor doctor, Patient patient, ActiveSubstance sub) {
        this.doctor = doctor;
        this.patient = patient;
        this.sub = sub;
    }

    // Getters and setters
    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public ActiveSubstance getActiveSub(){
        return sub;
    }
}
