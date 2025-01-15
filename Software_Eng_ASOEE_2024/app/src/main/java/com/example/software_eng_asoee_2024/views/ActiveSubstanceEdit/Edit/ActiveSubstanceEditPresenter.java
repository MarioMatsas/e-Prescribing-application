package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Edit;

import com.example.software_eng_asoee_2024.dao.ActiveSubstanceDAO;
import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;

import java.util.Objects;


public class ActiveSubstanceEditPresenter {
    private ActiveSubstanceEditView view;
    private ActiveSubstanceDAO activeSubstanceDAO;//to add the new ActiveSubstance

    public ActiveSubstanceEditView getView() {
        return view;
    }

    public void setView(ActiveSubstanceEditView view) {
        this.view = view;
    }

    /**
     * Edits the selected active substance with the data given.

     * Validates the input data, checks for duplicate names,
     * updates the active substance in the DAO and refreshes the UI.

     * @param selected the active substance to be edited
     * @param name the new name for the active substance
     * @param eqpm the new expected quantity per month (as a String)
     */
    public void editActiveSubstance(ActiveSubstance selected, String name, String eqpm) {
        try {
            if(selected == null) throw new IllegalArgumentException("None selected to be edited");

            if (name.isEmpty() || eqpm.isEmpty())
                throw new IllegalArgumentException("Not all fields are filled in");

            ActiveSubstance newAc = new ActiveSubstance(name, Double.parseDouble(eqpm));

            if(!newAc.getName().equals(selected.getName())) {
                for (ActiveSubstance tempAc : activeSubstanceDAO.findAll()) {
                    if (Objects.equals(newAc.getName(), tempAc.getName()))
                        throw new IllegalArgumentException("Cant have two active substances with same name!");
                }
            }

            this.activeSubstanceDAO.edit(selected, newAc);
            createActiveSubstanceSpinner();
            view.showMessage("Done!");
        } catch (NumberFormatException e) {
            view.showMessage("Expected Quantity Per Month should be a number");
        } catch (Exception e) {
            view.showMessage(e.getMessage());
        }
    }

    public void setActiveSubstanceDAO(ActiveSubstanceDAO actSubsDAO) {
        this.activeSubstanceDAO = actSubsDAO;
    }

    /**
     * Creates the active substance spinner in the view.
     */
    public void createActiveSubstanceSpinner() {
        view.createActiveSubstanceSpinner(activeSubstanceDAO.findAll());
    }
}
