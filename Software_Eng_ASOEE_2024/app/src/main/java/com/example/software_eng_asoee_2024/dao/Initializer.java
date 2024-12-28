package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.MedicineType;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.domain.PharmacudicalProduct;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.PrescriptionLine;
import com.example.software_eng_asoee_2024.domain.Unit;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.DoctorDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PatientDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmacistDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmacudicalProductDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;

import java.util.ArrayList;

public abstract class Initializer {
    protected abstract void eraseData();

    public void prepareData(){
        // Erase all the previous data
        eraseData();

        // Populate all the DAOs
        DoctorDAO doctorDAO = new DoctorDAOMemory();
        doctorDAO.save(new Doctor("m", "m","Cardiologist"));

        PharmacistDAO pharmacistDAO = new PharmacistDAOMemory();
        pharmacistDAO.save(new Pharmacist("d", "ch"));
        pharmacistDAO.save(new Pharmacist("a", "p"));

        PatientDAO patientDAO = new PatientDAOMemory();
        patientDAO.save(new Patient("Tom", "Hobs", 123123123));

        ActivaSubstanceDAO activeSubstanceDAO = new ActiveSubstanceDAOMemory();
        activeSubstanceDAO.save(new ActiveSubstance("Paracetamol", 20d));
        activeSubstanceDAO.save(new ActiveSubstance("Ibuprofen", 15d));

        PharmacudicalProductDAO pharmacudicalProductDAO = new PharmacudicalProductDAOMemory();
        ArrayList<ActiveSubstance> as = new ArrayList<ActiveSubstance>();
        ArrayList<ActiveSubstance> as2 = new ArrayList<ActiveSubstance>();
        as.add(activeSubstanceDAO.find("Paracetamol"));
        as.add(activeSubstanceDAO.find("Ibuprofen"));
        as2.add(activeSubstanceDAO.find("Ibuprofen"));
        pharmacudicalProductDAO.save(new PharmacudicalProduct("Brufen Plus", 600, Form.PILL, MedicineType.GENERIC, as, "32 pills in pack"));
        pharmacudicalProductDAO.save(new PharmacudicalProduct("Advil", 300, Form.PILL, MedicineType.GENERIC, as2, "16 pills in pack"));

        PrescriptionDAO prescriptionDAO = new PrescriptionDAOMemory();
        Prescription presc = new Prescription("Wolff-Parkinson-White", doctorDAO.find("m", "m"), patientDAO.find(123123123));
        PrescriptionLine line = new PrescriptionLine(Form.PILL, new Concentration(10, Unit.mg_per_g), "For 10 days, 2 pills per day", activeSubstanceDAO.find("Paracetamol"));
        presc.addLine(line);
        line = new PrescriptionLine(Form.PILL, new Concentration(40, Unit.mg_per_g), "For 20 days, 1 pill in the morning", activeSubstanceDAO.find("Ibuprofen"));
        presc.addLine(line);
        prescriptionDAO.save(presc);

    }

    public abstract DoctorDAO getDoctorDAO();
    public abstract PharmacistDAO getPharmacistDAO();
    public abstract PatientDAO getPatientDAO();

    public abstract ActivaSubstanceDAO getActiveSubstanceDAO();

    public abstract PharmacudicalProductDAO getPharmacudicalProductDAO();

    public abstract PrescriptionDAO getPrescriptionDAO();

}



