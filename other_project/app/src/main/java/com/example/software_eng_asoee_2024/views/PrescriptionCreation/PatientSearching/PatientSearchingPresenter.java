package com.example.software_eng_asoee_2024.views.PrescriptionCreation.PatientSearching;

import android.content.Intent;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.memorydao.PatientDAOMemory;
import com.example.software_eng_asoee_2024.views.PrescriptionCreation.PatientSearching.PatientSearchingView;

import java.util.List;

public class PatientSearchingPresenter {
    private PatientSearchingView view;
    private PatientDAOMemory patientDAO;

    public PatientSearchingView getView() {
        return view;
    }

    public void setView(PatientSearchingView view) {
        this.view = view;
    }

    public void setPatientDAO(PatientDAOMemory patientDAO) {
        this.patientDAO = patientDAO;
    }

    public void findPatient(String SSN, String diagnosis){
        if (SSN.isEmpty() || diagnosis.isEmpty()) {  // Check if the SSN field is empty
            view.showError("Fields can't be empty.");
            return;
        }
        try {
            int ssn = Integer.parseInt(SSN);  // Try to parse the SSN
            // Check if there is a user with the given SSN
            Patient patient = patientDAO.find(ssn);
            if (patient == null) {
                view.showError("Patient not found.");
                return;
            }
            view.navigateToCreation(ssn);
        }
        catch (NumberFormatException e) {
            view.showError("Invalid SSN format.");
        }
    }

}

