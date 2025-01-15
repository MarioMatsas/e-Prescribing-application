package com.example.software_eng_asoee_2024.views.NOHCS;

import com.example.software_eng_asoee_2024.dao.ActiveSubstanceDAO;
import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;


public class NOHCSSelectionPresenter {
    private NOHCSSelectionView view;

    public NOHCSSelectionView getView() {
        return view;
    }

    public void setView(NOHCSSelectionView view) {
        this.view = view;
    }

}
