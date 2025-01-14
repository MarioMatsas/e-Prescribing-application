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

    public boolean deletePharmaceuticalProduct(PharmaceuticalProduct pp) {
        if(pp == null) {
            view.showMessage("None selected to be deleted");
            return false;
        }

        for (PrescriptionExecution px : prescriptionExecutionDAO.findAll()) {
            px.getProductQuantities().remove(pp);

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

    public void createPharmaceuticalProductSpinner() {
        view.createPharmaceuticalProductSpinner(pharmaceuticalProductDAO.findAll());
    }
    public void setPrescriptionExecutionDAO(PrescriptionExecutionDAOMemory PrescriptionExecutionDAO) {
        this.prescriptionExecutionDAO = PrescriptionExecutionDAO;
    }
}
