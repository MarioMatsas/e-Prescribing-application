package com.example.software_eng_asoee_2024.views.PrescriptionCreation.PatientSearching;

import com.example.software_eng_asoee_2024.domain.Patient;

public interface PatientSearchingView {
    void prepareForCreate();
    void navigateToCreation(Integer patientSSN);

    void showError(String message);
}
