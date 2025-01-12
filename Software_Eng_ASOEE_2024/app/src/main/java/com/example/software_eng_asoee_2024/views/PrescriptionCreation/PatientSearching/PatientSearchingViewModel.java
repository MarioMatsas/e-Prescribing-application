package com.example.software_eng_asoee_2024.views.PrescriptionCreation.PatientSearching;

import android.util.Log;
import androidx.lifecycle.ViewModel;

import com.example.software_eng_asoee_2024.memorydao.DoctorDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PatientDAOMemory;

public class PatientSearchingViewModel extends ViewModel{

    private PatientSearchingPresenter presenter;

    /**
     * Ο κατασκευαστής του viewModel.
     * Δημιουργεί την μνήμη του presenter.
     */
    public PatientSearchingViewModel() {
        presenter = new PatientSearchingPresenter();
        presenter.setPatientDAO(new PatientDAOMemory());
    }

    /**
     * Επιστρέφει τον presenter του activity αυτού.
     * @return ο presenter του activity αυτού
     */
    public PatientSearchingPresenter getPresenter() {
        return presenter;
    }

    /**
     * Για debugging είναι η μέθοδος αυτή.
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("PatientSearching", "On Cleared");
    }
}
