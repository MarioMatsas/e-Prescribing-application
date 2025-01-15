package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Deletion;

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
    private ActiveSubstanceDAOMemory activeSubstanceDAO;
    private PharmaceuticalProductDAOMemory pharmaceuticalProductDAO;
    private PrescriptionDAOMemory prescriptionDAO;
    private PrescriptionExecutionDAOMemory prescriptionExecutionDAO;

    public ActiveSubstanceDeleteView getView() {
        return view;
    }

    public void setView(ActiveSubstanceDeleteView view) {
        this.view = view;
    }

    /**
     * Deletes the given active substance and updates related entities.
     *
     * This method removes the given active substance from all associated
     * PharmaceuticalProducts and Prescriptions.
     * If a PharmaceuticalProduct or Prescription becomes empty after the deletion,
     * it is also deleted from the respective DAOs and
     * the associated PrescriptionExecutions are updated and deleted as needed.
     *
     * @param ac the active substance to be deleted
     * @return true if the deleted active substance was the last one in the DAO, false otherwise
     */
    public boolean deleteActiveSubstance(ActiveSubstance ac) {
        if(ac == null) {
            view.showMessage("None selected to be edited");
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

    public void setPharmaceuticalProductDAO(PharmaceuticalProductDAOMemory pharmaceuticalProductDAO) {
        this.pharmaceuticalProductDAO = pharmaceuticalProductDAO;
    }

    public void setPrescriptionExecutionDAO(PrescriptionExecutionDAOMemory PrescriptionExecutionDAO) {
        this.prescriptionExecutionDAO = PrescriptionExecutionDAO;
    }

    public void setPrescriptionDAO(PrescriptionDAOMemory prescriptionDAO) {
        this.prescriptionDAO = prescriptionDAO;
    }

    public void setActiveSubstanceDAO(ActiveSubstanceDAOMemory actSubsDAO) {
        this.activeSubstanceDAO = actSubsDAO;
    }

    /**
     * Creates the active substance spinner in the view.
     */
    public void createActiveSubstanceSpinner() {
        view.createActiveSubstanceSpinner(activeSubstanceDAO.findAll());
    }
}
