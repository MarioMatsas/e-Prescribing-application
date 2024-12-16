package com.example.software_eng_asoee_2024;/* DONE  */

import java.util.Objects;

public class ActiveSubstance {
    private String name;
    private Double expectedQuantityPerMonth;

    public ActiveSubstance() {
    }
    
    public ActiveSubstance(String name, Double expectedQuantityPerMonth) {
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

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ActiveSubstance)) return false;
        return Objects.equals(((ActiveSubstance) obj).getName(), this.getName());
    }
}