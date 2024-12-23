package com.example.software_eng_asoee_2024.views.PrescreptionExecution.Selection;

import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Prescription;
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
            return;
        }

        // Display all of the patients prescriptions
        List<Prescription> prescriptions = prescriptionDAO.findPrescriptionByPatient(patient);
        view.updatePrescriptionSpinner(prescriptions);
    }
}
