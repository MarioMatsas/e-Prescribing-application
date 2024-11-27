/* DONE */

public class PrescriptionLine {
    private ActiveSubsance activeSubstance; 
    public final Form form;
    public final Concentration concentration;
    public final String instructions;


    public PrescriptionLine(Form form, Concentration concentration, String instructions) {
        this.form = form;
        this.concentration = concentration;
        this.instructions = instructions;
    }

    // TODO change the diagram to reflect these
    public ActiveSubsance getActiveSubstance() {
        return this.activeSubstance;
    }

    public void setActiveSubstance(ActiveSubsance activeSubstance) {
        this.activeSubstance = activeSubstance;
    }

}
