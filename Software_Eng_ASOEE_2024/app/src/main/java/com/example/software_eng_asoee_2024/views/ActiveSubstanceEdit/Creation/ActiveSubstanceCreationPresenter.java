package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Creation;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;

import java.util.Objects;


public class ActiveSubstanceCreationPresenter {
    private ActiveSubstanceCreationView view;
    private ActiveSubstanceDAOMemory activeSubstanceDAO;//to add the new ActiveSubstance

    public ActiveSubstanceCreationView getView() {
        return view;
    }

    public void setView(ActiveSubstanceCreationView view) {
        this.view = view;
    }

    public void createActiveSubstance(ActiveSubstance ac) {
        for(ActiveSubstance tempAc : activeSubstanceDAO.findAll()) {
            if(Objects.equals(ac.getName(), tempAc.getName())) throw new IllegalArgumentException("Cant have two active substances with same name!");
        }
        this.activeSubstanceDAO.save(ac);
    }

    public void setActiveSubstanceDAO(ActiveSubstanceDAOMemory actSubsDAO) {
        this.activeSubstanceDAO = actSubsDAO;
    }
}
