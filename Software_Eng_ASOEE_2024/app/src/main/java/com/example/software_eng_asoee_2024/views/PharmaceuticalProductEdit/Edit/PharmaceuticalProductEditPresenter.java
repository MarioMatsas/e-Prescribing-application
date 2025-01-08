package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Edit;

import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;


public class PharmaceuticalProductEditPresenter {
    private PharmaceuticalProductEditView view;
    private PharmaceuticalProductDAOMemory activeSubstanceDAO;//to add the new PharmaceuticalProduct

    public PharmaceuticalProductEditView getView() {
        return view;
    }

    public void setView(PharmaceuticalProductEditView view) {
        this.view = view;
    }

    public boolean createPharmaceuticalProduct(String name, Double expectedQuantityPerMonth) {
        this.activeSubstanceDAO.save(new PharmaceuticalProduct(name, expectedQuantityPerMonth));
        return true;
    }

    public void setPharmaceuticalProductDAO(PharmaceuticalProductDAOMemory actSubsDAO) {
        this.activeSubstanceDAO = actSubsDAO;
    }
}
