package com.example.software_eng_asoee_2024.views.PrescreptionExecution.Execution;

import com.example.software_eng_asoee_2024.dao.PharmacistDAO;
import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.PrescriptionExecution;
import com.example.software_eng_asoee_2024.domain.PrescriptionLine;
import com.example.software_eng_asoee_2024.domain.ProductQuantity;
import com.example.software_eng_asoee_2024.domain.Unit;
import com.example.software_eng_asoee_2024.memorydao.PatientDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmacistDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionExecutionDAOMemory;
import com.example.software_eng_asoee_2024.views.PrescreptionExecution.Selection.PrescriptionSelectionView;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionExecutionPresenter {
    private PrescriptionExecutionView view;
    private PrescriptionDAOMemory prescriptionDAO;
    private PharmaceuticalProductDAOMemory productDAO;
    private PrescriptionExecutionDAOMemory prescriptionExecutionDAO;
    private PrescriptionExecution prescriptionExecution;
    private PharmacistDAOMemory pharmacistDAO;

    public PrescriptionExecutionView getView() {
        return view;
    }

    public void init(Prescription prescription, String fn, String ln){
        prescriptionExecution = new PrescriptionExecution(pharmacistDAO.find(fn, ln), prescription);
    }
    public void setView(PrescriptionExecutionView view) {
        this.view = view;
    }
    public void setPrescriptionDAO(PrescriptionDAOMemory prescriptionDAO) {
        this.prescriptionDAO = prescriptionDAO;
    }
    public void setPharmaceuticalProdcutDAO(PharmaceuticalProductDAOMemory productDAO){
        this.productDAO = productDAO;
    }
    public void setPharmacistDAO(PharmacistDAOMemory phDAO){
        this.pharmacistDAO = phDAO;
    }
    public void setPrescriptionExecutionDAO(PrescriptionExecutionDAOMemory prescriptionExDAO){
        this.prescriptionExecutionDAO = prescriptionExDAO;
    }

    public Prescription getPrescription(int Id){
        return prescriptionDAO.findPrescriptionById(Id);
    }

    /**
     * Shows all products that have the active substance
     * present in the line
     *
     * @param line
     */
    public void showPrescriptionLineProducts(PrescriptionLine line){
        // Find all the products that have the same active substance as the one in the line
        List<PharmaceuticalProduct> productsToShow = new ArrayList<PharmaceuticalProduct>();
        for (PharmaceuticalProduct product: productDAO.findAll()){
            //product.getActiveSubstances().contains(line.getActiveSubstance())
            if (product.getForm().equals(line.getForm())) {
                for (int i=0; i<product.getActiveSubstances().size(); i++){
                    if (product.getActiveSubstances().get(i).equals(line.getActiveSubstance())
                        && product.getActiveSubstanceConcentrations().get(i).equals(line.getConcentration())){
                        productsToShow.add(product);
                        break;
                    }
                }

            }
        }
        if (productsToShow.isEmpty()) {
            view.showError("No products found.");
            view.clearProductSpinner();
            return;
        }
        view.updateProductSpinner(productsToShow);

    }

    /**
     * Checks if all fields are correct and adds the product to the buy list
     *
     * @param productFromSpinner
     * @param valueFromTextView
     * @return
     */
    public boolean addProductToBuy(PharmaceuticalProduct productFromSpinner, String valueFromTextView){
        if (productFromSpinner != null){
            if (valueFromTextView.isEmpty()) {  // Check if the quantity field is empty
                view.showError("Quantity cannot be empty.");
                //clearProductSpinner();
                return false;
            }
            try {
                int quantity = Integer.parseInt(valueFromTextView);
                if (quantity <= 0){
                    view.showError("Quantity must be positive.");
                    return false;
                }// Try to parse the quantity
                prescriptionExecution.addProductQuantity(new ProductQuantity(productFromSpinner, quantity));
                return true;

            }
            catch (NumberFormatException e) {
                view.showError("Invalid quantity format.");
                //clearProductSpinner();
                return false;
            }

        }
        else {
            view.showError("No products.");
            return false;
        }


    }

    /**
     * After the process is over the prescription is deleted
     * and the prescriptionExecution is saved to the DAO
     *
     * @param prescription
     * @return
     */
    public String finishExecution(Prescription prescription){
        // Remove the prescription from the DAO
        prescriptionDAO.delete(prescription);
        // Add the prescription execution to the DAO
        prescriptionExecutionDAO.save(prescriptionExecution);
        // Return the final price
        return String.valueOf(getTotalCost());
    }

    /**
     * Calculates cost of all the products
     *
     * @return
     */
    public double getTotalCost(){
        return prescriptionExecution.calculateTotalCost()/100.0;
    }
}