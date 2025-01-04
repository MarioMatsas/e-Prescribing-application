package com.example.software_eng_asoee_2024.views.PrescriptionCreation.PatientSearching;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.example.software_eng_asoee_2024.memorydao.PatientDAOMemory;
import com.example.software_eng_asoee_2024.views.PrescriptionCreation.PatientSearching.PatientSearchingPresenter;

public class PatientSearchingViewModel extends ViewModel{

    private PatientSearchingPresenter presenter;

    public PatientSearchingViewModel() {
        presenter = new PatientSearchingPresenter();
        presenter.setPatientDAO(new PatientDAOMemory());
    }

    public PatientSearchingPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
