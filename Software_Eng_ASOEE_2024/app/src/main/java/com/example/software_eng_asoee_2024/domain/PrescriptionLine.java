package com.example.software_eng_asoee_2024.domain;/* DONE  */

public class PrescriptionLine {
    private ActiveSubstance activeSubstance;
    private final Form form;
    private final Concentration concentration;
    private final String instructions;

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
