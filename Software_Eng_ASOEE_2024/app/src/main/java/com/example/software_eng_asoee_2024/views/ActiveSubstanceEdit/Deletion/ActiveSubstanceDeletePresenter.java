package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Deletion;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;


public class ActiveSubstanceDeletePresenter {
    private ActiveSubstanceDeleteView view;
    private ActiveSubstanceDAOMemory activeSubstanceDAO;//to add the new ActiveSubstance

    public ActiveSubstanceDeleteView getView() {
        return view;
    }

    public void setView(ActiveSubstanceDeleteView view) {
        this.view = view;
    }

    public void deleteActiveSubstance(ActiveSubstance ac) {
        this.activeSubstanceDAO.delete(ac);
        createActiveSubstanceSpinner();
    }

    public void setActiveSubstanceDAO(ActiveSubstanceDAOMemory actSubsDAO) {
        this.activeSubstanceDAO = actSubsDAO;
    }

    public void createActiveSubstanceSpinner() {
        view.createActiveSubstanceSpinner(activeSubstanceDAO.findAll());
    }
}
