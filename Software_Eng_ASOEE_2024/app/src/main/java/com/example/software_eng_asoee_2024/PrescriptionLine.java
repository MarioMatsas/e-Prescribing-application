package com.example.software_eng_asoee_2024;/* DONE  *//* DONE */

public class PrescriptionLine {
    private ActiveSubstance activeSubstance; 
    public final Form form;
    public final Concentration concentration;
    public final String instructions;


    public PrescriptionLine(Form form, Concentration concentration, String instructions, ActiveSubstance activeSubstance) {
        this.form = form;
        this.concentration = concentration;
        this.instructions = instructions;
        this.activeSubstance = activeSubstance;
    }

    // TODO change the diagram to reflect these
    public ActiveSubstance getActiveSubstance() {
        return this.activeSubstance;
    }

    public void setActiveSubstance(ActiveSubstance activeSubstance) {
        this.activeSubstance = activeSubstance;
    }

}
