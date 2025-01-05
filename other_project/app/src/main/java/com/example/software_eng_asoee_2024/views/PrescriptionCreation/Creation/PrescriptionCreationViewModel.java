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

    public PrescriptionCreationViewModel() {
        presenter = new PrescriptionCreationPresenter();
        presenter.setPrescriptionDAO(new PrescriptionDAOMemory());
        presenter.setDoctorDAO(new DoctorDAOMemory());
        presenter.setPatientDAO(new PatientDAOMemory());
        presenter.setSubstanceDAO(new ActiveSubstanceDAOMemory());
        presenter.setReportDAO(new ReportObjectDAOMemory());
    }

    public PrescriptionCreationPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("PrescriptionSelection", "On Cleared");
    }
}
