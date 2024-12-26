package com.example.software_eng_asoee_2024.domain;/* DONE  *//* DONE */
public class Person {
    private String firstName;
    private String lastname;

    public  Person(){}
    public Person(String firstName, String lastname) {
        this.firstName = firstName;
        this.lastname = lastname;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

}