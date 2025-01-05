package com.example.software_eng_asoee_2024.views.PrescriptionCreation.PatientSearching;

import android.content.Intent;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.memorydao.PatientDAOMemory;
import com.example.software_eng_asoee_2024.views.PrescriptionCreation.PatientSearching.PatientSearchingView;

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

    public Patient check_patient_login(String inputSSN) {
        try {
            int ssn = Integer.parseInt(inputSSN);  // Try to parse the ssn
            Patient patient = patientDAO.find(ssn);
            if (patient != null) {
                //view.navigateToCreationScreen(patient);
                return patient;
            }
            view.showError("Patient NOT FOUND");
        } catch (NumberFormatException e) {
            view.showError("Invalid SSN format.");
            //clearProductSpinner();
        }
        return null;
    }
}

