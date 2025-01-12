package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Deletion;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;

public class ActiveSubstanceDeleteViewModel extends ViewModel {
    private ActiveSubstanceDeletePresenter presenter;

    public ActiveSubstanceDeleteViewModel(){
        presenter = new ActiveSubstanceDeletePresenter();
        presenter.setActiveSubstanceDAO(new ActiveSubstanceDAOMemory());
        presenter.setPrescriptionDAO(new PrescriptionDAOMemory());
        presenter.setPharmaceuticalProductDAO(new PharmaceuticalProductDAOMemory());
    }

    public ActiveSubstanceDeletePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("ActiveSubstanceDeletion", "On Cleared");
    }
}
