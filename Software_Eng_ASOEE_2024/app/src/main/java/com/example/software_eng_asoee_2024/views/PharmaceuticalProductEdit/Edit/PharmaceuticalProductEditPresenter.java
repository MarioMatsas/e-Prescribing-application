package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Edit;

import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;

import java.util.Objects;


public class PharmaceuticalProductEditPresenter {
    private PharmaceuticalProductEditView view;
    private PharmaceuticalProductDAOMemory pharmaceuticalProductDAO;
    private ActiveSubstanceDAOMemory activeSubstanceDAO;

    public PharmaceuticalProductEditView getView() {
        return view;
    }

    public void setView(PharmaceuticalProductEditView view) {
        this.view = view;
    }

    public void editPharmaceuticalProduct(PharmaceuticalProduct oldPp, PharmaceuticalProduct newPp) {
        for(PharmaceuticalProduct tempAc : pharmaceuticalProductDAO.findAll()) {
            if(tempAc.equals(newPp) && !tempAc.equals(oldPp)) throw new IllegalArgumentException("Cant have two identical pharmaceutical products");
        }
        this.pharmaceuticalProductDAO.delete(oldPp);
        this.pharmaceuticalProductDAO.save(newPp);
        createPharmaceuticalProductSpinner();
    }

    public void setPharmaceuticalProductDAO(PharmaceuticalProductDAOMemory actSubsDAO) {
        this.pharmaceuticalProductDAO = actSubsDAO;
    }

    public void createPharmaceuticalProductSpinner() {
        view.createPharmaceuticalProductSpinner(pharmaceuticalProductDAO.findAll());
    }

    public void setActiveSubstanceDAO(ActiveSubstanceDAOMemory activeSubstanceDAOMemory) {
        this.activeSubstanceDAO = activeSubstanceDAOMemory;
    }

    public void createActiveSubstanceSpinner() {
        view.createActiveSubstanceSpinner(activeSubstanceDAO.findAll());
    }
}
