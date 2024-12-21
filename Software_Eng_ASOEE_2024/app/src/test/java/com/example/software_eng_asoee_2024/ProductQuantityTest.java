package com.example.software_eng_asoee_2024;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

public class ProductQuantityTest {
    private static ProductQuantity qnt;

    @Before
    public void init() {
        qnt = new ProductQuantity(new PharmacudicalProduct("name", 900, Form.PILL, MedicineType.GENERIC), 10);
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