package com.example.software_eng_asoee_2024;

import org.junit.*;

import java.util.ArrayList;

public class PrescriptionExecutionTest {
    static Doctor doc;
    static Patient pat;
    static Prescription presc;
    static PrescriptionExecution prescExc;
    static Pharmacist pharm;
    static PrescriptionLine line;
    static ProductQuantity qntt;
    static ArrayList<ProductQuantity> qntts;
    static ArrayList<ActiveSubstance> activeSubstances;

    @Before
    public void init() {
        doc = new Doctor("John", "Doe", "Cardiology");
        pat = new Patient("Tom", "Hobs", 123123123);
        presc = new Prescription("Wolff-Parkinson-White", doc, pat);
        line = new PrescriptionLine(Form.DISK, new Concentration(10, Unit.mg_per_disk), "For a week", new ActiveSubstance("Paracetamol", 10d));
        presc.addLine(line);
        pharm = new Pharmacist("Bob", "Smith");
        activeSubstances = new ArrayList<ActiveSubstance>();
        activeSubstances.add(new ActiveSubstance("Paracetamol", 10d));
        qntt = new ProductQuantity(new PharmacudicalProduct("Depon", 998, Form.DISK, MedicineType.ORIGINAL, activeSubstances), 10);
        prescExc = new PrescriptionExecution(pharm, presc);
        qntts =  new ArrayList<ProductQuantity>();
        qntts.add(qntt);
    }

    @Test
    public void testInit() {
        Assert.assertEquals(prescExc.completionDate.getDay(), (new Date()).getDay());
        Assert.assertEquals(prescExc.getPrescription(), presc);
        Assert.assertEquals(prescExc.getPharmacist(), pharm);
        prescExc.addProductQuantity(qntt);
        Assert.assertTrue(prescExc.getProductQuantities().contains(qntt));
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            prescExc.addProductQuantity(qntt);
        });
    }

    @Test
    public void testConstruct() {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            prescExc = new PrescriptionExecution(pharm, null);
        });
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            prescExc = new PrescriptionExecution(null, presc);
        });
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            prescExc = new PrescriptionExecution(pharm, presc, null);
        });
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            prescExc = new PrescriptionExecution(null, presc, qntts);
        });
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            prescExc = new PrescriptionExecution(pharm, presc, qntts);
            prescExc.addProductQuantity(new ProductQuantity(qntt.getProduct(), 2));
        });
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            prescExc = new PrescriptionExecution(pharm, presc);
            prescExc.addProductQuantity(qntt);
            prescExc.addProductQuantity(qntt);
        });
        qntts.remove(qntt);
        ArrayList<ActiveSubstance> acSubs = new ArrayList<ActiveSubstance>();
        acSubs.add(new ActiveSubstance("Ibuprofen", 10d));
        qntt = new ProductQuantity(new PharmacudicalProduct("Depon", 998, Form.DISK, MedicineType.ORIGINAL, acSubs), 10);
        qntts.add(qntt);
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            prescExc = new PrescriptionExecution(pharm, presc, qntts);
        });
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            prescExc = new PrescriptionExecution(pharm, presc);
            prescExc.addProductQuantity(qntt);
        });
    }

    @Test
    public void testProductQuantities() {
        Assert.assertEquals(prescExc.getProductQuantities().size(), 0);

        prescExc.addProductQuantity(qntt);
        Assert.assertEquals(prescExc.getProductQuantities().size(), 1);
        Assert.assertEquals(prescExc.getProductQuantities().get(0), qntt);
        Assert.assertEquals((int) prescExc.calculateTotalCost(), qntt.getProductQuantity() * qntt.getProduct().getFinalPrice()); // maybe 1000

        prescExc = new PrescriptionExecution(pharm, presc, qntts);
        Assert.assertEquals(prescExc.getProductQuantities().size(), 1);
        Assert.assertEquals(prescExc.getProductQuantities().get(0), qntt);
        Assert.assertEquals((int) prescExc.calculateTotalCost(), qntt.getProductQuantity() * qntt.getProduct().getFinalPrice());
    }
}