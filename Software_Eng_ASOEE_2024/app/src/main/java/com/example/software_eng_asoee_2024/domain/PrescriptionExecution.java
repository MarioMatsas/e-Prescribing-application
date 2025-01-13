package com.example.software_eng_asoee_2024.domain;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionExecution {
    private final Pharmacist pharmacist;
    private final Prescription prescription;
    private List<ProductQuantity> productQuantities;
    public final Date completionDate;

    /**
     * Ο κατασκευαστής (ένας από τους κατασκευαστές).
     * Αν δέν δοθεί συνταγή ή φαρμακοποιός, δεν έχουμε συνταγή (πετάγεται exception)
     * @param pharmacist ο φαρμακοποιός που εκτελεί την συνταγή
     * @param prescription η συνταγή
     */
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

    /**
     * Προσθέτει το αντικείμενο productQuantity στην λίστα των productQuantities,
     * εφόσον υπάρχει η ουσία του φαρμάκου σε κάποια γραμμή της συνταγής.
     * @param productQuantity το πλήθος των φαρμάκων που θα αγοραστούν
     */
    public void addProductQuantity(ProductQuantity productQuantity) {
        System.out.println("\n\nSSSSSSSSSSS\n\n");
        System.out.println(productQuantity.getProduct());
        boolean found = false;
        List<PrescriptionLine> prescrLines = prescription.getPrescriptionLines();
        for (PrescriptionLine prline : prescrLines){
            if(productQuantity.getProduct().getActiveSubstances().contains(prline.getActiveSubstance())){
                System.out.println("FOUND");
                found = true; // checking if there is at least one substance in prescrLines that is contained in productQuantity.product
                break;
            }
        }
        if(found)
            this.productQuantities.add(productQuantity);
    }

    /**
     * Επιστρέφει το συνολικό κόστος από την εκτέλεση της συνταγής.
     * Πολλάπλασιάζεται επί 100, γιατί ο αριθμητής του γινομένου διαιρείται δια 10000,
     * οπότε με επι 100 έρχεται στην μορφή ευρώ.
     * @return Επιστρέφει το συνολικό κόστος από την εκτέλεση της συνταγής
     */
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

