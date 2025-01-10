package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.MedicineType;
import com.example.software_eng_asoee_2024.domain.NOHCS_Employee;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.PrescriptionLine;
import com.example.software_eng_asoee_2024.domain.Unit;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.DoctorDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.NOHCSEmployeeDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PatientDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmacistDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.ReportObjectDAOMemory;

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

        NOHCSEmployeeDAO empDAO = new NOHCSEmployeeDAOMemory();
        empDAO.save(new NOHCS_Employee("a", "a"));

        ActivaSubstanceDAO activeSubstanceDAO = new ActiveSubstanceDAOMemory();
        activeSubstanceDAO.save(new ActiveSubstance("Paracetamol", 20d));
        activeSubstanceDAO.save(new ActiveSubstance("Ibuprofen", 15d));

        PharmaceuticalProductDAO pharmaceuticalProductDAO = new PharmaceuticalProductDAOMemory();
        ArrayList<ActiveSubstance> as = new ArrayList<ActiveSubstance>();
        ArrayList<Concentration> asCs = new ArrayList<Concentration>();
        ArrayList<ActiveSubstance> as2 = new ArrayList<ActiveSubstance>();
        ArrayList<Concentration> asCs2 = new ArrayList<Concentration>();
        as.add(activeSubstanceDAO.find("Paracetamol"));
        asCs.add(new Concentration(10.0, Unit.mg_per_g));
        as.add(activeSubstanceDAO.find("Ibuprofen"));
        asCs.add(new Concentration(20.0, Unit.mg_per_disk));
        as2.add(activeSubstanceDAO.find("Ibuprofen"));
        asCs2.add(new Concentration(100.0, Unit.mg_per_ml));
        pharmaceuticalProductDAO.save(new PharmaceuticalProduct("Brufen Plus", 600, Form.PILL, MedicineType.GENERIC, as, asCs, "32 pills in pack"));
        pharmaceuticalProductDAO.save(new PharmaceuticalProduct("Advil", 300, Form.PILL, MedicineType.GENERIC, as2, asCs2, "16 pills in pack"));

        PrescriptionDAO prescriptionDAO = new PrescriptionDAOMemory();
        Prescription presc1 = new Prescription("Wolff-Parkinson-White", doctorDAO.find("m", "m"), patientDAO.find(123123123));
        PrescriptionLine line = new PrescriptionLine(Form.PILL, new Concentration(10.0, Unit.mg_per_g), "For 10 days, 2 pills per day", activeSubstanceDAO.find("Paracetamol"));
        presc1.addLine(line);
        line = new PrescriptionLine(Form.PILL, new Concentration(40.0, Unit.mg_per_g), "For 20 days, 1 pill in the morning", activeSubstanceDAO.find("Ibuprofen"));
        presc1.addLine(line);
        prescriptionDAO.save(presc1);
        Prescription presc2 = new Prescription("Mild headache", doctorDAO.find("m", "m"), patientDAO.find(123123123));
        line = new PrescriptionLine(Form.PILL, new Concentration(10.0, Unit.mg_per_g), "For 10 days, 2 pills per day", activeSubstanceDAO.find("Paracetamol"));
        presc2.addLine(line);
        prescriptionDAO.save(presc2);

        // reportDAO.update(prescription.getDoctor(), prescription.getPatient(), line.getActiveSubstance(), prescription.getDate(), amounts.get(i));

        ReportObjectDAO reportDAO = new ReportObjectDAOMemory();
        reportDAO.update(doctorDAO.find("m", "m"), patientDAO.find(123123123), activeSubstanceDAO.find("Paracetamol"), presc1.getDate(), 5.0);
        reportDAO.update(doctorDAO.find("m", "m"), patientDAO.find(123123123), activeSubstanceDAO.find("Ibuprofen"), presc1.getDate(), 10.0);
        reportDAO.update(doctorDAO.find("m", "m"), patientDAO.find(123123123), activeSubstanceDAO.find("Paracetamol"), presc2.getDate(), 13.0);

    }

    public abstract DoctorDAO getDoctorDAO();
    public abstract PharmacistDAO getPharmacistDAO();
    public abstract PatientDAO getPatientDAO();
    public abstract NOHCSEmployeeDAO getNOHCSEmployeeDAO();

    public abstract ActivaSubstanceDAO getActiveSubstanceDAO();

    public abstract PharmaceuticalProductDAO getPharmaceuticalProductDAO();

    public abstract PrescriptionDAO getPrescriptionDAO();

    public abstract ReportObjectDAO getReportDAO();

}



