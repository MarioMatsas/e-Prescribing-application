package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Deletion;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.PrescriptionExecution;
import com.example.software_eng_asoee_2024.domain.PrescriptionLine;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;


public class ActiveSubstanceDeletePresenter {
    private ActiveSubstanceDeleteView view;
    private ActiveSubstanceDAOMemory activeSubstanceDAO;
    private PharmaceuticalProductDAOMemory pharmaceuticalProductDAO;
    private PrescriptionDAOMemory prescriptionDAO;

    public ActiveSubstanceDeleteView getView() {
        return view;
    }

    public void setView(ActiveSubstanceDeleteView view) {
        this.view = view;
    }

    public boolean deleteActiveSubstance(ActiveSubstance ac) {
        if(ac == null) {
            view.showMessage("None selected to be edited");
            return false;
        }

        for (PharmaceuticalProduct p : this.pharmaceuticalProductDAO.findAll()) {
            if(p.getActiveSubstances().contains(ac) && p.getActiveSubstances().size() == 1)
                this.pharmaceuticalProductDAO.delete(p);
        }

        for (Prescription p : this.prescriptionDAO.findAll()) {
            p.getPrescriptionLines().removeIf(pl -> pl.getActiveSubstance().equals(ac));
            if(p.getPrescriptionLines().isEmpty()) {

                prescriptionDAO.delete(p);
            }
        }

        this.activeSubstanceDAO.delete(ac);


        createActiveSubstanceSpinner();

        view.showMessage("Done!");
        return(this.activeSubstanceDAO.findAll().isEmpty());
    }

    public void setPharmaceuticalProductDAO(PharmaceuticalProductDAOMemory pharmaceuticalProductDAO) {
        this.pharmaceuticalProductDAO = pharmaceuticalProductDAO;
    }
    public void setPrescriptionDAO(PrescriptionDAOMemory prescriptionDAO) {
        this.prescriptionDAO = prescriptionDAO;
    }

    public void setActiveSubstanceDAO(ActiveSubstanceDAOMemory actSubsDAO) {
        this.activeSubstanceDAO = actSubsDAO;
    }

    public void createActiveSubstanceSpinner() {
        view.createActiveSubstanceSpinner(activeSubstanceDAO.findAll());
    }
}
