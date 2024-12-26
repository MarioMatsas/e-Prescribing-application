package com.example.software_eng_asoee_2024.domain;/* DONE  */

import java.io.Serializable;

public class Pharmacist extends Person implements Serializable {

    public Pharmacist(){}
    public Pharmacist(String fn, String ln){
        super(fn, ln);
    }
    
    //public void executePrescription(){}
}
