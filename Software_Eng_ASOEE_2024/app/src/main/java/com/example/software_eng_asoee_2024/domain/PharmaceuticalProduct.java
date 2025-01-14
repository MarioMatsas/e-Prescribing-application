package com.example.software_eng_asoee_2024.domain;/* DONE  */

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PharmaceuticalProduct {
    private String name;
    private Integer retailPrice; // in cents
    private Form form;
    private MedicineType type;
    private List<ActiveSubstance> activeSubstances;
    private List<Concentration> activeSubstanceConcentrations;

    private String information;

    public PharmaceuticalProduct() {
    }

    /**
     * Κατασκευαστής.
     * Αν δεν αντιστοιχούν  οι μονάδες μέτρησης για κάθε μορφή σκευάσματος του φαρμάκου,
     * το πλήθος των ουσιών δεν αντιστοιχεί με το πλήθος των περιεκτικοτήτων
     * και δεν υπάρχουν ουσίες για αυτό το φάρμακο, ο κωδικας πετά σφάλμα.
     * @param name όνομα φαρμάκου
     * @param retailPrice λιανική τιμή φαρμάκου
     * @param form μορφή σκευάσματος φαρμάκου
     * @param type τύπος φαρμάκου (γενόσημο ή αυθεντικό)
     * @param activeSubs δραστικές ουσίες που περιέχει το φάρμακο
     * @param activeSubstanceConcentrations περιεκτικότητες για τις ουσίες
     * @param info πληροφορίες για το φάρμακο
     */
    public PharmaceuticalProduct(String name, Integer retailPrice, Form form, MedicineType type, ArrayList<ActiveSubstance> activeSubs, List<Concentration> activeSubstanceConcentrations, String info) {
        if(activeSubs.size() != activeSubstanceConcentrations.size()) throw new IllegalArgumentException("Active Substances and Concentrations must correspond");
        if(activeSubs.isEmpty()) throw new IllegalArgumentException("Must have at least one Active Substance");
        if(retailPrice <= 0) throw new IllegalArgumentException("Retail Price must be non zero positive integer");
        Unit unit = activeSubstanceConcentrations.get(0).getUnit();
        for(int i = 1; i < activeSubstanceConcentrations.size(); i++) {
            if(!activeSubstanceConcentrations.get(i).getUnit().equals(unit))
                throw new IllegalArgumentException("All active substances must be of the same unit");
        }
        this.name = name;
        this.retailPrice = retailPrice;
        this.form = form;
        this.type = type;
        this.activeSubstances = activeSubs;
        this.activeSubstanceConcentrations = activeSubstanceConcentrations;
        this.information = info;
    }
    public String getInformation() {
        return this.information;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRetailPrice() {
        return this.retailPrice;
    }

    public void setRetailPrice(Integer retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Form getForm() {
        return this.form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public MedicineType getMedicineType() {
        return this.type;
    }

    public void setMedicineType(MedicineType type) {
        this.type = type;
    }

    /**
     * Υπολογίζει την συμμετοχή του ασθενή.
     * Ανάλογα αν είναι γενόσημο ή αυθεντικό,
     * επιστρέφει 0.02 ή 0.1 αντίστοιχα.
     * @return επιστρέφει την συμμετοχή του ασθενή
     */
    public Double getCustomerParticipation() {
        Double participation;
        if (getMedicineType() == MedicineType.ORIGINAL) {
            participation = 0.1;
        } else {
            // GENERIC
            participation = 0.02;
        }
        return participation;
    }

    /**
     * Επιστρέφει την τελική τιμή του φαρμάκου.
     * Πολλαπλασιάζει την συμμετοχή επί την λιανική τιμή του.
     * (Στρογγυλοποιεί προς τα κάτω, με τιμή σε cents)
     * @return eπιστρέφει την τελική τιμή του φαρμάκου
     */
    public Integer getFinalPrice() {
        return (int) Math.round((getRetailPrice() * getCustomerParticipation()));
    }


    public List<ActiveSubstance> getActiveSubstances(){
        return activeSubstances;
    }

    public void setActiveSubstances(List<ActiveSubstance> activeSubstances) {
        this.activeSubstances = activeSubstances;
    }

    public void setActiveSubstanceConcentrations(List<Concentration> activeSubstanceConcentrations) {
        this.activeSubstanceConcentrations = activeSubstanceConcentrations;
    }

    public List<Concentration> getActiveSubstanceConcentrations(){
        return activeSubstanceConcentrations;
    }

    /**
     * Επιστρέφει το string, απλα /100 την τελική τιμή για να την κάνουμε ευρώ
     * @return Επιστρέφει το string, ως ευρώ
     */
    public String toString(){
        return name + " " + getFinalPrice()/100.0 + " " + information;
    }

    /**
     * Override του equals για να καθορίσουμε εμείς πως θα συγκριθούν τα δύο προϊόντα.
     * Αρκεί να έχουν ίδιες τιμές στα πεδία τους.
     * @param obj το φάρμακο με το οποίο συγκρίνουμε το συγκεκριμένο
     * @return επιστρέφει true αν είναι ίδιες οι δυο ουσίες, αλλιώς false
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj == null) return false;
        if(obj.getClass() != this.getClass()) return false;
        PharmaceuticalProduct temp = (PharmaceuticalProduct) obj;
        return temp.getName().equals(this.getName()) && temp.getInformation().equals(this.getInformation()) && temp.getForm() == this.getForm() && temp.getMedicineType() == this.getMedicineType() && temp.getActiveSubstances() == this.getActiveSubstances() && temp.getActiveSubstanceConcentrations() == this.getActiveSubstanceConcentrations();
    }
}
