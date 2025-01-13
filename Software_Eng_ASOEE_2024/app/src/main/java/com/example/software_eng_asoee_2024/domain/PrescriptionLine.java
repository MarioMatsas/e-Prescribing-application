package com.example.software_eng_asoee_2024.domain;/* DONE  */

import java.io.Serializable;

public class PrescriptionLine implements Serializable {
    private ActiveSubstance activeSubstance;
    private final Form form;
    private final Concentration concentration;
    private final String instructions;

    /**
     * Ο κατασκευαστής.
     * Αν η ουσία δεν δοθεί πετάγεται σφάλμα.
     * @param form η μορφή του φαρμάκου
     * @param concentration η περιεκτικότητα
     * @param instructions οδηγίες χρήσης, από γιατρό
     * @param activeSubstance η φαρμακευτική ουσία
     */
    public PrescriptionLine(Form form, Concentration concentration, String instructions, ActiveSubstance activeSubstance) {
        if (activeSubstance == null)
            throw new IllegalArgumentException("ActiveSubstance null error");
        this.form = form;
        this.concentration = concentration;
        this.instructions = instructions;
        this.activeSubstance = activeSubstance;
    }

    public ActiveSubstance getActiveSubstance() {
        return this.activeSubstance;
    }

    public void setActiveSubstance(ActiveSubstance substance) {
        this.activeSubstance = substance;
    }

    public Form getForm() {
        return form;
    }

    public Concentration getConcentration() {
        return concentration;
    }

    public String getInstructions() {
        return instructions;
    }

}
