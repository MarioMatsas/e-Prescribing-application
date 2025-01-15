package com.example.software_eng_asoee_2024.views.NOHCS;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;


public class NOHCSSelectionPresenter {
    private NOHCSSelectionView view;
    private ActiveSubstanceDAOMemory activeSubstanceDAO;//to add the new ActiveSubstance

    public NOHCSSelectionView getView() {
        return view;
    }

    public void setView(NOHCSSelectionView view) {
        this.view = view;
    }

    public void setActiveSubstanceDAO(ActiveSubstanceDAOMemory actSubsDAO) {
        this.activeSubstanceDAO = actSubsDAO;
    }
}
