package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Creation;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.PrescriptionLine;
import com.example.software_eng_asoee_2024.domain.Unit;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;

import java.util.List;

public class ActiveSubstanceCreationPresenter {
    private ActiveSubstanceCreationView view;
    private ActiveSubstanceDAOMemory activeSubstanceDAO;//to add the new ActiveSubstance
    private Prescription prescription;

    public ActiveSubstanceCreationView getView() {
        return view;
    }

    public void setView(ActiveSubstanceCreationView view) {
        this.view = view;
    }

    public void init(Doctor doc, Patient p, String diag){//to initialize the prescription so we cn add each line with the help of addline(..)
        prescription = new Prescription(diag, doc, p);
    }

    public boolean createActiveSubstance(String name, Double expectedQuantityPerMonth) {
        this.activeSubstanceDAO.save(new ActiveSubstance(name, expectedQuantityPerMonth));
        return true;
    }

    public void setActiveSubstanceDAO(ActiveSubstanceDAOMemory actSubsDAO) {
        this.activeSubstanceDAO = actSubsDAO;
    }
}
