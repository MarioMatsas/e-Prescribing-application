package com.example.software_eng_asoee_2024;/* DONE  *//* DONE */

public class PrescriptionLine {
    private ActiveSubstance activeSubstance; 
    private final Form form;
    private final Concentration concentration;
    private final String instructions;


    public PrescriptionLine(Form form, Concentration concentration, String instructions, ActiveSubstance activeSubstance) {
        if (activeSubstance == null) {
            throw new IllegalArgumentException("ActiveSubstance null error");
        }

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
    public Form getForm(){
        return this.form;
    }

    public Concentration getConcentration(){
        return this.concentration;
    }
    public String getInstructions(){
        return this.instructions;
    }

}
