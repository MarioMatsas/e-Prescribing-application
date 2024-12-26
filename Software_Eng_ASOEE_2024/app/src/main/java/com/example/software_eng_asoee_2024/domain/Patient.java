package com.example.software_eng_asoee_2024.domain;/* DONE  *//* DONE */

import java.io.Serializable;

public class Patient extends Person implements Serializable {
    
    private final Integer SSN;

    public Patient(){this.SSN = 0;}

    public Patient(String fn, String ln, Integer SSN) {
        super(fn, ln);
        this.SSN = SSN;
    }

    public Integer getSSN() {
        return this.SSN;
    }

}
