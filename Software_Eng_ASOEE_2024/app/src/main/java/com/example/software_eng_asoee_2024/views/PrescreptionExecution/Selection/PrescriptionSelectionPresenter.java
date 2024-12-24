package com.example.software_eng_asoee_2024.views.PrescreptionExecution.Selection;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.PrescriptionLine;
import com.example.software_eng_asoee_2024.domain.Unit;
import com.example.software_eng_asoee_2024.memorydao.PatientDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;

import java.util.List;

public class PrescriptionSelectionPresenter {
    private PrescriptionSelectionView view;
    private PrescriptionDAOMemory prescriptionDAO;
    private PatientDAOMemory patientDAO;

    public PrescriptionSelectionView getView() {
        return view;
    }
    public void setView(PrescriptionSelectionView view) {
        this.view = view;
    }
    public void setPrescriptionDAO(PrescriptionDAOMemory prescriptionDAO) {
        this.prescriptionDAO = prescriptionDAO;
    }

    public void setPatientDAO(PatientDAOMemory patientDAO) {
        this.patientDAO = patientDAO;
    }

    public void showPatientPrescriptions(int SSN){
        Patient p1 = new Patient("Tom", "Hobs", 123123123);
        for (Patient p2: patientDAO.findAll()){
            System.out.println(p2.getSSN() +" "+p2.getFirstName());
        }
        patientDAO.save(p1);
        // Check if there is a user with the given SSN
        Patient patient = null;
        List<Patient> patients = patientDAO.findAll();
        for (Patient p : patients){
            if (p.getSSN() == SSN){
                patient = p;
                break;
            }
        }
        if (patient == null) {
            view.showError("Patient not found.");
            view.clearPrescriptionSpinner();
            return;
        }

        // Display all of the patients prescriptions
        Prescription presc = new Prescription("Wolff-Parkinson-White", new Doctor("John", "Doe", "Cardiology"), p1);
        PrescriptionLine line = new PrescriptionLine(Form.CREAM, new Concentration(10, Unit.mg_per_g), "For 10 days", new ActiveSubstance("Paracetamol", 20d));
        presc.addLine(line);
        line = new PrescriptionLine(Form.CREAM, new Concentration(40, Unit.mg_per_g), "For 20 days", new ActiveSubstance("Diddy juice", 20d));
        presc.addLine(line);
        prescriptionDAO.save(presc);
        presc = new Prescription("White", new Doctor("John", "Doe", "Cardiology"), p1);
        line = new PrescriptionLine(Form.CREAM, new Concentration(10, Unit.mg_per_g), "For 10 days", new ActiveSubstance("Paracetamol", 20d));
        presc.addLine(line);
        prescriptionDAO.save(presc);
        List<Prescription> prescriptions = prescriptionDAO.findPrescriptionByPatient(patient);
        view.updatePrescriptionSpinner(prescriptions);
    }
}
