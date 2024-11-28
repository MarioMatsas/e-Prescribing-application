import java.util.List;

public class Doctor extends Person{
    
    private String profession;

    public Doctor(String fn, String ln, String pr){
        super(ln, pr);
        this.profession = pr;
    }

    public String getProfession(){
        return this.profession;
    }

    public void setProfession(String profession){
        this.profession = profession;
    }

    // public void createPrescription(int SSN){}
    /* 
    public Patient findPatient(int SSN, List<Patient> patients){
        for (Patient p:patients){
            if (p.getSSN() == SSN) return p;
        }
        return null; // Patient not found
    }
    */
}