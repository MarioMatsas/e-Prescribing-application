package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Edit;

import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;

import java.util.Objects;


public class PharmaceuticalProductEditPresenter {
    private PharmaceuticalProductEditView view;
    private PharmaceuticalProductDAOMemory pharmaceuticalProductDAO;//to add the new PharmaceuticalProduct

    public PharmaceuticalProductEditView getView() {
        return view;
    }

    public void setView(PharmaceuticalProductEditView view) {
        this.view = view;
    }

    public void editPharmaceuticalProduct(PharmaceuticalProduct oldAc, PharmaceuticalProduct newAc) {
        if(!newAc.getName().equals(oldAc.getName())) {
            for (PharmaceuticalProduct tempAc : pharmaceuticalProductDAO.findAll()) {
                if (Objects.equals(newAc.getName(), tempAc.getName()))
                    throw new IllegalArgumentException("Cant have two active substances with same name!");
            }
        }
        this.pharmaceuticalProductDAO.delete(oldAc);
        this.pharmaceuticalProductDAO.save(newAc);
        createPharmaceuticalProductSpinner();
    }

    public void setPharmaceuticalProductDAO(PharmaceuticalProductDAOMemory actSubsDAO) {
        this.pharmaceuticalProductDAO = actSubsDAO;
    }

    public void createPharmaceuticalProductSpinner() {
        view.createPharmaceuticalProductSpinner(pharmaceuticalProductDAO.findAll());
    }
}
