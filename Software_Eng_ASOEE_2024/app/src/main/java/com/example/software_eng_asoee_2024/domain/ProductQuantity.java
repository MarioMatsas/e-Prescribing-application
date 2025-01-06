package com.example.software_eng_asoee_2024.domain;/* DONE  *//* DONE */

public class ProductQuantity {
    private final PharmaceuticalProduct product;
    private final Integer quantity;

    public ProductQuantity(PharmaceuticalProduct product, Integer quantity) {
        if (product == null) {
            throw new IllegalArgumentException("PharmaceuticalProduct null error");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public Integer getProductQuantity() {
        return this.quantity;
    }

    public PharmaceuticalProduct getProduct() {
        return this.product;
    }

    public String toString(){
        return product + " " + quantity;
    }
}
