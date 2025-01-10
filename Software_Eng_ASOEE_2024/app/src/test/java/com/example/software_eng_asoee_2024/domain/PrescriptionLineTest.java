package com.example.software_eng_asoee_2024.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

public class PrescriptionLineTest {
    static PrescriptionLine line;

    @Before
    public void init() {
        line = new PrescriptionLine(Form.CREAM, new Concentration(10.0, Unit.mg_per_g), "For 10 days", new ActiveSubstance("Paracetamol", 10d));
    }

    @Test
    public void testInit() {
        Assert.assertEquals(line.getForm(), Form.CREAM);;
        Assert.assertEquals(line.getConcentration().getQuantity(), (Double)10.0);
        Assert.assertEquals(line.getInstructions(), "For 10 days");
        Assert.assertEquals(line.getActiveSubstance().getName(), "Paracetamol");
    }

    @Test
    public void testConstruct() {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            line = new PrescriptionLine(Form.CREAM, new Concentration(10.0, Unit.mg_per_g), "For 10 days", null);
        });
    }

    @Test
    public void testSet() {
        line.setActiveSubstance(new ActiveSubstance("Ibuprofen", 5d));
        Assert.assertEquals(line.getActiveSubstance().getName(), "Ibuprofen");
    }
}