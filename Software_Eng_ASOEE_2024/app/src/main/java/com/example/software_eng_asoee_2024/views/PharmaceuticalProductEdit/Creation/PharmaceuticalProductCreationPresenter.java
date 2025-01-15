package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Creation;

import static com.example.software_eng_asoee_2024.domain.Form.CREAM;
import static com.example.software_eng_asoee_2024.domain.Form.PILL;
import static com.example.software_eng_asoee_2024.domain.Form.SPRAY;
import static com.example.software_eng_asoee_2024.domain.Form.SYRUP;

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


public class PharmaceuticalProductCreationPresenter {
    private PharmaceuticalProductCreationView view;
    private PharmaceuticalProductDAO pharmaceuticalProductDAO;
    private ActiveSubstanceDAO activeSubstanceDAO;

    public PharmaceuticalProductCreationView getView() {
        return view;
    }

    public void setView(PharmaceuticalProductCreationView view) {
        this.view = view;
    }

    /**
     * Creates and saves a new pharmaceutical product.

     * Validates input, checks for duplicate products (excluding the product being edited), creates
     * the product object and saves it to the DAO.

     * @param pharmaceuticalProductName the name of the pharmaceutical product
     * @param retailPrice the retail price (as a String)
     * @param form the form of the product (e.g., TABLET, CAPSULE)
     * @param type the type of medicine (e.g., ANALGESIC, ANTIBIOTIC)
     * @param activeSubstanceList the list of active substances in the product
     * @param concentrationList the list of concentrations for each active substance
     * @param information additional information about the product
     */
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

    public void setPharmaceuticalProductDAO(PharmaceuticalProductDAO pharmaceuticalProductDAO) {
        this.pharmaceuticalProductDAO = pharmaceuticalProductDAO;
    }

    /**
     * Creates the active substance spinner in the view.
     */
    public void createActiveSubstanceSpinner() {
        view.createActiveSubstanceSpinner(activeSubstanceDAO.findAll());
    }

    public void setActiveSubstanceDAO(ActiveSubstanceDAO activeSubstanceDAOMemory) {
        this.activeSubstanceDAO = activeSubstanceDAOMemory;
    }

    /**
     * Adds an active substance and its corresponding concentration to the product.

     * This method adds the selected active substance from the spinner
     * and the specified concentration to the respective lists.

     * It then updates the UI by creating the active substance list
     * and refreshing the active substance spinner.

     * @param subSpin the selected active substance from the spinner
     * @param conc the concentration of the active substance
     * @param activeSubstanceList the list of active substances in the product
     * @param concList the list of corresponding concentrations in the product
     * @param unit the unit of measurement for the concentration
     */
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
            view.showMessage("Concentration should be a number");
        } catch (Exception e) {
            view.showMessage(e.getMessage());
        }
    }

    /**
     * Helper function to determine unit of measurement based on each form.
     *
     * @param form the selected form of the medication
     * @return the unit for the given form
     */
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
