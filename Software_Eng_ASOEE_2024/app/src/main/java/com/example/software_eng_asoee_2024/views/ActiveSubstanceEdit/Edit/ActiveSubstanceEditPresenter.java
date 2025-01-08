package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Edit;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;


public class ActiveSubstanceEditPresenter {
    private ActiveSubstanceEditView view;
    private ActiveSubstanceDAOMemory activeSubstanceDAO;//to add the new ActiveSubstance

    public ActiveSubstanceEditView getView() {
        return view;
    }

    public void setView(ActiveSubstanceEditView view) {
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
