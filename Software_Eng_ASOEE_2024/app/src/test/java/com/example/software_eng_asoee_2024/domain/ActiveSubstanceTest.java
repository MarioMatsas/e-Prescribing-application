package com.example.software_eng_asoee_2024.domain;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class ActiveSubstanceTest {
    /**
     * Για επιβεβαίωση οτι θα πετάξει exception για μη-θετικό quantity.
     */
    @Test
    public void testConstructorWrongQty(){
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            ActiveSubstance act_sub1 = new ActiveSubstance("Paracetamol", -2.0);
        });

        Assert.assertThrows(IllegalArgumentException.class, () -> {
            ActiveSubstance act_sub1 = new ActiveSubstance("Paracetamol2", 0.0);
        });
    }

    /**
     * Για επιβεβαίωση οτι θα αλλάξει σωστά το όνομα.
     */
    @Test
    public void testName(){
        ActiveSubstance act_sub1 = new ActiveSubstance();
        act_sub1.setName("testName1");
        Assert.assertEquals("testName1", act_sub1.getName());

        ActiveSubstance act_sub2 = new ActiveSubstance("Jason", 25.2);
        act_sub2.setName("testName2");
        Assert.assertEquals("testName2", act_sub2.getName());
    }

    /**
     * Για επιβεβαίωση οτι θα αλλάξει σωστά την αναμενόμενη ποσότητα ανά μήνα.
     */
    @Test
    public void testExpectedQuantPerMonth(){
        ActiveSubstance act_sub1 = new ActiveSubstance();
        act_sub1.setExpectedQuantityPerMonth(25.5);
        Assert.assertEquals((Double)25.5, act_sub1.getExpectedQuantityPerMonth());

        ActiveSubstance act_sub2 = new ActiveSubstance("Jesse", 28.6);
        act_sub2.setExpectedQuantityPerMonth(25.5);
        Assert.assertEquals((Double)25.5, act_sub2.getExpectedQuantityPerMonth());
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει false, για σύγκριση διαφορετικών αντικειμένων ή null.
     */
    @Test
    public void TestEqualsWrong(){
        ActiveSubstance act_sub1 = new ActiveSubstance("hhhh", 2025.0);
        ActiveSubstance act_sub2 = null;
        Assert.assertFalse(act_sub1.equals(act_sub2));
        Assert.assertFalse(act_sub1.equals("null"));
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει το σωστό string.
     */
    @Test
    public void TestToString(){
        ActiveSubstance act_sub1 = new ActiveSubstance("hhhh", 2025.0);
        Assert.assertEquals(act_sub1.toString(), "hhhh: 2025.0 mg per month");
    }
}
