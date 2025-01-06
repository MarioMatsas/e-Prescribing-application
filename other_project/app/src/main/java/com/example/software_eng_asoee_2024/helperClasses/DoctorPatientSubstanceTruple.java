package com.example.software_eng_asoee_2024.helperClasses;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Patient;

public class DoctorPatientSubstanceTruple {
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

