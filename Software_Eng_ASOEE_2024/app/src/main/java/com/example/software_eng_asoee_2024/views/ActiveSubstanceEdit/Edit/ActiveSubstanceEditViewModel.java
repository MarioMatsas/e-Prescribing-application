package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Edit;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;

public class ActiveSubstanceEditViewModel extends ViewModel {
    private ActiveSubstanceEditPresenter presenter;

    public ActiveSubstanceEditViewModel(){
        presenter = new ActiveSubstanceEditPresenter();
        presenter.setActiveSubstanceDAO(new ActiveSubstanceDAOMemory());
    }

    public ActiveSubstanceEditPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("ActiveSubstanceEdit", "On Cleared");
    }
}
