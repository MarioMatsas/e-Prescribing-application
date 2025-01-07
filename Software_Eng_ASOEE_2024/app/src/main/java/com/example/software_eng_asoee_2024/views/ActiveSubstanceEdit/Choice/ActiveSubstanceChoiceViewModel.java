package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Choice;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;

public class ActiveSubstanceChoiceViewModel extends ViewModel {
    private ActiveSubstanceChoicePresenter presenter;

    public ActiveSubstanceChoiceViewModel(){
        presenter = new ActiveSubstanceChoicePresenter();
        presenter.setActiveSubstanceDAO(new ActiveSubstanceDAOMemory());
    }

    public ActiveSubstanceChoicePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("ActiveSubstanceCreation", "On Cleared");
    }
}
