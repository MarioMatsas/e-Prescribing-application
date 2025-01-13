package com.example.software_eng_asoee_2024.domain;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionExecutionTest {
    Doctor doc;
    Patient pat;
    Prescription presc;
    PrescriptionExecution prescExc;
    Pharmacist pharm;
    PrescriptionLine line;
    ProductQuantity qntt;
    ArrayList<ProductQuantity> qntts;
    ActiveSubstance a = new ActiveSubstance("Paracetamol", 500.0);
    ArrayList<ActiveSubstance> activeSubs = new ArrayList<>();

    /**
     * Πριν τα τέστ, αρχικοποιούνται τα πεδία.
     */
    @Before
    public void init() {
        doc = new Doctor("John", "Doe", "Cardiology");
        pat = new Patient("Tom", "Hobs", 123123123);
        presc = new Prescription("Wolff-Parkinson-White", doc, pat);
        line = new PrescriptionLine(Form.PILL, new Concentration(10.0, Unit.mg_per_disk), "For a week", a);
        presc.addLine(line);
        pharm = new Pharmacist("Bob", "Smith");
        activeSubs.add(a);
        List<Concentration> conc_list = new ArrayList<Concentration>();
        conc_list.add(new Concentration(3.2, Unit.mg_per_disk));
        qntt = new ProductQuantity(new PharmaceuticalProduct("Depon", 998, Form.PILL, MedicineType.ORIGINAL, activeSubs, conc_list, "2 big pills"), 10);
        prescExc = new PrescriptionExecution(pharm, presc);
        qntts =  new ArrayList<ProductQuantity>();
        qntts.add(qntt);
    }

    /**
     * Για επιβεβαίωση οτι δούλεψε σωστά το init().
     */
    @Test
    public void testInit() {
        Assert.assertEquals(prescExc.completionDate.getDay(), (new Date()).getDay());
        Assert.assertEquals(prescExc.getPrescription(), presc);
        Assert.assertEquals(prescExc.getPharmacist(), pharm);
    }

    /**
     * Για επιβεβαίωση οτι δούλεψε σωστά ο κατασκευαστής.
     */
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

    /**
     * Για επιβεβαίωση οτι δούλεψε σωστά το addProductQuantity().
     */
    @Test
    public void testProductQuantities() {
        // Product correctly added
        ArrayList<ActiveSubstance> actives = new ArrayList<ActiveSubstance>();
        actives.add(a);
        List<Concentration> conc_list = new ArrayList<Concentration>();
        conc_list.add(new Concentration(3.2, Unit.mg_per_disk));
        PharmaceuticalProduct pr = new PharmaceuticalProduct("blahblahblah", 50, Form.PILL, MedicineType.GENERIC, actives, conc_list, "info");
        prescExc.addProductQuantity(new ProductQuantity(pr, 17));
        Assert.assertEquals(prescExc.getProductQuantities().size(), 1);

        // Product rejected, because it's active substances don't match those of the prescription lines
        actives = new ArrayList<ActiveSubstance>();
        actives.add(new ActiveSubstance("Paracet", 500.0));
        conc_list = new ArrayList<Concentration>();
        conc_list.add(new Concentration(3.2, Unit.mg_per_disk));
        pr = new PharmaceuticalProduct("blahblahblah", 50, Form.PILL, MedicineType.GENERIC, actives, conc_list, "info");
        prescExc.addProductQuantity(new ProductQuantity(pr, 17));
        Assert.assertEquals(prescExc.getProductQuantities().size(), 1);
    }
}