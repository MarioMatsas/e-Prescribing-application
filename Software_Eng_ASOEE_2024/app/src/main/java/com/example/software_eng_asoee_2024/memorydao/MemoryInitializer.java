package com.example.software_eng_asoee_2024.memorydao;

import com.example.software_eng_asoee_2024.dao.ActivaSubstanceDAO;
import com.example.software_eng_asoee_2024.dao.DoctorDAO;
import com.example.software_eng_asoee_2024.dao.Initializer;
import com.example.software_eng_asoee_2024.dao.PatientDAO;
import com.example.software_eng_asoee_2024.dao.PharmacistDAO;
import com.example.software_eng_asoee_2024.dao.PharmacudicalProductDAO;
import com.example.software_eng_asoee_2024.dao.PrescriptionDAO;
import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.domain.PharmacudicalProduct;
import com.example.software_eng_asoee_2024.domain.Prescription;

public class MemoryInitializer extends Initializer {
    protected void eraseData() {
        // Delete doctors
        for (Doctor doctor: getDoctorDAO().findAll()){
            getDoctorDAO().delete(doctor);
        }
        // Delete pharmacists
        for (Pharmacist pharmacist: getPharmacistDAO().findAll()){
            getPharmacistDAO().delete(pharmacist);
        }
        // Delete patients
        for (Patient patient: getPatientDAO().findAll()){
            getPatientDAO().delete(patient);
        }
        // Delete substances
        for (ActiveSubstance activeSub: getActiveSubstanceDAO().findAll()){
            getActiveSubstanceDAO().delete(activeSub);
        }
        // Delete products
        for (PharmacudicalProduct product: getPharmacudicalProductDAO().findAll()){
            getPharmacudicalProductDAO().delete(product);
        }
        // Delete prescriptions
        for (Prescription prescription: getPrescriptionDAO().findAll()){
            getPrescriptionDAO().delete(prescription);
        }
    }
    public DoctorDAO getDoctorDAO(){
        return new DoctorDAOMemory();
    }

    public PharmacistDAO getPharmacistDAO(){
        return new PharmacistDAOMemory();
    }

    public PatientDAO getPatientDAO(){
        return new PatientDAOMemory();
    }

    public ActivaSubstanceDAO getActiveSubstanceDAO(){
        return new ActiveSubstanceDAOMemory();
    }

    public PharmacudicalProductDAO getPharmacudicalProductDAO(){
        return new PharmacudicalProductDAOMemory();
    }

    public PrescriptionDAO getPrescriptionDAO(){
        return new PrescriptionDAOMemory();
    }
}
