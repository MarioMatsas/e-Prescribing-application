package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Deletion;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;

public class ActiveSubstanceDeleteViewModel extends ViewModel {
    private ActiveSubstanceDeletePresenter presenter;

    public ActiveSubstanceDeleteViewModel(){
        presenter = new ActiveSubstanceDeletePresenter();
        presenter.setActiveSubstanceDAO(new ActiveSubstanceDAOMemory());
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
