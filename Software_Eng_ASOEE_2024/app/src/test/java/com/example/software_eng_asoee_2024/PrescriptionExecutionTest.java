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

    @Before
    public void init() {
        doc = new Doctor("John", "Doe", "Cardiology");
        pat = new Patient("Tom", "Hobs", 123123123);
        presc = new Prescription("Wolff-Parkinson-White", doc, pat);
        line = new PrescriptionLine(Form.DISK, new Concentration(10, Unit.mg_per_disk), "For a week", new ActiveSubstance("Paracetamol", 10d));
        presc.addLine(line);
        pharm = new Pharmacist("Bob", "Smith");
        qntt = new ProductQuantity(new PharmacudicalProduct("Depon", 998, Form.DISK, MedicineType.ORIGINAL), 10);
        prescExc = new PrescriptionExecution(pharm, presc);
        qntts =  new ArrayList<ProductQuantity>();
        qntts.add(qntt);
    }

    @Test
    public void testInit() {
        Assert.assertEquals(prescExc.completionDate.getDay(), (new Date()).getDay());
        Assert.assertEquals(prescExc.getPrescription(), presc);
        Assert.assertEquals(prescExc.getPharmacist(), pharm);
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
            prescExc = new PrescriptionExecution(null, presc, qntts);
        });
    }

    @Test
    public void testProductQuantities() {
        Assert.assertEquals(prescExc.getProductQuantities().size(), 0);

        prescExc.addProductQuantity(qntt);
        Assert.assertEquals(prescExc.getProductQuantities().size(), 1);
        Assert.assertEquals(prescExc.getProductQuantities().get(0), qntt);
        Assert.assertEquals((int) prescExc.calculateTotalCost(), qntt.getProductQuantity() * qntt.getProduct().getFinalPrice());

        prescExc = new PrescriptionExecution(pharm, presc, qntts);
        Assert.assertEquals(prescExc.getProductQuantities().size(), 1);
        Assert.assertEquals(prescExc.getProductQuantities().get(0), qntt);
        Assert.assertEquals((int) prescExc.calculateTotalCost(), qntt.getProductQuantity() * qntt.getProduct().getFinalPrice());
    }
}