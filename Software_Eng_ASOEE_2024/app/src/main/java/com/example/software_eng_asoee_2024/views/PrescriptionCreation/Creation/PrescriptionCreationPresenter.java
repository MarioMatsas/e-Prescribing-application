package com.example.software_eng_asoee_2024.views.PrescriptionCreation.Creation;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.PrescriptionExecution;
import com.example.software_eng_asoee_2024.domain.PrescriptionLine;
import com.example.software_eng_asoee_2024.domain.Unit;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionCreationPresenter {
    private PrescriptionCreationView view;
    private PrescriptionDAOMemory prescriptionDAO;//to add the new prescription
    private ActiveSubstanceDAOMemory activeSubstanceDAO;//to get all the active substances
    private Prescription prescription;

    public PrescriptionCreationView getView() {
        return view;
    }

    public void setView(PrescriptionCreationView view) {
        this.view = view;
    }

    public void init(Doctor doc, Patient p, String diag){//to initialize the prescription so we cn add each line with the help of addline(..)
        prescription = new Prescription(diag, doc, p);
    }

    public boolean addPrescriptionLine(ActiveSubstance as, Form f, Unit u, String qty, String instr){//maybe it can be void
        try {
            if( (as == null) || (f == null) || (u == null) || qty.isEmpty() || instr.isEmpty() ) {//if there is a field with no item
                view.showError("Fill all the fields!");
                return false;
            }
            //create prescription line object and add it
            Integer conc_qty = (Integer) Integer.parseInt(qty);
            Concentration conc = new Concentration(conc_qty, u);
            prescription.addLine(new PrescriptionLine(f, conc, instr, as));//add the line in the current description

            return true;//everything is ok

        } catch (NumberFormatException e) {
//            view.showError("Quantity Number Out-Of-Bounds");
            view.showError("Give proper Quantity");
            return false;
        }
    }


    public void setPrescriptionDAO(PrescriptionDAOMemory prescriptionDAO) {
        this.prescriptionDAO = prescriptionDAO;
    }

    public void setActiveSubstanceDAO(ActiveSubstanceDAOMemory actSubsDAO) {
        this.activeSubstanceDAO = actSubsDAO;
    }

//    public Prescription getPrescription(){
//        return prescription;
//    }

    public boolean registerPrescription(){
        if(!prescription.getPrescriptionLines().isEmpty()){
            prescriptionDAO.save(prescription);
            return true;
        }

        return false;
    }

    public Form[] initFormSpinner(){
        return Form.values();
    }

    public Unit[] initUnitSpinner(){
        return Unit.values();
    }

    public List<ActiveSubstance> initActiveSubstanceSpinner(){
        return activeSubstanceDAO.findAll();
    }
}
