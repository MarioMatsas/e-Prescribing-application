package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Choice;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;


public class ActiveSubstanceChoicePresenter {
    private ActiveSubstanceChoiceView view;
    private ActiveSubstanceDAOMemory activeSubstanceDAO;//to add the new ActiveSubstance

    public ActiveSubstanceChoiceView getView() {
        return view;
    }

    public void setView(ActiveSubstanceChoiceView view) {
        this.view = view;
    }

    public boolean createActiveSubstance(String name, Double expectedQuantityPerMonth) {
        this.activeSubstanceDAO.save(new ActiveSubstance(name, expectedQuantityPerMonth));
        return true;
    }

    public void setActiveSubstanceDAO(ActiveSubstanceDAOMemory actSubsDAO) {
        this.activeSubstanceDAO = actSubsDAO;
    }
}
