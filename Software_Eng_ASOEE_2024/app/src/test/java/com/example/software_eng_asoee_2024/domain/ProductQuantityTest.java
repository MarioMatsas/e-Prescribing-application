package com.example.software_eng_asoee_2024.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;

public class ProductQuantityTest {
    private static ProductQuantity qnt;

    @Before
    public void init() {
        ArrayList<ActiveSubstance> as = new ArrayList<ActiveSubstance>();
        as.add(new ActiveSubstance("Paracetamol", 20d));
        qnt = new ProductQuantity(new PharmacudicalProduct("name", 900, Form.PILL, MedicineType.GENERIC, as,"8 pills per pack"), 10);
    }

    @Test
    public void testInit() {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            this.qnt = new ProductQuantity(null, 10);
        });
        Assert.assertEquals("name", qnt.getProduct().getName());
        Assert.assertEquals((Integer)10, qnt.getProductQuantity());
    }

    @Test
    public void testPrice() {
        Assert.assertEquals((int) 180, qnt.getProductQuantity() * qnt.getProduct().getFinalPrice());
    }

}