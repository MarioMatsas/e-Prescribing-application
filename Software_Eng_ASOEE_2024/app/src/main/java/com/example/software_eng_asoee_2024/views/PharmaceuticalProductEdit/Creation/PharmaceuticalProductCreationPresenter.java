package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Creation;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;

import java.util.ArrayList;
import java.util.Objects;


public class PharmaceuticalProductCreationPresenter {
    private PharmaceuticalProductCreationView view;
    private PharmaceuticalProductDAOMemory pharmaceuticalProductDAO;
    private ActiveSubstanceDAOMemory activeSubstanceDAO;

    public PharmaceuticalProductCreationView getView() {
        return view;
    }

    public void setView(PharmaceuticalProductCreationView view) {
        this.view = view;
    }

    public void createPharmaceuticalProduct(PharmaceuticalProduct ac) {
        for(PharmaceuticalProduct tempAc : pharmaceuticalProductDAO.findAll()) {
            if(tempAc.equals(ac)) throw new IllegalArgumentException("Cant have two identical active substances");
        }
        this.pharmaceuticalProductDAO.save(ac);
    }

    public void setPharmaceuticalProductDAO(PharmaceuticalProductDAOMemory pharmaceuticalProductDAO) {
        this.pharmaceuticalProductDAO = pharmaceuticalProductDAO;
    }

    public void createActiveSubstanceSpinner() {
        view.createActiveSubstanceSpinner(activeSubstanceDAO.findAll());
    }

    public void setActiveSubstanceDAO(ActiveSubstanceDAOMemory activeSubstanceDAOMemory) {
        this.activeSubstanceDAO = activeSubstanceDAOMemory;
    }
}
