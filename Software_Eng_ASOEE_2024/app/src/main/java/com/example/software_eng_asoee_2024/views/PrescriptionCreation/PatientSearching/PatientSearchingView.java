package com.example.software_eng_asoee_2024.views.PrescriptionCreation.PatientSearching;

import com.example.software_eng_asoee_2024.domain.Patient;

public interface PatientSearchingView {

    /**
     * Καλεί τον presenter, μέσω του viewModel, να ελέγξει και να επιβεβαιώσει
     * την δημιουργία του επόμενου activity(PrescriptionCreation).
     */
    void prepareForCreate();

    /**
     * Δημιουργεί το επόμενο activity και το εκκινεί.
     * @param patientSSN ΑΜΚΑ ασθενή
     */
    void navigateToCreation(Integer patientSSN);

    /**
     * Ορίζει στο errorMessage μήνυμα,
     * το περιεχόμενο του message.
     * @param message Το περιεχόμενο του μηνύματος
     */
    void showError(String message);
}
