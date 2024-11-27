public class Doctor extends Person{
    
    private String profession;

    public Doctor(){}

    public Doctor(String pr){
        profession = pr;
    }

    public String getProfession(){
        return profession;
    }

    public String setProfession(String str){
        profession= str;
    }

    public void createPrescription(int SSN){}

    public Patient findPatient(int SSN){}
}
