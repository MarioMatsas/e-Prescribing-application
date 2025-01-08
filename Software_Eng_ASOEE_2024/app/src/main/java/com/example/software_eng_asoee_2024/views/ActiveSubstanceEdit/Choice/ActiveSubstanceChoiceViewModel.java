package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Choice;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;

public class ActiveSubstanceChoiceViewModel extends ViewModel {
    private ActiveSubstanceChoicePresenter presenter;

    public ActiveSubstanceChoiceViewModel(){
        presenter = new ActiveSubstanceChoicePresenter();
    }

    public ActiveSubstanceChoicePresenter getPresenter() {
        return presenter;
    }
}
