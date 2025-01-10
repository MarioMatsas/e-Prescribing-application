package com.example.software_eng_asoee_2024.domain;/* DONE  */

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Concentration implements Serializable {
    private Double quantity;
    private Unit unit;


    public Concentration() {
    }

    public Concentration(Double quantity, Unit unit) {
        this.quantity = quantity;
        this.unit = unit;
    }

    public Double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Unit getUnit() {
        return this.unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @NonNull
    @Override
    public String toString() {
        return quantity + " | " + unit.name();
    }

}

