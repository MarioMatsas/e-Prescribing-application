package com.example.software_eng_asoee_2024.views.PrescreptionExecution.Execution;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.PharmacudicalProduct;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.PrescriptionExecution;
import com.example.software_eng_asoee_2024.domain.PrescriptionLine;
import com.example.software_eng_asoee_2024.domain.ProductQuantity;
import com.example.software_eng_asoee_2024.domain.Unit;
import com.example.software_eng_asoee_2024.memorydao.PatientDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmacudicalProductDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;
import com.example.software_eng_asoee_2024.views.PrescreptionExecution.Selection.PrescriptionSelectionView;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionExecutionPresenter {
    private PrescriptionExecutionView view;
    private PrescriptionDAOMemory prescriptionDAO;
    private PharmacudicalProductDAOMemory productDAO;
    private PrescriptionExecution prescriptionExecution;

    public PrescriptionExecutionView getView() {
        return view;
    }
    public void setView(PrescriptionExecutionView view) {
        this.view = view;
    }
    public void setPrescriptionDAO(PrescriptionDAOMemory prescriptionDAO) {
        this.prescriptionDAO = prescriptionDAO;
    }
    public void setPharmacudicalProdcutDAO(PharmacudicalProductDAOMemory productDAO){
        this.productDAO = productDAO;
    }

    public void showPrescriptionLineProducts(PrescriptionLine line){
        // Find all the products that have the same active substance as the one in the line
        List<PharmacudicalProduct> productsToShow = new ArrayList<PharmacudicalProduct>();
        for (PharmacudicalProduct product: productDAO.findAll()){
            if (product.getActiveSubstances().contains(line.getActiveSubstance())){
                productsToShow.add(product);
            }
        }
        if (productsToShow.isEmpty()) {
            view.showError("No products found.");
            view.clearProductSpinner();
            return;
        }
        view.updateProductSpinner(productsToShow);

    }

    public void addProductToBuy(PharmacudicalProduct productFromSpinner, int valueFromTextView){
        prescriptionExecution.addProductQuantity(new ProductQuantity(productFromSpinner, valueFromTextView));
    }

    public void finishExecution(Prescription prescription){
        // Remove the prescription from the DAO
        prescriptionDAO.delete(prescription);
        // Print the final price
        System.out.println(getTotalCost());
    }

    public int getTotalCost(){
        return prescriptionExecution.calculateTotalCost();
    }
}
