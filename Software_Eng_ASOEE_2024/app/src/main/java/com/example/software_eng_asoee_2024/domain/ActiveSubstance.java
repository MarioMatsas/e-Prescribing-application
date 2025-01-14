package com.example.software_eng_asoee_2024.domain;/* DONE  */

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;

public class ActiveSubstance implements Serializable {
    private String name;
    private Double expectedQuantityPerMonth;

    public ActiveSubstance() {
    }
    
    public ActiveSubstance(String name, Double expectedQuantityPerMonth) {
        if(expectedQuantityPerMonth <= 0) throw new IllegalArgumentException("Quantity must be non zero positive number");
        this.name = name;
        this.expectedQuantityPerMonth = expectedQuantityPerMonth;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getExpectedQuantityPerMonth() {
        return this.expectedQuantityPerMonth;
    }

    public void setExpectedQuantityPerMonth(Double expectedQuantityPerMonth) {
        this.expectedQuantityPerMonth = expectedQuantityPerMonth;
    }

    @NonNull
    @Override
    public String toString() {
        return getName() + " | " + getExpectedQuantityPerMonth();
    }

    /**
     * Override του equals για να καθορίσουμε εμείς πως θα συγκριθούν οι δύο ουσίες.
     * Αρκεί να έχουν ίδιο όνομα και ίδια εκτιμώμενη ποσότητα ανά μήνα, για να είναι ίδιες.
     * @param obj η ουσία με την οποία συγκρίνουμε την συγκεκριμένη
     * @return επιστρέφει true αν είναι ίδιες οι δυο ουσίες, αλλιώς false
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj == null) return false;
        if(obj.getClass() != this.getClass()) return false;
        ActiveSubstance ac = (ActiveSubstance) obj;
        return ac.getName().equals(this.getName()) && ac.getExpectedQuantityPerMonth().equals(this.getExpectedQuantityPerMonth());
    }
}