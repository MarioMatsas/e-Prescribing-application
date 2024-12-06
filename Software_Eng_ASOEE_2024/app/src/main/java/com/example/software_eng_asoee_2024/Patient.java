package com.example.software_eng_asoee_2024;/* DONE  *//* DONE */
public class Patient extends Person{
    
    private final Integer SSN;

    public Patient(String fn, String ln, Integer SSN) {
        super(fn, ln);
        this.SSN = SSN;
    }

    public Integer getSSN() {
        return this.SSN;
    }

}
