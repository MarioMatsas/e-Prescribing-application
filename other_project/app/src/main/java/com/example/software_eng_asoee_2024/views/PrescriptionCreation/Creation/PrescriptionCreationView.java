package com.example.software_eng_asoee_2024.views.PrescriptionCreation.Creation;

public interface PrescriptionCreationView {
    void populateActiveSubSpinner();
    void setupFormSpinner();
    void createPrescription();
    void addLine();
    void clearFields();
    void showError(String message);

}
