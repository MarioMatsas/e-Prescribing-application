package com.example.software_eng_asoee_2024.memorydao;

import com.example.software_eng_asoee_2024.dao.ActivaSubstanceDAO;
import com.example.software_eng_asoee_2024.dao.DoctorDAO;
import com.example.software_eng_asoee_2024.dao.Initializer;
import com.example.software_eng_asoee_2024.dao.NOHCSEmployeeDAO;
import com.example.software_eng_asoee_2024.dao.PatientDAO;
import com.example.software_eng_asoee_2024.dao.PharmacistDAO;
import com.example.software_eng_asoee_2024.dao.PharmaceuticalProductDAO;
import com.example.software_eng_asoee_2024.dao.PrescriptionDAO;
import com.example.software_eng_asoee_2024.dao.ReportObjectDAO;
import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.NOHCS_Employee;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
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
        // Delete NOHCS Employees
        for (NOHCS_Employee employee: getNOHCSEmployeeDAO().findAll()){
            getNOHCSEmployeeDAO().delete(employee);
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
        for (PharmaceuticalProduct product: getPharmaceuticalProductDAO().findAll()){
            getPharmaceuticalProductDAO().delete(product);
        }
        // Delete prescriptions
        for (Prescription prescription: getPrescriptionDAO().findAll()){
            getPrescriptionDAO().delete(prescription);
        }
        // Delete all the the report data
        getReportDAO().clearData();
    }
    public DoctorDAO getDoctorDAO(){
        return new DoctorDAOMemory();
    }

    public NOHCSEmployeeDAO getNOHCSEmployeeDAO() {
        return new NOHCSEmployeeDAOMemory();
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

    public PharmaceuticalProductDAO getPharmaceuticalProductDAO(){
        return new PharmaceuticalProductDAOMemory();
    }

    public PrescriptionDAO getPrescriptionDAO(){
        return new PrescriptionDAOMemory();
    }
    public ReportObjectDAO getReportDAO() { return new ReportObjectDAOMemory(); }
}
