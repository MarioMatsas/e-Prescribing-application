package com.example.software_eng_asoee_2024;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class ConcentrationTest {
    @Test
    public void testQuantity(){
        Concentration con1 = new Concentration();
        con1.setQuantity(10);
        Assert.assertEquals((Integer)10, con1.getQuantity());

        Concentration con2 = new Concentration(5, Unit.mg_per_disk);
        con2.setQuantity(20);
        Assert.assertEquals((Integer)20, con2.getQuantity());
    }

    @Test
    public void testUnit(){
        Concentration con = new Concentration();
        con.setUnit(Unit.mg_per_ml);
        Assert.assertEquals(Unit.mg_per_ml, con.getUnit());
    }
}
