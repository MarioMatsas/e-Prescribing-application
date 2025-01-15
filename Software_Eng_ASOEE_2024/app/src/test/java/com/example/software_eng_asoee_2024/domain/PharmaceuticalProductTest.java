package com.example.software_eng_asoee_2024.domain;


import static com.example.software_eng_asoee_2024.domain.Form.CREAM;
import static com.example.software_eng_asoee_2024.domain.Form.PILL;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PharmaceuticalProductTest {
    /**
     *
     * Test constructor at throwing exception when the two lists have different sizes
     */
    @Test
    public void testConstructorExceptionNotSameSize(){
        ArrayList<ActiveSubstance> as = new ArrayList<>();
        as.add(new ActiveSubstance("Meth", 3.9));
        ArrayList<Concentration> c = new ArrayList<>();

        Assert.assertThrows(IllegalArgumentException.class, () -> {
            PharmaceuticalProduct php = new PharmaceuticalProduct("depon", 32, CREAM, MedicineType.GENERIC, as, c, "info");
        });
    }

    /**
     *
     * Test constructor at throwing exception when there are no active subs
     */
    @Test
    public void testConstructorExceptionNoActSub(){
        ArrayList<ActiveSubstance> as = new ArrayList<>();
        ArrayList<Concentration> c = new ArrayList<>();

        Assert.assertThrows(IllegalArgumentException.class, () -> {
            PharmaceuticalProduct php = new PharmaceuticalProduct("depon", 32, CREAM, MedicineType.GENERIC, as, c, "info");
        });
    }

    /**
     *
     * Test constructor at throwing exception when there is wrong price
     */
    @Test
    public void testConstructorExceptionWrongPrice(){
        ArrayList<ActiveSubstance> as = new ArrayList<>();
        as.add(new ActiveSubstance("Meth", 3.9));
        ArrayList<Concentration> c = new ArrayList<>();
        c.add(new Concentration(3.4, Unit.mg_per_g));

        Assert.assertThrows(IllegalArgumentException.class, () -> {
            PharmaceuticalProduct php = new PharmaceuticalProduct("depon", -32, CREAM, MedicineType.GENERIC, as, c, "info");
        });//negative price

        Assert.assertThrows(IllegalArgumentException.class, () -> {
            PharmaceuticalProduct php = new PharmaceuticalProduct("depon", 0, CREAM, MedicineType.GENERIC, as, c, "info");
        });//zero price
    }

    /**
     *
     * Test active substance and concentration setters
     */
    @Test
    public void testSubstAndConcSetters(){
        PharmaceuticalProduct php = new PharmaceuticalProduct();
        ArrayList<ActiveSubstance> as = new ArrayList<>();
        ArrayList<Concentration> c = new ArrayList<>();
        php.setActiveSubstances(as);
        php.setActiveSubstanceConcentrations(c);
        Assert.assertEquals(as, php.getActiveSubstances());
        Assert.assertEquals(c, php.getActiveSubstanceConcentrations());
    }
    /**
     * Για επιβεβαίωση οτι θα οριστεί σωστά το όνομα.
     */
    @Test
    public void testName(){
        PharmaceuticalProduct php = new PharmaceuticalProduct();
        php.setName("name1");
        Assert.assertEquals("name1", php.getName());
    }

    /**
     * Για επιβεβαίωση οτι θα οριστεί σωστά η τιμή.
     */
    @Test
    public void testRetailPrice(){
        PharmaceuticalProduct php = new PharmaceuticalProduct();
        php.setRetailPrice(500);
        Assert.assertEquals((Integer)500, php.getRetailPrice());
    }

    /**
     * Για επιβεβαίωση οτι θα οριστεί σωστά η μορφή.
     */
    @Test
    public void testForm(){
        PharmaceuticalProduct php = new PharmaceuticalProduct();
        php.setForm(PILL);
        Assert.assertEquals(PILL, php.getForm());
    }

    /**
     * Για επιβεβαίωση οτι θα οριστεί σωστά ο τύπος.
     */
    @Test
    public void testMedicineType(){
        PharmaceuticalProduct php = new PharmaceuticalProduct();
        php.setMedicineType(MedicineType.GENERIC);
            Assert.assertEquals(MedicineType.GENERIC, php.getMedicineType());
    }

    /**
     * Για επιβεβαίωση οτι θα οριστεί σωστά η συμμετοχή ασθενή.
     */
    @Test
    public void testCustomerParticipation(){
        ArrayList<ActiveSubstance> as = new ArrayList<ActiveSubstance>();
        as.add(new ActiveSubstance("Paracetamol", 20d));

        List<Concentration> conc_list = new ArrayList<Concentration>();
        conc_list.add(new Concentration(3.2, Unit.mg_per_disk));

        PharmaceuticalProduct php1 =
//                new PharmaceuticalProduct("testName1", 500, PILL, MedicineType.GENERIC, as, "16 pills included");
                new PharmaceuticalProduct("testName1", 500, PILL, MedicineType.GENERIC, as, conc_list, "16 pills included");
        Assert.assertEquals(0.02, php1.getCustomerParticipation(), 0.0);

        PharmaceuticalProduct php2 =
//                new PharmaceuticalProduct("testName2", 500, PILL, MedicineType.ORIGINAL, as, "16 pills included");
                new PharmaceuticalProduct("testName2", 500, PILL, MedicineType.ORIGINAL, as, conc_list, "16 pills included");
        Assert.assertEquals(0.1, php2.getCustomerParticipation(), 0.0);
    }

    /**
     * Για επιβεβαίωση οτι θα υπολογιστεί σωστά η τελική τιμή.
     */
    @Test
    public void testFinalPrice(){
        ArrayList<ActiveSubstance> as = new ArrayList<ActiveSubstance>();
        as.add(new ActiveSubstance("Paracetamol", 20d));

        List<Concentration> conc_list = new ArrayList<Concentration>();
        conc_list.add(new Concentration(3.2, Unit.mg_per_disk));

        PharmaceuticalProduct php1 =
//                new PharmaceuticalProduct("testName1", 4285, PILL, MedicineType.ORIGINAL, as, "16 pills included");
                new PharmaceuticalProduct("testName1", 4285, PILL, MedicineType.ORIGINAL, as, conc_list, "16 pills included");
        Assert.assertEquals((Integer) 429, php1.getFinalPrice());

        PharmaceuticalProduct php2 =
//                new PharmaceuticalProduct("testName2", 746, PILL, MedicineType.GENERIC, as, "16 pills included");
                new PharmaceuticalProduct("testName2", 746, PILL, MedicineType.GENERIC, as, conc_list, "16 pills included");
        Assert.assertEquals((Integer) 15, php2.getFinalPrice());
    }

    /**
     * Για επιβεβαίωση οτι θα πεταχτεί exception για λάθος ανιστίχοιση περιεκτηκότητας και μονάδας μέτρησης.
     */
    @Test
    public void testSubstanceUnitException(){
        ArrayList<ActiveSubstance> a = new ArrayList<>();
        ArrayList<Concentration> c = new ArrayList<>();
        a.add(new ActiveSubstance("Paracetamol", 20d));
        a.add(new ActiveSubstance("Paracetamol", 20d));
        c.add(new Concentration(10.0, Unit.mg_per_g));
        c.add( new Concentration(10.0, Unit.mg_per_disk));
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            PharmaceuticalProduct p = new PharmaceuticalProduct("n",1234, PILL, MedicineType.GENERIC, a, c, "blah");
        });
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει true όταν το φάρμακο είναι όμοιο.
     */
    @Test
    public void testEqualsEqual(){
        ArrayList<ActiveSubstance> a = new ArrayList<ActiveSubstance>();
        a.add(new ActiveSubstance("Paracetamol", 20d));

        List<Concentration> c = new ArrayList<Concentration>();
        c.add(new Concentration(3.2, Unit.mg_per_disk));

        PharmaceuticalProduct p1 = new PharmaceuticalProduct("n",1234, PILL, MedicineType.GENERIC, a, c, "blah");
        PharmaceuticalProduct p2 = new PharmaceuticalProduct("n",1234, PILL, MedicineType.GENERIC, a, c, "blah");

        Assert.assertTrue(p1.equals(p2));
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει false ότι το φάρμακο δεν είναι όμοιο.
     */
    @Test
    public void testEqualsNotEqual(){
        ArrayList<ActiveSubstance> a1 = new ArrayList<ActiveSubstance>();
        a1.add(new ActiveSubstance("Paracetamol", 20d));
        ArrayList<ActiveSubstance> a2 = new ArrayList<ActiveSubstance>();
        a2.add(new ActiveSubstance("Aspirin", 20d));

        List<Concentration> c1 = new ArrayList<Concentration>();
        c1.add(new Concentration(3.2, Unit.mg_per_disk));
        List<Concentration> c2 = new ArrayList<Concentration>();
        c2.add(new Concentration(5.2, Unit.mg_per_disk));

        PharmaceuticalProduct p1 = new PharmaceuticalProduct("n",1234, PILL, MedicineType.GENERIC, a1, c1, "blah1");
        PharmaceuticalProduct p2 = new PharmaceuticalProduct("noo",1235, PILL, MedicineType.ORIGINAL, a2, c2, "blah2");

        Assert.assertFalse(p1.equals(p2));
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει false όταν συγκρίνει ανόμοια αντικείμενα,
     * ως προς τον τύπο κλάσης.
     */
    @Test
    public void testEqualsNotEqualClass(){
        ArrayList<ActiveSubstance> a = new ArrayList<ActiveSubstance>();
        a.add(new ActiveSubstance("Paracetamol", 20d));

        List<Concentration> c = new ArrayList<Concentration>();
        c.add(new Concentration(3.2, Unit.mg_per_disk));

        PharmaceuticalProduct p1 = new PharmaceuticalProduct("n",1234, PILL, MedicineType.GENERIC, a, c, "blah");
        Assert.assertFalse(p1.equals("null"));

    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει false όταν συγκρίνει με null.
     */
    @Test
    public void testEqualsNull(){
        ArrayList<ActiveSubstance> a = new ArrayList<ActiveSubstance>();
        a.add(new ActiveSubstance("Paracetamol", 20d));

        List<Concentration> c = new ArrayList<Concentration>();
        c.add(new Concentration(3.2, Unit.mg_per_disk));

        PharmaceuticalProduct p1 = new PharmaceuticalProduct("n",1234, PILL, MedicineType.GENERIC, a, c, "blah");
        Assert.assertFalse(p1.equals(null));
    }

}
