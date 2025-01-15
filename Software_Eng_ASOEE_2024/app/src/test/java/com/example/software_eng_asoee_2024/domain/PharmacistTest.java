package com.example.software_eng_asoee_2024.domain;

import org.junit.Assert;
import org.junit.Test;

public class PharmacistTest {
    /**
     * tests the constructor when there is no field filled
     */
    @Test
    public void EmptyConstructorTest(){
        Assert.assertNotNull(new Pharmacist());
    }
}
