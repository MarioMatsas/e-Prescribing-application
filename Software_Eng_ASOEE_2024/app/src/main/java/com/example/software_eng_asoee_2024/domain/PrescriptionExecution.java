package com.example.software_eng_asoee_2024.domain;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionExecution {
    private final Pharmacist pharmacist;
    private final Prescription prescription;
    private List<ProductQuantity> productQuantities;
    public final Date completionDate;

    public PrescriptionExecution(Pharmacist pharmacist, Prescription prescription) {
        if (pharmacist == null) throw new IllegalArgumentException("Pharmacist null error");
        if (prescription == null) throw new IllegalArgumentException("Prescription null error");
        this.completionDate = new Date();
        this.pharmacist = pharmacist;
        this.prescription = prescription;
        productQuantities = new ArrayList<ProductQuantity>();
    }

    public PrescriptionExecution(Pharmacist pharmacist, Prescription prescription, ArrayList<ProductQuantity> list) {
        this(pharmacist, prescription);
        productQuantities = list;
    }

    public void addProductQuantity(ProductQuantity productQuantity) {
        boolean found = false;
        for(ProductQuantity prQ : productQuantities){//checking if the product of productquantity is already registered
            if(prQ.getProduct().equals(productQuantity.getProduct())){
                found = true;
                break;
            }
        }
        if (found)
            return;

        List<PrescriptionLine> prescrLines = prescription.getPrescriptionLines();
        found = false;
        for (PrescriptionLine prline : prescrLines){
            if(productQuantity.getProduct().getActiveSubstances().contains(prline.getActiveSubstance())){
                found = true; // checking if there is at least one substance in prescrLines that is contained in productQuantity.product
                break;
            }
        }
        if(found)
            this.productQuantities.add(productQuantity);
    }

    public Integer calculateTotalCost() {
        int totalCost = 0;
        for (ProductQuantity productQuantity : productQuantities) {
            totalCost += productQuantity.getProductQuantity() * productQuantity.getProduct().getFinalPrice();
        }
        // TODO MAYBE DON'T ADD THE X100, DID IT SO THAT THE PRICES MAKE SENSE
        return totalCost * 100;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public List<ProductQuantity> getProductQuantities() {
        return productQuantities;
    }

}

