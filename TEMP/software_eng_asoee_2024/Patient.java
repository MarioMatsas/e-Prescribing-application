public class Patient extends Person {

    private final int SSN;

    public Patient(String fn, String ln, int SSN) {
        super(fn, ln);
        this.SSN = SSN;
    }

    public int getSSN() {
        return this.SSN;
    }

}
