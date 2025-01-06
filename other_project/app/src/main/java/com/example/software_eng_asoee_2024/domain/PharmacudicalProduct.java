package com.example.software_eng_asoee_2024.domain;/* DONE  */

import java.util.ArrayList;
import java.util.List;

public class PharmacudicalProduct {
    private String name;
    private Integer retailPrice; // in cents
    private Form form;
    private MedicineType type;
    private List<ActiveSubstance> activeSubstances;
    private Concentration concentration;
    private String infortmation;

    public PharmacudicalProduct() {
    }

    public PharmacudicalProduct(String name, Integer retailPrice, Form form, MedicineType type, ArrayList<ActiveSubstance> activeSubs, String info) {
        this.name = name;
        this.retailPrice = retailPrice;
        this.form = form;
        this.type = type;
        this.activeSubstances = activeSubs;
        this.concentration = concentration;
        this.infortmation = info;
        //, Concentration concentration
    }
    public String getInformation() {
        return this.infortmation;
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

    public Integer getFinalPrice() {
        return (int) Math.round((getRetailPrice() * getCustomerParticipation()));
    }

    public List<ActiveSubstance> getActiveSubstances(){
        return activeSubstances;
    }

    //public Concentration getConcentration() { return concentration; }

    public String toString(){
        return name + " " + getFinalPrice()/100.0 + " " + infortmation;
    }
}
