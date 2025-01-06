package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Date;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.helperClasses.DoctorPatientSubstanceTruple;


import java.util.HashMap;
import java.util.List;

public interface ReportObjectDAO {
    void update(Doctor doctor, Patient patient, ActiveSubstance substance, Date date, Double amount);

    void checkForUnlawful(DoctorPatientSubstanceTruple truple);

    HashMap<Doctor, Integer> getUnlawfulDoctors();

    void addUn(Doctor doc, Integer i);
}
