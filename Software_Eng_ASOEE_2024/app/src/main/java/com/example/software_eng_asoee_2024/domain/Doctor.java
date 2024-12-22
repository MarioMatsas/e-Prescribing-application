package com.example.software_eng_asoee_2024.domain;

public class Doctor extends Person {

    private String specialty;

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