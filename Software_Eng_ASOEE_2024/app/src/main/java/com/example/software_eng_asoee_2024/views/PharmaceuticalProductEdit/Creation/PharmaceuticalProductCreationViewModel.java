package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Creation;

import android.util.Log;
import androidx.lifecycle.ViewModel;

import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;

public class PharmaceuticalProductCreationViewModel extends ViewModel {
    private PharmaceuticalProductCreationPresenter presenter;

    public PharmaceuticalProductCreationViewModel(){
        presenter = new PharmaceuticalProductCreationPresenter();
        presenter.setPharmaceuticalProductDAO(new PharmaceuticalProductDAOMemory());
        presenter.setActiveSubstanceDAO(new ActiveSubstanceDAOMemory());
    }

    public PharmaceuticalProductCreationPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("PharmaceuticalProductCreation", "On Cleared");
    }
}
