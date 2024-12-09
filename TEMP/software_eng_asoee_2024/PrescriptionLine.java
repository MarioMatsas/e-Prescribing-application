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

    public ActiveSubstance getActiveSubstance() {
        return this.activeSubstance;
    }

    public void setActiveSubstance(ActiveSubstance activeSubstance) {
        this.activeSubstance = activeSubstance;
    }

}
