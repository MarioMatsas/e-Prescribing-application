package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Edit;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;

import java.util.Objects;


public class ActiveSubstanceEditPresenter {
    private ActiveSubstanceEditView view;
    private ActiveSubstanceDAOMemory activeSubstanceDAO;//to add the new ActiveSubstance

    public ActiveSubstanceEditView getView() {
        return view;
    }

    public void setView(ActiveSubstanceEditView view) {
        this.view = view;
    }

    public void editActiveSubstance(ActiveSubstance oldAc, ActiveSubstance newAc) {
        if(!newAc.getName().equals(oldAc.getName())) {
            for (ActiveSubstance tempAc : activeSubstanceDAO.findAll()) {
                if (Objects.equals(newAc.getName(), tempAc.getName()))
                    throw new IllegalArgumentException("Cant have two active substances with same name!");
            }
        }
        this.activeSubstanceDAO.delete(oldAc);
        this.activeSubstanceDAO.save(newAc);
        createActiveSubstanceSpinner();
    }

    public void setActiveSubstanceDAO(ActiveSubstanceDAOMemory actSubsDAO) {
        this.activeSubstanceDAO = actSubsDAO;
    }

    public void createActiveSubstanceSpinner() {
        view.createActiveSubstanceSpinner(activeSubstanceDAO.findAll());
    }
}
