package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Creation;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;

import java.util.Objects;


public class ActiveSubstanceCreationPresenter {
    private ActiveSubstanceCreationView view;
    private ActiveSubstanceDAOMemory activeSubstanceDAO;//to add the new ActiveSubstance

    public ActiveSubstanceCreationView getView() {
        return view;
    }

    public void setView(ActiveSubstanceCreationView view) {
        this.view = view;
    }

    /**
     * Creates ands adds a new active substance to the DAO.
     *
     * Validates that the "expected quantity per month" is a double.
     * Checks for duplicate names.
     *
     * @param ActiveSubstanceName the name of the active substance
     * @param ExpectedQuantityPerMonth the expected quantity per month (as a String)
     */
    public void createActiveSubstance(String ActiveSubstanceName, String ExpectedQuantityPerMonth) {
        try {
            ActiveSubstance ac = new ActiveSubstance(ActiveSubstanceName, Double.parseDouble(ExpectedQuantityPerMonth));
            for(ActiveSubstance tempAc : activeSubstanceDAO.findAll()) {
                if(Objects.equals(ac.getName(), tempAc.getName())) throw new IllegalArgumentException("Cant have two active substances with same name!");
            }
            this.activeSubstanceDAO.save(ac);
            this.view.showMessage("Done!");
        } catch (NumberFormatException e) {
            this.view.showMessage("Expected Quantity Per Month should be a number");
        } catch (Exception e) {
            this.view.showMessage(e.getMessage());
        }
    }

    public void setActiveSubstanceDAO(ActiveSubstanceDAOMemory actSubsDAO) {
        this.activeSubstanceDAO = actSubsDAO;
    }
}
