public class Doctor extends Person {

    private String specialty;

    public Doctor(String fn, String ln, String pr) {
        super(ln, pr);
        this.specialty = pr;
    }

    public String getSpecialty() {
        return this.specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}