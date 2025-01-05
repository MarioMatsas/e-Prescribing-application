package com.example.software_eng_asoee_2024.domain;


import static com.example.software_eng_asoee_2024.domain.Form.PILL;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;

public class PharmacudicalProductTest {
    @Test
    public void testName(){
        PharmacudicalProduct php = new PharmacudicalProduct();
        php.setName("name1");
        Assert.assertEquals("name1", php.getName());
    }

    @Test
    public void testRetailPrice(){
        PharmacudicalProduct php = new PharmacudicalProduct();
        php.setRetailPrice(500);
        Assert.assertEquals((Integer)500, php.getRetailPrice());
    }

    @Test
    public void testForm(){
        PharmacudicalProduct php = new PharmacudicalProduct();
        php.setForm(PILL);
        Assert.assertEquals(PILL, php.getForm());
    }

    @Test
    public void testMedicineType(){
        PharmacudicalProduct php = new PharmacudicalProduct();
        php.setMedicineType(MedicineType.GENERIC);
            Assert.assertEquals(MedicineType.GENERIC, php.getMedicineType());
    }

    @Test
    public void testCustomerParticipation(){
        ArrayList<ActiveSubstance> as = new ArrayList<ActiveSubstance>();
        as.add(new ActiveSubstance("Paracetamol", 20d));
        PharmacudicalProduct php1 =
                new PharmacudicalProduct("testName1", 500, PILL, MedicineType.GENERIC, as, "16 pills included");
        Assert.assertEquals(0.02, php1.getCustomerParticipation(), 0.0);

        PharmacudicalProduct php2 =
                new PharmacudicalProduct("testName2", 500, PILL, MedicineType.ORIGINAL, as, "16 pills included");
        Assert.assertEquals(0.1, php2.getCustomerParticipation(), 0.0);
    }

    @Test
    public void testFinalPrice(){
        ArrayList<ActiveSubstance> as = new ArrayList<ActiveSubstance>();
        as.add(new ActiveSubstance("Paracetamol", 20d));
        PharmacudicalProduct php1 =
                new PharmacudicalProduct("testName1", 4285, PILL, MedicineType.ORIGINAL, as, "16 pills included");
        Assert.assertEquals((Integer) 429, php1.getFinalPrice());

        PharmacudicalProduct php2 =
                new PharmacudicalProduct("testName2", 746, PILL, MedicineType.GENERIC, as, "16 pills included");
        Assert.assertEquals((Integer) 15, php2.getFinalPrice());
    }
}
