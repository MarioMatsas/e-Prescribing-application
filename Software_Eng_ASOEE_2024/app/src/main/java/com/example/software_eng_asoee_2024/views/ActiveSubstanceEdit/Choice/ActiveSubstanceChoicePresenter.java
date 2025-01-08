package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Choice;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;


public class ActiveSubstanceChoicePresenter {
    private ActiveSubstanceChoiceView view;

    public ActiveSubstanceChoiceView getView() {
        return view;
    }

    public void setView(ActiveSubstanceChoiceView view) {
        this.view = view;
    }
}
