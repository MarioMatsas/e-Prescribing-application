package com.example.software_eng_asoee_2024.views.PrescriptionCreation.Creation;

import com.example.software_eng_asoee_2024.domain.Prescription;

public interface PrescriptionCreationView {
    void showError(String message);
    void addPrescriptionLine();
    void finishCreation();
}
