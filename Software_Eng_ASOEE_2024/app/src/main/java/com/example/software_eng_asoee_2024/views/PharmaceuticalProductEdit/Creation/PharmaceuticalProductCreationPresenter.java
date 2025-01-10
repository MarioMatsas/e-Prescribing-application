package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Creation;

import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;

import java.util.Objects;


public class PharmaceuticalProductCreationPresenter {
    private PharmaceuticalProductCreationView view;
    private PharmaceuticalProductDAOMemory pharmaceuticalProductDAO;//to add the new PharmaceuticalProduct

    public PharmaceuticalProductCreationView getView() {
        return view;
    }

    public void setView(PharmaceuticalProductCreationView view) {
        this.view = view;
    }

    public void createPharmaceuticalProduct(PharmaceuticalProduct ac) {
        for(PharmaceuticalProduct tempAc : pharmaceuticalProductDAO.findAll()) {
            if(Objects.equals(ac.getName(), tempAc.getName())) throw new IllegalArgumentException("Cant have two active substances with same name!");
        }
        this.pharmaceuticalProductDAO.save(ac);
    }

    public void setPharmaceuticalProductDAO(PharmaceuticalProductDAOMemory actSubsDAO) {
        this.pharmaceuticalProductDAO = actSubsDAO;
    }
}
