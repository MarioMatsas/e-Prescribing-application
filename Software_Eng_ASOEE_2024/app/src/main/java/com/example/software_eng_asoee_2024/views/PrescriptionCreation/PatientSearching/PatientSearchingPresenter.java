package com.example.software_eng_asoee_2024.views.PrescriptionCreation.PatientSearching;

import android.content.Intent;

import com.example.software_eng_asoee_2024.dao.PatientDAO;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.memorydao.PatientDAOMemory;
import com.example.software_eng_asoee_2024.views.PrescriptionCreation.PatientSearching.PatientSearchingView;

public class PatientSearchingPresenter {
    private PatientSearchingView view;
    private PatientDAO patientDAO;

    public PatientSearchingView getView() {
        return view;
    }

    public void setView(PatientSearchingView view) {
        this.view = view;
    }

    public void setPatientDAO(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    /**
     * Βρίσκει τον ασθενή, ώστε να συνεχιστεί η διαδικασία δημιουργίας συνταγής,
     * για τον συγκεκριμένο ασθενή.
     * Αν δεν τον βρεί, εμφανίζεται κατάλληλο μήνυμα.
     * Αν δοθεί λάθος input, πάλι εμφανίζεται κατάλληλο μήνυμα.
     * @param diagnosis διάγνωση για αυτό τον ασθενή
     * @param SSN ΑΜΚΑ ασθενή
     */
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

