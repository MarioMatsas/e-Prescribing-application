package com.example.software_eng_asoee_2024.domain;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionExecutionTest {
    static Doctor doc;
    static Patient pat;
    static Prescription presc;
    static PrescriptionExecution prescExc;
    static Pharmacist pharm;
    static PrescriptionLine line;
    static ProductQuantity qntt;
    static ArrayList<ProductQuantity> qntts;
    static ArrayList<ActiveSubstance> activeSubs = new ArrayList<>();

    @Before
    public void init() {
        doc = new Doctor("John", "Doe", "Cardiology");
        pat = new Patient("Tom", "Hobs", 123123123);
        presc = new Prescription("Wolff-Parkinson-White", doc, pat);
//        line = new PrescriptionLine(Form.DISK, new Concentration(10, Unit.mg_per_disk), "For a week", new ActiveSubstance("Paracetamol", 10d));
        line = new PrescriptionLine(Form.PILL, new Concentration(10.0, Unit.mg_per_disk), "For a week", new ActiveSubstance("Paracetamol", 10d));
        presc.addLine(line);
        pharm = new Pharmacist("Bob", "Smith");
        activeSubs.add(new ActiveSubstance("Paracetamol", 500.0));
//        qntt = new ProductQuantity(new PharmaceuticalProduct("Depon", 998, Form.PILL, MedicineType.ORIGINAL, activeSubs, "2 big pills"), 10);
        List<Concentration> conc_list = new ArrayList<Concentration>();
        conc_list.add(new Concentration(3.2, Unit.mg_per_disk));
        qntt = new ProductQuantity(new PharmaceuticalProduct("Depon", 998, Form.PILL, MedicineType.ORIGINAL, activeSubs, conc_list, "2 big pills"), 10);
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
        Assert.assertEquals((int) prescExc.calculateTotalCost(), qntt.getProductQuantity() * qntt.getProduct().getFinalPrice()); // maybe 1000

        prescExc = new PrescriptionExecution(pharm, presc, qntts);
        Assert.assertEquals(prescExc.getProductQuantities().size(), 1);
        Assert.assertEquals(prescExc.getProductQuantities().get(0), qntt);
        Assert.assertEquals((int) prescExc.calculateTotalCost(), qntt.getProductQuantity() * qntt.getProduct().getFinalPrice());
    }
}