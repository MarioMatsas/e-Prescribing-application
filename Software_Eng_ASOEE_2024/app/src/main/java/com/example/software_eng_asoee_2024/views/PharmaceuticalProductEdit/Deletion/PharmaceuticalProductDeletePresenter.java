package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Deletion;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.domain.PrescriptionExecution;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionExecutionDAOMemory;


public class PharmaceuticalProductDeletePresenter {
    private PharmaceuticalProductDeleteView view;
    private PharmaceuticalProductDAOMemory pharmaceuticalProductDAO;
    private PrescriptionExecutionDAOMemory prescriptionExecutionDAO;

    public PharmaceuticalProductDeleteView getView() {
        return view;
    }

    public void setView(PharmaceuticalProductDeleteView view) {
        this.view = view;
    }

    /**
     * Deletes the specified pharmaceutical product and updates related entities.
     * Removes any associated product quantities from
     * PrescriptionExecutions. If a PrescriptionExecution has no remaining
     * product quantities after the deletion it is also deleted.
     *
     * @param pp the pharmaceutical product to be deleted
     * @return true if the deleted pharmaceutical product the last one in the DAO, false otherwise
     */
    public boolean deletePharmaceuticalProduct(PharmaceuticalProduct pp) {
        if(pp == null) {
            view.showMessage("None selected to be deleted");
            return false;
        }

        for (PrescriptionExecution px : prescriptionExecutionDAO.findAll()) {
            px.getProductQuantities().removeIf(v -> v.getProduct().equals(pp));

            if(px.getProductQuantities().isEmpty())
                this.prescriptionExecutionDAO.delete(px);
        }

        this.pharmaceuticalProductDAO.delete(pp);
        createPharmaceuticalProductSpinner();
        view.showMessage("Done!");
        return(this.pharmaceuticalProductDAO.findAll().isEmpty());
    }

    public void setPharmaceuticalProductDAO(PharmaceuticalProductDAOMemory actSubsDAO) {
        this.pharmaceuticalProductDAO = actSubsDAO;
    }

    /**
     * Creates the pharmaceutical product spinner in the view.
     */
    public void createPharmaceuticalProductSpinner() {
        view.createPharmaceuticalProductSpinner(pharmaceuticalProductDAO.findAll());
    }
    public void setPrescriptionExecutionDAO(PrescriptionExecutionDAOMemory PrescriptionExecutionDAO) {
        this.prescriptionExecutionDAO = PrescriptionExecutionDAO;
    }
}
