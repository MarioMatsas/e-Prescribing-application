package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Deletion;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;


public class PharmaceuticalProductDeletePresenter {
    private PharmaceuticalProductDeleteView view;
    private PharmaceuticalProductDAOMemory pharmaceuticalProductDAO;//to add the new PharmaceuticalProduct

    public PharmaceuticalProductDeleteView getView() {
        return view;
    }

    public void setView(PharmaceuticalProductDeleteView view) {
        this.view = view;
    }

    public boolean deletePharmaceuticalProduct(PharmaceuticalProduct ac) {
        this.pharmaceuticalProductDAO.delete(ac);
        createPharmaceuticalProductSpinner();
        return(this.pharmaceuticalProductDAO.findAll().isEmpty());
    }

    public void setPharmaceuticalProductDAO(PharmaceuticalProductDAOMemory actSubsDAO) {
        this.pharmaceuticalProductDAO = actSubsDAO;
    }

    public void createPharmaceuticalProductSpinner() {
        view.createPharmaceuticalProductSpinner(pharmaceuticalProductDAO.findAll());
    }
}
