package com.example.software_eng_asoee_2024;

import static com.example.software_eng_asoee_2024.Form.PILL;

import org.junit.Assert;
import org.junit.Test;

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
        PharmacudicalProduct php1 =
                new PharmacudicalProduct("testName1", 500, PILL, MedicineType.GENERIC);
        Assert.assertEquals((Integer)10, php1.getCustomerParticipation());

        PharmacudicalProduct php2 =
                new PharmacudicalProduct("testName2", 500, PILL, MedicineType.ORIGINAL);
        Assert.assertEquals((Integer)2, php2.getCustomerParticipation());
    }

    @Test
    public void testFinalPrice(){
        PharmacudicalProduct php1 =
                new PharmacudicalProduct("testName1", 500, PILL, MedicineType.GENERIC);
        Assert.assertEquals((Integer) 50, php1.getFinalPrice());

        PharmacudicalProduct php2 =
                new PharmacudicalProduct("testName2", 500, PILL, MedicineType.ORIGINAL);
        Assert.assertEquals((Integer) 10, php2.getFinalPrice());
    }
}
