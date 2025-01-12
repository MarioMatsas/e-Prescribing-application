package com.example.software_eng_asoee_2024.views.PrescriptionCreation.Creation;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.DoctorDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PatientDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.ReportObjectDAOMemory;
import com.example.software_eng_asoee_2024.views.PrescreptionExecution.Selection.PrescriptionSelectionPresenter;
import com.example.software_eng_asoee_2024.views.PrescriptionCreation.Creation.PrescriptionCreationPresenter;

public class PrescriptionCreationViewModel extends ViewModel {
    private PrescriptionCreationPresenter presenter;

    /**
     * Ο κατασκευαστής του viewModel.
     * Δημιουργεί την μνήμη του presenter.
     */
    public PrescriptionCreationViewModel() {
        presenter = new PrescriptionCreationPresenter();
        presenter.setPrescriptionDAO(new PrescriptionDAOMemory());
        presenter.setDoctorDAO(new DoctorDAOMemory());
        presenter.setPatientDAO(new PatientDAOMemory());
        presenter.setSubstanceDAO(new ActiveSubstanceDAOMemory());
        presenter.setReportDAO(new ReportObjectDAOMemory());
    }

    /**
     * Επιστρέφει τον presenter του activity αυτού.
     * @return ο presenter του activity αυτού
     */
    public PrescriptionCreationPresenter getPresenter() {
        return presenter;
    }

    /**
     * Για debugging είναι η μέθοδος αυτή.
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("PrescriptionSelection", "On Cleared");
    }
}
