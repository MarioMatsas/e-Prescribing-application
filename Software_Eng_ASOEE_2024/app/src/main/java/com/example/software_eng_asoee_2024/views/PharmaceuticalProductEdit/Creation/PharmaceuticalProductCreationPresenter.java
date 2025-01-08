package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Creation;

import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;


public class PharmaceuticalProductCreationPresenter {
    private PharmaceuticalProductCreationView view;
    private PharmaceuticalProductDAOMemory activeSubstanceDAO;//to add the new PharmaceuticalProduct

    public PharmaceuticalProductCreationView getView() {
        return view;
    }

    public void setView(PharmaceuticalProductCreationView view) {
        this.view = view;
    }

    public boolean createPharmaceuticalProduct(String name, Double expectedQuantityPerMonth) {
//        this.activeSubstanceDAO.save(new PharmaceuticalProduct(name, expectedQuantityPerMonth));
        return true;
    }

    public void setPharmaceuticalProductDAO(PharmaceuticalProductDAOMemory actSubsDAO) {
        this.activeSubstanceDAO = actSubsDAO;
    }
}
