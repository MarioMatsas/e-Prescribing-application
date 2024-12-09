package com.example.software_eng_asoee_2024;/* DONE  */
import java.util.List;

public class Doctor extends Person{
    
    private String specialty;

    public Doctor(String fn, String ln, String pr){
        super(fn, ln);
        this.specialty = pr;
    }

    public String getSpecialty(){
        return this.specialty;
    }

    public void setSpecialty(String specialty){
        this.specialty = specialty;
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