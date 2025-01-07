package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Creation;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;


public class ActiveSubstanceCreationPresenter {
    private ActiveSubstanceCreationView view;
    private ActiveSubstanceDAOMemory activeSubstanceDAO;//to add the new ActiveSubstance

    public ActiveSubstanceCreationView getView() {
        return view;
    }

    public void setView(ActiveSubstanceCreationView view) {
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
