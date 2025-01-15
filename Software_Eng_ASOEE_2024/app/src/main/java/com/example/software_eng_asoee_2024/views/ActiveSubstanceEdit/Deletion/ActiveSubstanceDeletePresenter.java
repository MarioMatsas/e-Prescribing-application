package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Deletion;

import com.example.software_eng_asoee_2024.dao.ActiveSubstanceDAO;
import com.example.software_eng_asoee_2024.dao.PharmaceuticalProductDAO;
import com.example.software_eng_asoee_2024.dao.PrescriptionDAO;
import com.example.software_eng_asoee_2024.dao.PrescriptionExecutionDAO;
import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.PrescriptionExecution;
import com.example.software_eng_asoee_2024.domain.PrescriptionLine;
import com.example.software_eng_asoee_2024.domain.ProductQuantity;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionExecutionDAOMemory;


public class ActiveSubstanceDeletePresenter {
    private ActiveSubstanceDeleteView view;
    private ActiveSubstanceDAO activeSubstanceDAO;
    private PharmaceuticalProductDAO pharmaceuticalProductDAO;
    private PrescriptionDAO prescriptionDAO;
    private PrescriptionExecutionDAO prescriptionExecutionDAO;

    public ActiveSubstanceDeleteView getView() {
        return view;
    }

    public void setView(ActiveSubstanceDeleteView view) {
        this.view = view;
    }

    public boolean deleteActiveSubstance(ActiveSubstance ac) {
        if(ac == null) {
            view.showMessage("None selected to be deleted");
            return false;
        }

        for (PharmaceuticalProduct p : this.pharmaceuticalProductDAO.findAll()) {
            for(int i = 0; i < p.getActiveSubstances().size(); i++) {
                ActiveSubstance tmpAc = p.getActiveSubstances().get(i);
                if(tmpAc.equals(ac)) {
                    p.getActiveSubstances().remove(i);
                    p.getActiveSubstanceConcentrations().remove(i);
                    i--;
                }
            }
            if(p.getActiveSubstances().isEmpty()) {
                for (PrescriptionExecution px : prescriptionExecutionDAO.findAll()) {
                    px.getProductQuantities().removeIf(v -> v.getProduct().equals(p));

                    if(px.getProductQuantities().isEmpty())
                        this.prescriptionExecutionDAO.delete(px);
                }

                this.pharmaceuticalProductDAO.delete(p);
            }
        }

        for (Prescription p : this.prescriptionDAO.findAll()) {
            p.getPrescriptionLines().removeIf(pl -> pl.getActiveSubstance().equals(ac));
            if(p.getPrescriptionLines().isEmpty()) {
                for (PrescriptionExecution px : prescriptionExecutionDAO.findAll())
                    if(px.getPrescription().equals(p))
                        this.prescriptionExecutionDAO.delete(px);
                prescriptionDAO.delete(p);
            }
        }

        this.activeSubstanceDAO.delete(ac);


        createActiveSubstanceSpinner();

        view.showMessage("Done!");
        return(this.activeSubstanceDAO.findAll().isEmpty());
    }

    public void setPharmaceuticalProductDAO(PharmaceuticalProductDAO pharmaceuticalProductDAO) {
        this.pharmaceuticalProductDAO = pharmaceuticalProductDAO;
    }

    public void setPrescriptionExecutionDAO(PrescriptionExecutionDAO PrescriptionExecutionDAO) {
        this.prescriptionExecutionDAO = PrescriptionExecutionDAO;
    }

    public void setPrescriptionDAO(PrescriptionDAO prescriptionDAO) {
        this.prescriptionDAO = prescriptionDAO;
    }

    public void setActiveSubstanceDAO(ActiveSubstanceDAO actSubsDAO) {
        this.activeSubstanceDAO = actSubsDAO;
    }

    public void createActiveSubstanceSpinner() {
        view.createActiveSubstanceSpinner(activeSubstanceDAO.findAll());
    }
}
