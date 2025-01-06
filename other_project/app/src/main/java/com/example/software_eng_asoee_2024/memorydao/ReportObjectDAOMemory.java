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
    private Integer month;
    private Integer year;
    static HashMap<DoctorPatientSubstanceTruple, Double> map = new HashMap<DoctorPatientSubstanceTruple, Double>();
    static HashMap<Doctor, Integer> unlawful = new HashMap<Doctor, Integer>();

    public void update(Doctor doctor, Patient patient, ActiveSubstance substance, Date date, Double amount){
        // Check for month change
        if (date.getMonth() != month || date.getYear() != year){
            month = date.getMonth();
            year = date.getYear();
            map.clear();
            unlawful.clear();
        }
        // Check if the pair is already in the map
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

    public void addUn(Doctor doc, Integer i){
        System.out.println("CUZ");
        unlawful.put(doc, i);
        System.out.println(unlawful.size());
    }

}