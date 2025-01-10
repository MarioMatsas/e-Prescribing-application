package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Edit;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;

import java.util.Objects;


public class ActiveSubstanceEditPresenter {
    private ActiveSubstanceEditView view;
    private ActiveSubstanceDAOMemory activeSubstanceDAO;//to add the new ActiveSubstance

    public ActiveSubstanceEditView getView() {
        return view;
    }

    public void setView(ActiveSubstanceEditView view) {
        this.view = view;
    }

    public void editActiveSubstance(ActiveSubstance selected, String name, String eqpm) {
        if(selected == null) throw new IllegalArgumentException("None selected to be edited");
        try {
            if (name.isEmpty() || eqpm.isEmpty())
                throw new IllegalArgumentException("Not all fields are filled in");

            ActiveSubstance newAc = new ActiveSubstance(name, Double.parseDouble(eqpm));

            if(!newAc.getName().equals(selected.getName())) {
                for (ActiveSubstance tempAc : activeSubstanceDAO.findAll()) {
                    if (Objects.equals(newAc.getName(), tempAc.getName()))
                        throw new IllegalArgumentException("Cant have two active substances with same name!");
                }
            }

            this.activeSubstanceDAO.delete(selected);
            this.activeSubstanceDAO.save(newAc);
            createActiveSubstanceSpinner();
            view.showMessage("Done!");
        } catch (NumberFormatException e) {
            view.showMessage("Expected Quantity Per Month should be a number");
        } catch (Exception e) {
            view.showMessage(e.getMessage());

        }
    }

    public void setActiveSubstanceDAO(ActiveSubstanceDAOMemory actSubsDAO) {
        this.activeSubstanceDAO = actSubsDAO;
    }

    public void createActiveSubstanceSpinner() {
        view.createActiveSubstanceSpinner(activeSubstanceDAO.findAll());
    }
}
