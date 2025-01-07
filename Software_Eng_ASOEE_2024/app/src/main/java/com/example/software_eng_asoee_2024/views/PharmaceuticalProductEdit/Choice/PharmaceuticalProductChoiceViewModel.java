package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Choice;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.software_eng_asoee_2024.dao.PharmaceuticalProductDAO;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;

public class PharmaceuticalProductChoiceViewModel extends ViewModel {
    private PharmaceuticalProductChoicePresenter presenter;

    public PharmaceuticalProductChoiceViewModel(){
        presenter = new PharmaceuticalProductChoicePresenter();
        presenter.setPharmaceuticalProductDAO(new PharmaceuticalProductDAOMemory());
    }

    public PharmaceuticalProductChoicePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("ActiveSubstanceCreation", "On Cleared");
    }
}
