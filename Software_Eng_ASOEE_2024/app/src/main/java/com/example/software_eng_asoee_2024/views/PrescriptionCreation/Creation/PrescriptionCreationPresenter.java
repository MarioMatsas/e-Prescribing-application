package com.example.software_eng_asoee_2024.views.PrescriptionCreation.Creation;

import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;
import com.example.software_eng_asoee_2024.views.PrescriptionCreation.Creation.PrescriptionCreationView;

public class PrescriptionCreationPresenter {
    private PrescriptionCreationView view;
    private PrescriptionDAOMemory prescriptionDAO;
    private ActiveSubstanceDAOMemory activeSubstanceDAO;

    public PrescriptionCreationView getView() {
        return view;
    }

    public void setView(PrescriptionCreationView view) {
        this.view = view;
    }

    public void init(Prescription prescription, Doctor doctor){

    }

    public void setPrescriptionDAO(PrescriptionDAOMemory prescriptionDAO) {
        this.prescriptionDAO = prescriptionDAO;
    }

    public Prescription getPrescription(int Id){
        return prescriptionDAO.findPrescriptionById(Id);
    }
}
