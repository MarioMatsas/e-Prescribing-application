package com.example.software_eng_asoee_2024.domain;

import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Unit;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class ConcentrationTest {
    /**
     * Για επιβεβαίωση οτι θα πετάξει exception για μη-θετικό quantity.
     */
    @Test
    public void testConstructorWrongQty(){
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            Concentration conc1 = new Concentration(0.0, Unit.mg_per_disk);
        });

        Assert.assertThrows(IllegalArgumentException.class, () -> {
            Concentration conc2 = new Concentration(-1.0, Unit.mg_per_disk);
        });
    }

    /**
     * Για επιβεβαίωση οτι θα αλλάξει σωστά η ποσότητα.
     */
    @Test
    public void testQuantity(){
        Concentration con1 = new Concentration();
        con1.setQuantity(10.0);
        Assert.assertEquals((Double)10.0, con1.getQuantity());

        Concentration con2 = new Concentration(5.0, Unit.mg_per_disk);
        con2.setQuantity(20.0);
        Assert.assertEquals((Double)20.0, con2.getQuantity());
    }

    /**
     * Για επιβεβαίωση οτι θα αλλάξει σωστά η μονάδα μέτρησης.
     */
    @Test
    public void testUnit(){
        Concentration con = new Concentration();
        con.setUnit(Unit.mg_per_ml);
        Assert.assertEquals(Unit.mg_per_ml, con.getUnit());
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει το false,
     * για σύγκριση με διαφορετικής κλάσης αντικείμενο ή null.
     */
    @Test
    public void testEqualsWrongInput(){
        Concentration con2 = new Concentration(5.0, Unit.mg_per_disk);
        Concentration con1 = null;
        Assert.assertFalse(con2.equals(con1)); //null
        Assert.assertFalse(con2.equals("null")); // Different class
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει το true αν συγκρίνει τον εαυτό του.
     */
    @Test
    public void testEqualsSame(){
        Concentration con2 = new Concentration(5.0, Unit.mg_per_disk);
        Assert.assertTrue(con2.equals(con2));
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει το true αν συγκριθεί με όμοιο αντικείμενο.
     */
    @Test
    public void testEquals(){
        Concentration con2 = new Concentration(5.0, Unit.mg_per_disk);
        Concentration con1 = new Concentration(5.0, Unit.mg_per_disk);

        Assert.assertTrue(con2.equals(con1));
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει false,
     * αν συγκριθεί με ανόμοια concentration objects.
     */
    @Test
    public void testNotEquals(){//compairing each of them (2-7) with con1, con1_1, conc_2, conc_3
        Concentration con1 = new Concentration(5.0, Unit.mg_per_disk);// both fileds filled
        Concentration con1_1 = new Concentration();// unit field filled
        con1_1.setUnit(Unit.mg_per_disk);
        Concentration con1_2 = new Concentration();// qty filed filled
        con1_2.setQuantity(5.0);
        Concentration con1_3 = new Concentration();// both null

        Concentration con2 = new Concentration(15.0, Unit.mg_per_disk);// diff qty
        Concentration con3 = new Concentration(10.0, Unit.mg_per_ml);// diff both
        Concentration con4 = new Concentration(5.0, Unit.mg_per_dose);// diff unit
        Concentration con5 = new Concentration();// null unit
        con5.setQuantity(5.0);
        Concentration con6 = new Concentration();// diff qty
        con6.setUnit(Unit.mg_per_dose);
        Concentration con7 = new Concentration();// diff both

        Assert.assertFalse(con1.equals(con2));
        Assert.assertFalse(con1.equals(con3));
        Assert.assertFalse(con1.equals(con4));
        Assert.assertFalse(con1.equals(con5));
        Assert.assertFalse(con1.equals(con6));
        Assert.assertFalse(con1.equals(con7));

        Assert.assertFalse(con1_1.equals(con2));
        Assert.assertFalse(con1_1.equals(con3));
        Assert.assertFalse(con1_1.equals(con4));
        Assert.assertFalse(con1_1.equals(con5));
        Assert.assertFalse(con1_1.equals(con6));
        Assert.assertFalse(con1_1.equals(con7));

        Assert.assertFalse(con1_2.equals(con2));
        Assert.assertFalse(con1_2.equals(con3));
        Assert.assertFalse(con1_2.equals(con4));
        Assert.assertFalse(con1_2.equals(con6));
        Assert.assertFalse(con1_2.equals(con7));

    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει το σωστό string.
     */
    @Test
    public void testToString(){
        Concentration con2 = new Concentration(5.0, Unit.mg_per_disk);
        Assert.assertEquals(con2.toString(), "5.0 | mg_per_disk");
    }
}
