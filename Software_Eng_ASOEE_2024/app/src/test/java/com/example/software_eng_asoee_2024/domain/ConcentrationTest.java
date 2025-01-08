package com.example.software_eng_asoee_2024.domain;

import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Unit;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class ConcentrationTest {
    @Test
    public void testQuantity(){
        Concentration con1 = new Concentration();
        con1.setQuantity(10.0);
        Assert.assertEquals((Double)10.0, con1.getQuantity());

        Concentration con2 = new Concentration(5.0, Unit.mg_per_disk);
        con2.setQuantity(20.0);
        Assert.assertEquals((Double)20.0, con2.getQuantity());
    }

    @Test
    public void testUnit(){
        Concentration con = new Concentration();
        con.setUnit(Unit.mg_per_ml);
        Assert.assertEquals(Unit.mg_per_ml, con.getUnit());
    }
}
