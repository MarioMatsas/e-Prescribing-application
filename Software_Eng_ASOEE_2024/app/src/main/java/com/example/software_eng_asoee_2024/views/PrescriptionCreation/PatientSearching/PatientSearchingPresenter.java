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
}

