package com.example.software_eng_asoee_2024.views.PrescreptionExecution.Selection;

import com.example.software_eng_asoee_2024.domain.Prescription;

import java.util.List;

public interface PrescriptionSelectionView {
    void navigateToExecution();
    void showPatientPrescriptions(PrescriptionSelectionPresenter presenter);
    void updatePrescriptionSpinner(List<Prescription> prescriptions);
    void showError(String message);
}
