package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Edit;

import com.example.software_eng_asoee_2024.dao.ActiveSubstanceDAO;
import com.example.software_eng_asoee_2024.dao.PharmaceuticalProductDAO;
import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.MedicineType;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.domain.Unit;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;

import java.util.ArrayList;
import java.util.Objects;


public class PharmaceuticalProductEditPresenter {
    private PharmaceuticalProductEditView view;
    private PharmaceuticalProductDAO pharmaceuticalProductDAO;
    private ActiveSubstanceDAO activeSubstanceDAO;

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

            this.pharmaceuticalProductDAO.edit(selected, pp);
            createPharmaceuticalProductSpinner();

            view.showMessage("Done!");
        } catch (NumberFormatException e) {
            view.showMessage("Retail Price should be a number");
        } catch (Exception e) {
            view.showMessage(e.getMessage());
        }
    }

    public void setPharmaceuticalProductDAO(PharmaceuticalProductDAO actSubsDAO) {
        this.pharmaceuticalProductDAO = actSubsDAO;
    }

    public void createPharmaceuticalProductSpinner() {
        view.createPharmaceuticalProductSpinner(pharmaceuticalProductDAO.findAll());
    }

    public void setActiveSubstanceDAO(ActiveSubstanceDAO activeSubstanceDAOMemory) {
        this.activeSubstanceDAO = activeSubstanceDAOMemory;
    }

    public void createActiveSubstanceSpinner() {
        view.createActiveSubstanceSpinner(activeSubstanceDAO.findAll());
    }

    public void addSubToProduct(Object subSpin, String conc, ArrayList<ActiveSubstance> activeSubstanceList,
                                ArrayList<Concentration> concList, Unit unit){
        if(subSpin == null) return;
        try {
            if (conc.isEmpty())
                throw new IllegalArgumentException("Concentration isn't filled in");
            activeSubstanceList.add((ActiveSubstance) subSpin);
            concList.add(new Concentration(Double.parseDouble(conc), unit));
            view.createActiveSubstanceList();
            createActiveSubstanceSpinner();
            view.showMessage("Added!");
        } catch (NumberFormatException e) {
            System.out.println("ERROR1");
            view.showMessage("Concentration should be a number");
        } catch (Exception e) {
            System.out.println("ERROR2");
            view.showMessage(e.getMessage());
        }
    }

    public Unit onSelectedUnit(Form form){
        Unit selectedUnit = null;
        switch (form) {
            case PILL:
                selectedUnit = Unit.mg_per_disk;
                break;
            case CREAM:
                selectedUnit = Unit.mg_per_g;
                break;
            case SPRAY:
                selectedUnit = Unit.mg_per_dose;
                break;
            case SYRUP:
                selectedUnit = Unit.mg_per_ml;
                break;
            default:
                break;
        }
        return selectedUnit;
    }
}
