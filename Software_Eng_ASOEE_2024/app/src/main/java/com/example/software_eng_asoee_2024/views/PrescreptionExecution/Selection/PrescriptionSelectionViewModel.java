package com.example.software_eng_asoee_2024.views.PrescreptionExecution.Selection;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.software_eng_asoee_2024.memorydao.PatientDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;

public class PrescriptionSelectionViewModel extends ViewModel{
    private PrescriptionSelectionPresenter presenter;

    public PrescriptionSelectionViewModel() {
        presenter = new PrescriptionSelectionPresenter();
        presenter.setPrescriptionDAO(new PrescriptionDAOMemory());
        presenter.setPatientDAO(new PatientDAOMemory());
    }

    public PrescriptionSelectionPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("PrescriptionSelection", "On Cleared");
    }
}
