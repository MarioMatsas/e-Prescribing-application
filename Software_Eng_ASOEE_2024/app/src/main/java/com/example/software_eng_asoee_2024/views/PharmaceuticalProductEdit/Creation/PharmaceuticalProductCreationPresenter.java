package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Creation;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.MedicineType;
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

    public void createPharmaceuticalProduct(String pharmaceuticalProductName, String retailPrice, Form form, MedicineType type, ArrayList<ActiveSubstance> activeSubstanceList, ArrayList<Concentration> concentrationList, String information) {
        try {
            if (pharmaceuticalProductName.isEmpty() || retailPrice.isEmpty())
                throw new IllegalArgumentException("Not all fields are filled in");
            if(activeSubstanceList.isEmpty())
                throw new IllegalArgumentException("No Active Substances have been given");
            PharmaceuticalProduct pp = new PharmaceuticalProduct(pharmaceuticalProductName, Integer.parseInt(retailPrice), form, type, activeSubstanceList, concentrationList, information);

            for(PharmaceuticalProduct tempPp : pharmaceuticalProductDAO.findAll()) {
                if(tempPp.equals(pp)) throw new IllegalArgumentException("Cant have two identical pharmaceutical products");
            }
            this.pharmaceuticalProductDAO.save(pp);

            view.showMessage("Done!");
        } catch (NumberFormatException e) {
            view.showMessage("Retail Price should be a number");
        } catch (Exception e) {
            view.showMessage(e.getMessage());
        }
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
