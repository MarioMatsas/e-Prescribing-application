package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Edit;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.MedicineType;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;

import java.util.ArrayList;
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

    public void editPharmaceuticalProduct(PharmaceuticalProduct selected, String pharmaceuticalProductName, String retailPrice, Form form, MedicineType type, ArrayList<ActiveSubstance> activeSubstanceList, ArrayList<Concentration> concentrationList, String information) {
        try {
            if(selected == null)
                throw new IllegalArgumentException("None selected to be delete");
            if (pharmaceuticalProductName.isEmpty() || retailPrice.isEmpty())
                throw new IllegalArgumentException("Not all fields are filled in");
            if(activeSubstanceList.isEmpty())
                throw new IllegalArgumentException("No Active Substances have been given");

            PharmaceuticalProduct pp = new PharmaceuticalProduct(pharmaceuticalProductName, Integer.parseInt(retailPrice), form, type, activeSubstanceList, concentrationList, information);
            for(PharmaceuticalProduct tempAc : pharmaceuticalProductDAO.findAll()) {
                if(tempAc.equals(pp) && !tempAc.equals(selected)) throw new IllegalArgumentException("Cant have two identical pharmaceutical products");
            }
            this.pharmaceuticalProductDAO.delete(selected);
            this.pharmaceuticalProductDAO.save(pp);
            createPharmaceuticalProductSpinner();

            view.showMessage("Done!");
        } catch (NumberFormatException e) {
            view.showMessage("Expected Quantity Per Month should be a number");
        } catch (Exception e) {
            view.showMessage(e.getMessage());
        }
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
