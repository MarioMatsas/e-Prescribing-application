package com.example.software_eng_asoee_2024.domain;

import java.io.Serializable;

public class Doctor extends Person implements Serializable {

    private String specialty;
    public Doctor(){}

    public Doctor(String fn, String ln, String pr) {
        super(fn, ln);
        this.specialty = pr;
    }

    public String getSpecialty() {
        return this.specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}