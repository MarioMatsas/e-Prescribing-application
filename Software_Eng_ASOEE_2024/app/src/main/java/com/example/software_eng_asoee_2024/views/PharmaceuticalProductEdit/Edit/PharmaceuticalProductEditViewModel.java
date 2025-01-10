package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Edit;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;

public class PharmaceuticalProductEditViewModel extends ViewModel {
    private PharmaceuticalProductEditPresenter presenter;

    public PharmaceuticalProductEditViewModel(){
        presenter = new PharmaceuticalProductEditPresenter();
        presenter.setPharmaceuticalProductDAO(new PharmaceuticalProductDAOMemory());
    }

    public PharmaceuticalProductEditPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("PharmaceuticalProductEdit", "On Cleared");
    }
}
