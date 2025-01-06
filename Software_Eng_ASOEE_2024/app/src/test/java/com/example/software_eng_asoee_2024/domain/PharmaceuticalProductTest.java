package com.example.software_eng_asoee_2024.domain;


import static com.example.software_eng_asoee_2024.domain.Form.PILL;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;

public class PharmaceuticalProductTest {
    @Test
    public void testName(){
        PharmaceuticalProduct php = new PharmaceuticalProduct();
        php.setName("name1");
        Assert.assertEquals("name1", php.getName());
    }

    @Test
    public void testRetailPrice(){
        PharmaceuticalProduct php = new PharmaceuticalProduct();
        php.setRetailPrice(500);
        Assert.assertEquals((Integer)500, php.getRetailPrice());
    }

    @Test
    public void testForm(){
        PharmaceuticalProduct php = new PharmaceuticalProduct();
        php.setForm(PILL);
        Assert.assertEquals(PILL, php.getForm());
    }

    @Test
    public void testMedicineType(){
        PharmaceuticalProduct php = new PharmaceuticalProduct();
        php.setMedicineType(MedicineType.GENERIC);
            Assert.assertEquals(MedicineType.GENERIC, php.getMedicineType());
    }

    @Test
    public void testCustomerParticipation(){
        ArrayList<ActiveSubstance> as = new ArrayList<ActiveSubstance>();
        as.add(new ActiveSubstance("Paracetamol", 20d));
        PharmaceuticalProduct php1 =
                new PharmaceuticalProduct("testName1", 500, PILL, MedicineType.GENERIC, as, "16 pills included");
        Assert.assertEquals(0.02, php1.getCustomerParticipation(), 0.0);

        PharmaceuticalProduct php2 =
                new PharmaceuticalProduct("testName2", 500, PILL, MedicineType.ORIGINAL, as, "16 pills included");
        Assert.assertEquals(0.1, php2.getCustomerParticipation(), 0.0);
    }

    @Test
    public void testFinalPrice(){
        ArrayList<ActiveSubstance> as = new ArrayList<ActiveSubstance>();
        as.add(new ActiveSubstance("Paracetamol", 20d));
        PharmaceuticalProduct php1 =
                new PharmaceuticalProduct("testName1", 4285, PILL, MedicineType.ORIGINAL, as, "16 pills included");
        Assert.assertEquals((Integer) 429, php1.getFinalPrice());

        PharmaceuticalProduct php2 =
                new PharmaceuticalProduct("testName2", 746, PILL, MedicineType.GENERIC, as, "16 pills included");
        Assert.assertEquals((Integer) 15, php2.getFinalPrice());
    }
}
