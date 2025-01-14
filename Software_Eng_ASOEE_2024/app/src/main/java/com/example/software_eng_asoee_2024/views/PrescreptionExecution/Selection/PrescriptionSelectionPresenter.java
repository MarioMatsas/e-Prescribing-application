package com.example.software_eng_asoee_2024.views.PrescreptionExecution.Selection;

import android.content.Intent;

import com.example.software_eng_asoee_2024.dao.PatientDAO;
import com.example.software_eng_asoee_2024.dao.PrescriptionDAO;
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
import com.example.software_eng_asoee_2024.views.PrescreptionExecution.Execution.PrescriptionExecutionActivity;

import java.util.List;

public class PrescriptionSelectionPresenter {
    private PrescriptionSelectionView view;
    private PrescriptionDAO prescriptionDAO;
    private PatientDAO patientDAO;

    public PrescriptionSelectionView getView() {
        return view;
    }
    public void setView(PrescriptionSelectionView view) {
        this.view = view;
    }
    public void setPrescriptionDAO(PrescriptionDAO prescriptionDAO) {
        this.prescriptionDAO = prescriptionDAO;
    }

    public void setPatientDAO(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    /**
     * Makes sure the SSN given is valid and updates the
     * prescriptions shown.
     *
     * @param SSN
     */
    public void showPatientPrescriptions(String SSN){
        if (SSN.isEmpty()) {  // Check if the SSN field is empty
            view.showError("SSN cannot be empty.");
            view.clearPrescriptionSpinner();
            return;
        }
        try {
            int ssn = Integer.parseInt(SSN);  // Try to parse the SSN
            // Check if there is a user with the given SSN
            Patient patient = null;
            List<Patient> patients = patientDAO.findAll();
            for (Patient p : patients){
                if (p.getSSN() == ssn){
                    patient = p;
                    break;
                }
            }
            if (patient == null) {
                view.showError("Patient not found.");
                view.clearPrescriptionSpinner();
                return;
            }

            List<Prescription> prescriptions = prescriptionDAO.findPrescriptionByPatient(patient);
            if (prescriptions.isEmpty()){
                view.showError("No prescriptions found.");
            }
            view.updatePrescriptionSpinner(prescriptions);

        }
        catch (NumberFormatException e) {
            view.showError("Invalid SSN format.");
            view.clearPrescriptionSpinner();
        }
    }

    /**
     * Checks if there are no available prescriptions,
     * if so the user can't execute a prescription
     *
     * @param spinnerItem
     * @return
     */
    public boolean navigateToExecution(Prescription spinnerItem){
        if (spinnerItem != null) {
            return true;
        }
        else {
            view.showError("No prescription selected.");
            return false;
        }
    }
}
