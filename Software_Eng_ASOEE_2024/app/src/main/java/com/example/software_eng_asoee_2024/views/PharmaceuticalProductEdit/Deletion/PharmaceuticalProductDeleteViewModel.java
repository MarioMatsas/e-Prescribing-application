package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Deletion;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionExecutionDAOMemory;

public class PharmaceuticalProductDeleteViewModel extends ViewModel {
    private PharmaceuticalProductDeletePresenter presenter;

    public PharmaceuticalProductDeleteViewModel(){
        presenter = new PharmaceuticalProductDeletePresenter();
        presenter.setPharmaceuticalProductDAO(new PharmaceuticalProductDAOMemory());
        presenter.setPrescriptionExecutionDAO(new PrescriptionExecutionDAOMemory());
    }

    public PharmaceuticalProductDeletePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("PharmaceuticalProductDeletion", "On Cleared");
    }
}
